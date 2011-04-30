/**
 * XOJ
 * 
 * 
 */
package com.droberts.xoj;

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
	public static void main(String[] args)
	{
		// Create a loader
		Loader loader = new Loader();
		
		try 
		{
			Document d = loader.load("/Users/droberts/Dropbox/Xournal Notes/Year1/Raw/fcs-1/sets-1.xoj");
			
			PageGenerator gen = new PageGenerator(d);
		
			
			Exporter ds = new Exporter(new PdfRenderer("output.pdf"));
			ds.renderPages(gen.paginate());
			
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
