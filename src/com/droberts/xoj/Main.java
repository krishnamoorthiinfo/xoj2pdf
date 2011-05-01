/**
 * XOJ
 * 
 * 
 */
package com.droberts.xoj;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.droberts.xoj.format.Loader;
import com.droberts.xoj.format.PageGenerator;
import com.droberts.xoj.imaging.Exporter;
import com.droberts.xoj.imaging.pdf.PdfRenderer;

/**
 * Entry point
 * 
 * @author droberts
 */
public class Main 
{
	/**
	 * 
	 * @param args <input directory to convert> <destination directory> 
	 * Files will be named as per original names but with pdf instead of xoj
	 */
	public static void main(String[] args)
	{
		// Check args
		if (args.length != 2)
		{
			System.out.println("Expected two parameters: Input directory to convert and converted destination directory");
			return;
		}
		
		// Get input folder
		File inputFolder = new File(args[0]);
		
		// Exists?
		if (!inputFolder.exists() || !inputFolder.isDirectory())
		{
			System.out.println("Input directory does not exist or is not a directory");
			return;
		}
		
		// Get output folder
		File outputFolder = new File(args[1]);
		
		// Exists?
		if (!outputFolder.exists() || !outputFolder.isDirectory())
		{
			System.out.println("Output directory does not exist or is not a directory");
			return;
		}
		
		
		// Right, let's go to work...
		
		// Create a loader for XOJs
		Loader loader = new Loader();
		
		// List files in input dir
		File[] xojs = inputFolder.listFiles();

		// Only want XOJs
		FileFilter fileFilter = new FileFilter() {
		    public boolean accept(File file) {
		        return file.getName().toLowerCase().endsWith(".xoj");
		    }
		};
		
		// List all XOJs
		xojs = inputFolder.listFiles(fileFilter);
		
		// Iterate each XOJ
		for (int i = 0; i < xojs.length; i++)
		{
			try 
			{
				// Load the .xoj
				Document xojDocument = loader.load(xojs[i].getAbsolutePath());
				
				// Create output name
				String outputName = outputFolder.getAbsolutePath() + "/" + xojs[i].getName().replace(".xoj",".pdf");
				
				Progress.onStartFile(xojs[i].getAbsolutePath(), outputName);
				
				// Prepare to generate pages
				PageGenerator pageGen = new PageGenerator(xojDocument);
			
				// Prepare to export with the given renderer
				Exporter export = new Exporter(new PdfRenderer(outputName));
				
				// Render pages on the page generator's output
				export.renderPages(pageGen.paginate());
				
			} 
			catch (ParserConfigurationException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (SAXException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
}
