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
package org.eshark.xmlprog.api;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Project Name  :: XMLProgramming
 * File Name     :: GenericXMLCache
 * Created on    :: Mar 18, 2008 3:36:27 PM
 * @author       :: Subhasish Chattopadhyay
 * Purpose       :: 
 *
 * ********************************************************************
 *                         File Change History                        *
 **********************************************************************
 *       Date        |                 Description                    |
 *---------------------------------------------------------------------
 *      Mar 18, 2008 |                  Created                       |
 *---------------------------------------------------------------------
 */
@SuppressWarnings("unchecked")
public abstract class GenericXMLCache implements XMLCache,Cloneable
{
	
	protected Map<String,Class>                     mXmlMap             = 	new HashMap<String,Class>();

	
	public boolean containsKey(String aKey)
	{
		return mXmlMap.containsKey(aKey);
	}
	
	public boolean containsValue(Class aValue)
	{
		return mXmlMap.containsValue(aValue);
	}
	
	public boolean empty()
	{
		return mXmlMap.isEmpty();
	}

	public Set<String> keySet()
	{
		return mXmlMap.keySet();
	}
	
	public Set<Map.Entry<String, Class>> entrySet()
	{
		return mXmlMap.entrySet();
	}


	public int size()
	{
		return mXmlMap.size();
	}

	public Object remove(String aKey)
	{
		return mXmlMap.remove(aKey);
	}

	public Object get(String aKey)
	{
		return mXmlMap.get(aKey);
	}
	
	public IGenericHandler getAndInstantiate(String aKey) throws InstantiationException, IllegalAccessException
	{
		Class	lClass	=	(Class) mXmlMap.get(aKey);
		return (IGenericHandler)lClass.newInstance();
	}
	
	public Object put(String aKey, Class aValue)
	{
		return mXmlMap.put(aKey, aValue);
	}

	public void clear()
	{
		mXmlMap.clear();
	}

	public void putAll(Map<String,Class> aMap)
	{
		mXmlMap.putAll(aMap);
	}

	public Collection<Class> values()
	{
		return mXmlMap.values();
	}
	
	public abstract Object clone() throws CloneNotSupportedException;
	public abstract void loadFromXML(File aXMLFile) throws Exception;
	
	protected DocumentBuilder getDocumentBuilder() throws ParserConfigurationException 
	{
		//For XML 
		DocumentBuilderFactory 	lDocBuilderFactory 			= 	null;
		DocumentBuilder 		lDocBuilder 				= 	null;
		lDocBuilderFactory					= 	DocumentBuilderFactory.newInstance();
		lDocBuilderFactory.setIgnoringElementContentWhitespace(true);
		lDocBuilderFactory.setValidating(true);
		lDocBuilderFactory.setCoalescing(true);
		lDocBuilderFactory.setIgnoringComments(true);
		
		lDocBuilder 						= 	lDocBuilderFactory.newDocumentBuilder();
		lDocBuilder.setErrorHandler(new XMLCacheErrorHandler());
				
		return lDocBuilder;
	}

	public static class XMLCacheErrorHandler implements ErrorHandler
	{
		public void error(SAXParseException SAXPE) throws SAXException {throw SAXPE;}
		public void fatalError(SAXParseException SAXPE) throws SAXException {throw SAXPE;}
		public void warning(SAXParseException SAXPE) throws SAXException {throw SAXPE;}
	}
}
