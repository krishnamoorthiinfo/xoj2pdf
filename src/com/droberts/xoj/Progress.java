/**
 * XOJ
 * 
 */
package com.droberts.xoj;

/**
 * Keeps track of progress through the conversion
 * 
 * @author droberts
 */
public class Progress 
{
	/**
	 * Page number
	 */
	private static int Page = 1;
	
	/**
	 * When conversion reaches a given filename
	 * @param filename filename reached
	 * @param destination where the file is being saved to
	 */
	public static void onStartFile(String filename, String destination)
	{
		System.out.println("Starting " + filename + " (saving output to " + destination + ")");
		
		// Reset page 
		Page = 1;
	}
	
	/**
	 * When paginating starts
	 */
	public static void onPaginating()
	{
		System.out.println("Paginating...");
	}
	
	/**
	 * When a given page is being processed
	 */
	public static void onRendering()
	{
		System.out.println("Rendering page " + Page + "...");
		Page += 1;
	}

	/**
	 * When a given file completes
	 */
	public static void onFileComplete()
	{
		System.out.println("File complete");
	}
	
}
