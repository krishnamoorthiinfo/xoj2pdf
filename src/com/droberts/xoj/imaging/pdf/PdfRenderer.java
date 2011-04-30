/**
 * XOJ
 * 
 */
package com.droberts.xoj.imaging.pdf;

import gnu.jpdf.PDFJob;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.droberts.xoj.imaging.Renderer;

/**
 * Renders to PDF via GnuJPdf
 * 
 * @author droberts
 */
public class PdfRenderer implements Renderer
{
	/**
	 * PDF job
	 */
	private PDFJob PdfJob;
	
	/**
	 * Store the most recent graphics (since dispose
	 * causes page change)
	 */
	private Graphics LastGraphics;
	
	/**
	 * Constructor
	 * @param filename where to save output PDF
	 * @throws FileNotFoundException file cannot be found (should never be thrown)
	 */
	public PdfRenderer(String filename) throws FileNotFoundException
	{
		// Create the PDF job
		PdfJob = new PDFJob(new FileOutputStream(filename));
	}
	
	/**
	 * Dispose of the renderer. Run when rendering
	 * activities have finished
	 */
	public void disposeRenderer()
	{
		PdfJob.end();
	}
	
	/**
	 * Get the graphics for which to draw to the renderer
	 * @return
	 */
	public Graphics getRendererGraphics()
	{
		// If there is a graphics object acquired
		if (LastGraphics != null)
		{
			// Use that (don't cause page turn)
			return LastGraphics;
			
		} else {
			
			// Otherwise re-attain graphics (page turn presumably
			// occurred)
			LastGraphics = PdfJob.getGraphics();
			return LastGraphics;
		}
	}
	
	/**
	 * Cause a move to the next page
	 */
	public void moveToNextPage()
	{
		// Dispose of existent graphics so next call creates anew
		LastGraphics.dispose();
		LastGraphics = null;
	}
}
