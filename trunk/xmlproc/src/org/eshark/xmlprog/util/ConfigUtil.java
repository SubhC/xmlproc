package org.eshark.xmlprog.util;

/************************************************************************************************
 * This Class is the utility class to conver a XML DOM into a ConfigElement DOM.
 * This exposed only to static method.
 * 1]loadFromXML	to convert the XML DOM into ConfigElement
 * 2]print			to see the ConfigElement DOM
 ************************************************************************************************/
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;

import org.eshark.xmlprog.api.IConfigElement;
import org.eshark.xmlprog.vo.ConfigElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author subhasish.chattopadhyay
 */
public class ConfigUtil
{
	private static final long	serialVersionUID	= -2444427094486846751L;

	/************************************************************************************************
	 * This mathod takes a path for xml and parses it to produce the IConfigElement Chain and return
	 * Gets documents bouilder and calls for converting XML into the chain of IConfigElements 
	 * @param aInputStream the FileInputStream [For the specified XML file]. 
	 * @throws Exception
	 * @param aXML the String absolute path of the XML to be parsed
	 * @throws DfException 
	 * @throws IOException 
	 * @throws SAXException 
	 ************************************************************************************************/
	public static IConfigElement loadConfigFromXML(String aXMLFile)
	{
		if (isEmpty(aXMLFile))
			return null;
		Document xmlDoc = null;
		IConfigElement configEle = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(false);
		dbf.setCoalescing(true);
		dbf.setIgnoringComments(true);
		try
		{
			DocumentBuilder builder = dbf.newDocumentBuilder();
			builder.setErrorHandler(new DefaultErrorHandler());
			xmlDoc = builder.parse(aXMLFile);
			dbf = null;
			Element rootElement = (Element) xmlDoc.getDocumentElement();
			configEle = loadRecursive(rootElement, null);
		} catch (FactoryConfigurationError fce)
		{
			fce.printStackTrace();
		} catch (SAXException saxe)
		{
			saxe.printStackTrace();
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return configEle;
	}

	/************************************************************************************************
	 * This method recursively iterates the nodes and creates the ConfigElement chain.
	 * @param xmlElement the current node
	 * @param parentConfigElement the parent config element
	 ************************************************************************************************/

	private static IConfigElement loadRecursive(Node xmlElement,
			IConfigElement parentConfigElement)
	{
		if (xmlElement == null || xmlElement.getNodeType() != Node.ELEMENT_NODE)
			return null;
		IConfigElement thisChild = null;
		NodeList entries = xmlElement.getChildNodes();
		Node entry = null;
		int numEntries = entries.getLength();
		//System.out.println("Leaf "+ xmlElement.getNodeName());
		//convert the node to  Config Element
		thisChild = convertNodeToConfigElement(xmlElement, parentConfigElement);
		//System.out.println(" Parent : " +  xmlElement.getNodeName());
		//System.out.println("Root has Child  : " + numEntries);
		for (int i = 0; i < numEntries; i++)
		{
			entry = entries.item(i);
			//convert the node to  Config Element
			loadRecursive(entry, thisChild);
		}
		return thisChild;
	}

	/**
	 * This methods converts the XML node into a ConfigElement. Also sets the the parent
	 * and related childs (if any).
	 * @param currentNode
	 * @param parentConfigElement
	 * @return
	 */
	private static IConfigElement convertNodeToConfigElement(Node currentNode,
			IConfigElement parentConfigElement)
	{
		//System.out.println("Converting Node : " + currentNode.getNodeName()+ " Value " + currentNode.getNodeValue() + " Attr " + currentNode.getAttributes().getLength());
		ConfigElement aconfigElement = new ConfigElement();
		aconfigElement.setName(currentNode.getNodeName());
		//aconfigElement.setValue(currentNode.getNodeValue());
		aconfigElement.setParent(parentConfigElement);
		//This check is needed for the root node, whose parent is null
		if (parentConfigElement != null)
			parentConfigElement.addChildElement(aconfigElement);
		//System.out.println("=====================================================================================");
		//System.out.println("Converted Config Name : " + aconfigElement.getName());
		//System.out.println("Converted Config  Value " + aconfigElement.getValue());
		//System.out.println("Converted Config Parent " + (parentConfigElement == null?"NONE" : parentConfigElement.getName()));
		//Convert attributes and add
		NamedNodeMap attributes = currentNode.getAttributes();
		int attrMaxCoutnt = attributes.getLength();
		Node currentAttribute = null;
		for (int attrCnt = attrMaxCoutnt - 1; attrCnt >= 0; attrCnt--)
		{
			currentAttribute = attributes.item(attrCnt);
			aconfigElement.setAttributeValue(currentAttribute.getNodeName(),
					currentAttribute.getNodeValue());
			//System.out.println("Attribute Name : " + currentAttribute.getNodeName() + " Value " + aconfigElement.getAttributeValue(currentAttribute.getNodeName())); 
		}
		//System.out.println("=====================================================================================");
		return aconfigElement;
	}

	/*****************************************************************************
	 * This personals static SAX Error handler class customises the Error handling
	 * Please log properly in case of need.
	 *****************************************************************************/
	private static class DefaultErrorHandler implements ErrorHandler
	{
		public void error(SAXParseException SAXPE) throws SAXException
		{
			throw SAXPE;
		}

		public void fatalError(SAXParseException SAXPE) throws SAXException
		{
			throw SAXPE;
		}

		public void warning(SAXParseException SAXPE) throws SAXException
		{
			throw SAXPE;
		}
	}

	/*****************************************************************************
	 * 							MAIN METHOD TO TEST
	 *****************************************************************************/
	/*public static void main(String[] args) throws Exception
	{
		String 			url 					=	"c:\\test.xml";
		IConfigElement	xediConfig				=	loadConfigFromDocXML(url);
		print(xediConfig);
	}*/

	/*****************************************************************************
	 * 			DO NOT DELETE
	 * This method is to test the elementConfig DOM and prints in the console.
	 * @param elementConfig the root Config Element
	 *****************************************************************************/
	public static void print(IConfigElement elementConfig) 
	{  
		//recursive method call using IConfig chaining
		System.out.println();
		System.out.print('<');
		System.out.print(elementConfig.getName()+"[");
		
		Iterator<String> attrNames				= 	elementConfig.getAttributeNames();
		if (attrNames != null) 
		{
			String tempString				=	"";
			for (; attrNames.hasNext();) 
			{
				tempString 					= 	(String)attrNames.next();
				System.out.print(' ');
				System.out.print(tempString+"="+elementConfig.getAttributeValue(tempString)+",");
			}
		}
		System.out.print(" ] ");
		System.out.print('>');
		
		Iterator<IConfigElement> childrens = elementConfig.getChildElements();
		if (childrens != null) 
		{
			for (;childrens.hasNext(); ) 
			{
				print((IConfigElement)childrens.next());
			}
		}
	}
}
