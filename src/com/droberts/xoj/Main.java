/**
 * XOJ
 * 
 * 
 */
package com.droberts.xoj;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
			Document d = loader.load("/Users/droberts/Dropbox/Xournal Notes/Year2/SoftEng/Process/3.xoj");
			
			PageGenerator gen = new PageGenerator(d);
		
			
			DrawingSurface ds = new DrawingSurface();
			ds.draw(gen.paginate().get(0));
			
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
	}
	
	
}
