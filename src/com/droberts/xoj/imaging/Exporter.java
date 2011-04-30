/**
 * XOJ
 * 
 */
package com.droberts.xoj.imaging;

import gnu.jpdf.PDFJob;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.droberts.xoj.Progress;
import com.droberts.xoj.format.Page;
import com.droberts.xoj.format.PagePoint;
import com.droberts.xoj.format.Stroke;

/**
 * Controls the output of the given XOJ
 * 
 * @author droberts
 */
public class Exporter 
{
	/**
	 * The output renderer
	 */
	private Renderer OutputRenderer;
	
	/**
	 * Constructor
	 * @param renderer what to render the files to
	 */
	public Exporter(Renderer renderer)
	{
		// Store output renderer
		OutputRenderer = renderer;
	}
	
	/**
	 * Render a list of pages
	 * @param pageData page data to render
	 */
	public void renderPages(List<Page> pageData)
	{
		Graphics2D graphics = (Graphics2D) OutputRenderer.getRendererGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		for(Page p : pageData)
		{
			// We're rendering...
			Progress.onRendering();
			
			// Render single page
			renderPage(p);
		}
		
		// Tell the renderer we're done
		OutputRenderer.disposeRenderer();
		
		Progress.onFileComplete();
	}
	
	/**
	 * Draw the content of a given page on to the surface
	 * @param pageData page data to draw
	 */
	private void renderPage(Page pageData)
	{
		// Get the list of all strokes from the page (slow)
		List<Stroke> strokes = pageData.getStrokes();
		
		Graphics drawGraphics = OutputRenderer.getRendererGraphics();
		
		// Iterate each stroke
		for (Stroke s : strokes)
		{
			drawStroke(s, (Graphics2D)drawGraphics);
		}
		
		OutputRenderer.moveToNextPage();
	}
	
	/**
	 * Draw a single stroke
	 * @param stroke stroke to draw
	 */
	private void drawStroke(Stroke stroke, Graphics2D graphics)
	{
		// Get all positions
		List<PagePoint> positions = stroke.getPosition();

		GeneralPath gp = new GeneralPath();
		gp.moveTo(positions.get(0).getX(), positions.get(0).getY());
		
		for (int i = 0; i < positions.size(); i++)
		{
			gp.lineTo(positions.get(i).getX(), positions.get(i).getY());
		}
		
		graphics.setColor(stroke.getColour());
		graphics.setStroke( new BasicStroke((float)stroke.getWidth()[0]));
		
		graphics.draw(gp);
	}
}
