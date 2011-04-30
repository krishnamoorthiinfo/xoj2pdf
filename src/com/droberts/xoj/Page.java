/**
 * XOJ
 * 
 */
package com.droberts.xoj;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Contains the content of a single page
 * 
 * 
 * @author droberts
 */
public class Page 
{
	/**
	 * XML node data for this page
	 */
	private Node PageNode;
	
	/**
	 * The <layer> node
	 */
	private Node LayerNode;
	
	/**
	 * Construct page based on the page node in the XML
	 * it is based upon
	 * @param pageNode XML for a single page (<page> node)
	 */
	public Page(Node pageNode)
	{
		// Store the node
		PageNode = pageNode;
		
		// Get all nodes attached
		NodeList children = PageNode.getChildNodes();
		
		// Track down the layer
		for (int i = 0; i < children.getLength(); i++)
		{
			// Layer?
			if (children.item(i).getNodeName().equalsIgnoreCase("layer"))
			{
				LayerNode = children.item(i);
			}
		}
		
		// No layer?
		if (LayerNode == null)
		{
			throw new RuntimeException("Cannot find layer node in page");
		}
	}
	
	/**
	 * Get a set of all strokes from the page
	 */
	public List<Stroke> getStrokes()
	{
		// Create a list to return of all strokes on page
		ArrayList<Stroke> strokes = new ArrayList<Stroke>();
		
		// Get all nodes attached to the layer
		NodeList children = LayerNode.getChildNodes();
		
		for (int i = 0; i < children.getLength(); i++)
		{
			// Stroke data?
			if (children.item(i).getNodeName().equalsIgnoreCase("stroke"))
			{
				Node stroke = children.item(i);
				
				// Create and add new stroke
				strokes.add( new Stroke(stroke.getAttributes().getNamedItem("tool").getNodeValue(),
										stroke.getAttributes().getNamedItem("color").getNodeValue(), 
										stroke.getAttributes().getNamedItem("width").getNodeValue(), 
										stroke.getTextContent())  );				
			}
		}
		
		return strokes;
	}

}
