/**
 * XOJ 
 * 
 */
package com.droberts.xoj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;


/**
 * Loads an XOJ file
 * 
 * ZIP -> XML -> Parse
 * 
 * @author droberts
 */
public class Loader 
{
	/**
	 * Load from a given filename
	 * @param filename filename to load from
	 */
	public void load(String filename)
	{
		try 
		{
			// Open up the XOJ (zip)
			GZIPInputStream zip = new GZIPInputStream(new FileInputStream(filename));
			
			// Create temp file for extracted content
		    File temp = File.createTempFile("extractedxoj", ".tmp");
		    temp.deleteOnExit();
			
		    // Open up to write to the temp file
		    OutputStream extracted = new FileOutputStream(temp.getAbsolutePath());
		    
		    // Transfer bytes from the compressed file to the output file
	        byte[] buf = new byte[1024];
	        int len;
	        
	        while ((len = zip.read(buf)) > 0) 
	        {
	        	extracted.write(buf, 0, len);
	        }
	    
	        // Kill off gzip
	        zip.close();
	        
	        // Kill of creation of temp xml
	        extracted.close();
			
			
		} 
		catch (IOException e) 
		{
			// Presumably we get this for "Not a gzip" 
			// 
			throw new RuntimeException("Failed to load from " + filename + " - does not exist or not a .XOJ? ");
		}
		
	}

}
