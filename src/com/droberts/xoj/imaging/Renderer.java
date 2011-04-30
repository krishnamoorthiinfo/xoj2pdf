/**
 * XOJ
 * 
 */
package com.droberts.xoj.imaging;

import java.awt.Graphics;

/**
 * Some Graphics-oriented render which will accept our
 * data
 * 
 * @author droberts
 */
public interface Renderer 
{
	/**
	 * Cause a move to the next page
	 */
	public void moveToNextPage();
	
	/**
	 * Get the graphics for which to draw to the renderer
	 * @return
	 */
	public Graphics getRendererGraphics();
	
	/**
	 * Dispose of the renderer. Run when rendering
	 * activities have finished
	 */
	public void disposeRenderer();
}
