/**
 * XOJ
 * 
 */
package com.droberts.xoj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Controls actually drawing the content of each page
 * 
 * @author droberts
 */
public class DrawingSurface 
{
	
	/**
	 * Actual image we draw to
	 */
	private BufferedImage Canvas;
	
	public DrawingSurface()
	{
		// Create an image
		Canvas = new BufferedImage(1224, 1582, BufferedImage.TYPE_INT_ARGB);
		Canvas.getGraphics().fillRect(0, 0, Canvas.getWidth(), Canvas.getHeight());
	}
	
	/**
	 * Draw the content of a given page on to the surface
	 * @param pageData page data to draw
	 */
	public void draw(Page pageData)
	{
		// Get the list of all strokes from the page (slow)
		List<Stroke> strokes = pageData.getStrokes();
		
		// Iterate each stroke
		for (Stroke s : strokes)
		{
			drawStroke(s);
		}
		
		save("");
	}
	
	/**
	 * Draw a single stroke
	 * @param stroke stroke to draw
	 */
	private void drawStroke(Stroke stroke)
	{
		// Get all positions
		List<PagePoint> positions = stroke.getPosition();
		
		// Get all widths 
		double[] widths = stroke.getWidth();
		
		// Get graphics to draw with 
		Graphics graphics = Canvas.getGraphics();
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
			
			graphics.fillRect((int)positions.get(i).getX()*2, (int)positions.get(i).getY()*2, 
					(int)widthToDraw*4, (int)widthToDraw*4);
		}
	
	}

	/**
	 * Save the surface to a file
	 * @param filename filename to save the surface to
	 */
	public void save(String filename)
	{
		try 
		{
			ImageIO.write(Canvas, "png", new File("test.png"));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
