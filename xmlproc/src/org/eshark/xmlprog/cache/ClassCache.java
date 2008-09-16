/*
 * Licensed to the <Company Name> under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The <Supporter> licenses this file to You under the <Company Name> License, Version 1.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://<company site url>/addax-licenses//LICENSE-1.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eshark.xmlprog.cache;

import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.SystemUtils.getUserDir;
import static org.eshark.xmlprog.reflect.ClassUtils.getAppClass;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.eshark.xmlprog.api.GenericXMLCache;
import org.eshark.xmlprog.exception.XMLProgException;
import org.eshark.xmlprog.exception.XMLProgFormatException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ClassCache extends GenericXMLCache
{

	private static final long	serialVersionUID	= -3346813405555943467L;
	private static ClassCache	CLASS_CACHE			= null;
	public static String		FILE_PATH			= getUserDir().getAbsolutePath();
	public static String		FILE_NAME			= "class.cache.xml";
	// The required DTD URI for exported dql-cache
	private final String		DTD_URI				= "http://dtd/class-cache.dtd";
	private final String		DTD					= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
															+ "<!-- DTD for class-cache -->"
															+ "<!ELEMENT classes ( comment?, class* ) >"
															+ "<!ATTLIST classes"
															+ " version CDATA #FIXED \"1.0\">"
															+ "<!ELEMENT comment (#PCDATA) >"
															+ "<!ELEMENT class (#PCDATA) >"
															+ "<!ATTLIST class "
															+ " key CDATA #REQUIRED>";

	public Object clone() throws CloneNotSupportedException
	{
		return CLASS_CACHE;
	}

	// TOD0 :: sysnchronization is needed
	public static ClassCache getClassCache()
	{
		if (CLASS_CACHE == null)
			CLASS_CACHE = new ClassCache();
		return CLASS_CACHE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dematas.addax.common.cache.GenericXMLCache#loadFromXML(com.documentum.fc.client.IDfSession,
	 *      java.lang.String, java.lang.String)
	 */
	public void loadFromXML(File aXMLFile) throws XMLProgFormatException
	{
		try
		{
			DocumentBuilder lDocumentBuilder = getDocumentBuilder();
			lDocumentBuilder.setEntityResolver(new Resolver());
			// For XML
			Document lXMLDocument = null;
			if(aXMLFile == null)
				lXMLDocument = lDocumentBuilder.parse(new File(FILE_PATH+"\\xmls",FILE_NAME));
			else
				lXMLDocument = lDocumentBuilder.parse(aXMLFile);

			// normalize the document
			lXMLDocument.getDocumentElement().normalize();

			// Read the XML and create the cache
			Element daoElement = (Element) lXMLDocument.getChildNodes().item(1);
			String xmlVersion = daoElement.getAttribute("version");
			if (xmlVersion.compareTo(EXTERNAL_XML_VERSION) > 0)
				throw new XMLProgFormatException(
						"Exported Class Cache file format version "
								+ xmlVersion
								+ " is not supported. This XMLProg Configuration installation can read"
								+ " versions " + EXTERNAL_XML_VERSION
								+ " or older. You"
								+ " may need to check the configuration.");
			importClasses(daoElement);
		} catch (ParserConfigurationException PCE)
		{
			PCE.printStackTrace();
		} catch (SAXException SXE)
		{
			SXE.printStackTrace();
		} catch (IOException IOE)
		{
			IOE.printStackTrace();
		}
	}

	private void importClasses(Element xmlElement)
	{
		final String	lKey	=	"key";
		NodeList entries = xmlElement.getChildNodes();
		int numEntries = entries.getLength();
		int start = numEntries > 0
				&& entries.item(0).getNodeName().equals("comment") ? 1 : 0;
		for (int i = start; i < numEntries; i++)
		{
			Element entry = (Element) entries.item(i);
			if (entry.hasAttribute(lKey))
			{
				Node n = entry.getFirstChild();
				String val = (n == null) ? "" : n.getNodeValue();
				if (!isEmpty(val = val.trim()))
				{
					
					try
					{
						mXmlMap.put(entry.getAttribute(lKey), getAppClass(val));
					} 
					catch (XMLProgException XMLPE)
					{
						XMLPE.printStackTrace();
					} 
					catch (ClassNotFoundException CNFE)
					{
						CNFE.printStackTrace();
					}
					// For testing purpose
					// Please delete later
					System.out.println("CLASS KEY :: "
							+ entry.getAttribute(lKey) + " VALUE :: " + val);
				}
			}
		}
	}

	private class Resolver implements EntityResolver
	{
		public InputSource resolveEntity(String pid, String sid)
				throws SAXException
		{
			if (sid.equals(DTD_URI))
			{
				InputSource is;
				is = new InputSource(new StringReader(DTD));
				is.setSystemId(DTD_URI);
				return is;
			}
			throw new SAXException("Invalid system identifier : " + sid);
		}
	}

}
