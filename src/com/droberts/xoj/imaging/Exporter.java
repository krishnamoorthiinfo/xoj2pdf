/**
 * XOJ
 * 
 */
package com.droberts.xoj.imaging;

import gnu.jpdf.PDFJob;

import java.awt.Color;
import java.awt.Graphics;
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
			drawStroke(s, drawGraphics);
		}
		
		OutputRenderer.moveToNextPage();
	}
	
	/**
	 * Draw a single stroke
	 * @param stroke stroke to draw
	 */
	private void drawStroke(Stroke stroke, Graphics graphics)
	{
		// Get all positions
		List<PagePoint> positions = stroke.getPosition();
		
		// Get all widths 
		double[] widths = stroke.getWidth();
		
		// Get graphics to draw with 
		graphics.setColor(Color.black);

		// Store the initial width in case we run out
		double defaultWidth = 0;
		
		// Iterate all positions
		for (int i = 0 ; i < positions.size(); i++)
		{		
			double widthToDraw = 0;
			
			if (i < widths.length)
			{
				widthToDraw = widths[i];
				defaultWidth = widthToDraw;
			} else {
				widthToDraw = defaultWidth;
			}
			
			graphics.fillOval((int)positions.get(i).getX(), (int)positions.get(i).getY(), 
					(int)widthToDraw*2, (int)widthToDraw*2);
		}
	
	}
}
