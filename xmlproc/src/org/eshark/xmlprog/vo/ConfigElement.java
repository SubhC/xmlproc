/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eshark.xmlprog.vo;


/**
 * Project Name  :: XMLProgramming
 * File Name     :: ConfigElement.java
 * Created on    :: Feb 29, 2008 7:06:29 PM
 * @author       :: Subhasish Chattopadhyay
 * Purpose       :: 
 *
 * ********************************************************************
 *                         File Change History                        *
 **********************************************************************
 *       Date        |                 Description                    |
 *---------------------------------------------------------------------
 *   Feb 29, 2008 |                  Created                       |
 *---------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eshark.xmlprog.api.IConfigElement;

public class ConfigElement implements IConfigElement
{

	private static final long serialVersionUID = 2009562096563552034L;
	//the Tag Name
	private String 						mName					=	"";
	//the parent of the current ConfigElement
	private IConfigElement	 			mParent					=	null;
	//value of the tag
	private String						mValue					=	"";
	//holds all childs as ref of IConfigElement 
	private ArrayList<IConfigElement>	mChilds					=	null;
	//holds all attribute name and value pair
	private Map<String,String>			mAttributes				=	null;
	
	
	/**
	 * This creates ConfigElement
	 */
	public ConfigElement()
	{	
		super();
		mChilds						=	new ArrayList<IConfigElement>();
		mAttributes					=	new HashMap<String,String>();
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getName()
	 */
	public String getName() 
	{
		return mName;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String aName) 
	{
		mName 					= 	aName;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(String aValue) 
	{
	  mValue						=	aValue;
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getParent()
	 */
	public IConfigElement getParent() 
	{
		return mParent;
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#setParent(com.documentum.web.formext.config.IConfigElement)
	 */
	public void setParent(IConfigElement aParent) 
	{
		mParent					=	aParent;
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getValue()
	 */
	public String getValue() 
	{
		return mValue;
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getValueAsBoolean()
	 */
	public Boolean getValueAsBoolean() 
	{
		return new Boolean(mValue);
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getValueAsInteger()
	 */
	public Integer getValueAsInteger() 
	{
		return Integer.valueOf(mValue,10);
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getAttributeValue(java.lang.String)
	 */
	public String getAttributeValue(String attrName) 
	{
		if(mAttributes.size()>0)
		{
			Object tempObj				=	mAttributes.get(attrName);	
			// in case that attr name is not present return null else actual value
			return tempObj==null?null:(String)tempObj;
		}
		//in case of no attribute return null
		return null; 
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getAttributeValueAsBoolean(java.lang.String)
	 */
	public Boolean getAttributeValueAsBoolean(String attrName) 
	{
		if(mAttributes.size()>0)
		{
			Object tempObj				=	mAttributes.get(attrName);	
			// in case that attr name is not present return null else actual value
			return tempObj==null?null:new Boolean((String)tempObj);
		}
		//in case of no attribute return null
		return null; 
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getAttributeValueAsInteger(java.lang.String)
	 */
	public Integer getAttributeValueAsInteger(String attrName) 
	{
		if(mAttributes.size()>0)
		{
			Object tempObj				=	mAttributes.get(attrName);	
			// in case that attr name is not present return null else actual value
			return tempObj==null?null:Integer.valueOf((String)tempObj,10);
		}
		//in case of no attribute return null
		return null;
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#setAttributeValue(java.lang.String, java.lang.String)
	 */
	public void setAttributeValue(String aKey, String aValue) 
	{
		mAttributes.put(aKey,aValue);
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#setAttributeValue(java.lang.String, boolean)
	 */
	public void setAttributeValue(String aKey, boolean aValue) 
	{
		//convert the boolean value into string.
		mAttributes.put(aKey,aValue+"");
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#setAttributeValue(java.lang.String, int)
	 */
	public void setAttributeValue(String aKey, int aValue) 
	{
		//convert the int value into string.
		mAttributes.put(aKey,aValue+"");
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getAttributeNames()
	 */
	public Iterator<String> getAttributeNames() 
	{
		return mAttributes.isEmpty()?null:mAttributes.keySet().iterator();
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getChildElements()
	 */
	public Iterator<IConfigElement> getChildElements() 
	{
		return mChilds.isEmpty()?null:mChilds.iterator();
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getChildElements(java.lang.String)
	 */
	public Iterator<IConfigElement> getChildElements(String aChildCommonName) 
	{
		if(aChildCommonName == null || aChildCommonName.trim().length()== 0) return null;
		ArrayList<IConfigElement>				repeatedChilds		=	new ArrayList<IConfigElement>();
		IConfigElement			thisChild			=	null;
		for (Iterator<IConfigElement> itrChild = mChilds.iterator(); itrChild.hasNext();) 
		{
			thisChild = (IConfigElement) itrChild.next();
			if(aChildCommonName.equals(thisChild.getName()))
				repeatedChilds.add(thisChild);
		}
		return repeatedChilds.isEmpty()?null:repeatedChilds.iterator();
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getChildElement(java.lang.String)
	 */
	public IConfigElement getChildElement(String aChildName) 
	{
		if(aChildName == null || aChildName.trim().length()== 0) return null;
		IConfigElement			thisChild			=	null;
		for (Iterator<IConfigElement> itrChild = mChilds.iterator(); itrChild.hasNext();) 
		{
			thisChild = (IConfigElement) itrChild.next();
			if(aChildName.equals(thisChild.getName()))
			{
				return thisChild;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getChildValue(java.lang.String)
	 */
	public String getChildValue(String aChildName) 
	{
		if(aChildName == null || aChildName.trim().length()== 0) return null;
		IConfigElement			thisChild			=	null;
		for (Iterator<IConfigElement> itrChild = mChilds.iterator(); itrChild.hasNext();) 
		{
			thisChild = (IConfigElement) itrChild.next();
			if(aChildName.equals(thisChild.getName()))
				break;
		}
		return thisChild == null ? null:thisChild.getValue();
	}
	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getChildValueAsBoolean(java.lang.String)
	 */
	public Boolean getChildValueAsBoolean(String aChildName) 
	{
		if(aChildName == null || aChildName.trim().length()== 0) return null;
		IConfigElement			thisChild			=	null;
		for (Iterator<IConfigElement> itrChild = mChilds.iterator(); itrChild.hasNext();) 
		{
			thisChild = (IConfigElement) itrChild.next();
			if(aChildName.equals(thisChild.getName()))
				break;
		}
		return thisChild == null ? null: thisChild.getValueAsBoolean();
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getChildValueAsInteger(java.lang.String)
	 */
	public Integer getChildValueAsInteger(String aChildName) 
	{
		if(aChildName == null || aChildName.trim().length()== 0) return null;
		IConfigElement			thisChild			=	null;
		for (Iterator<IConfigElement> itrChild = mChilds.iterator(); itrChild.hasNext();) 
		{
			thisChild = (IConfigElement) itrChild.next();
			if(aChildName.equals(thisChild.getName()))
				break;
		}
		return thisChild == null ? null: thisChild.getValueAsInteger();
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getDescendantElement(java.lang.String)
	 */
	public IConfigElement getDescendantElement(String aChildName) 
	{
		return getChildElement(aChildName);
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getDescendantValue(java.lang.String)
	 */
	public String getDescendantValue(String aChildName) 
	{
		return getChildValue(aChildName);
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getDescendantValueAsBoolean(java.lang.String)
	 */
	public Boolean getDescendantValueAsBoolean(String aChildName) 
	{
		return getChildValueAsBoolean(aChildName);
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#getDescendantValueAsInteger(java.lang.String)
	 */
	public Integer getDescendantValueAsInteger(String aChildName) 
	{
		return  getChildValueAsInteger(aChildName);
	}

	/* (non-Javadoc)
	 * @see com.documentum.web.formext.config.IConfigElement#addChildElement(com.documentum.web.formext.config.IConfigElement)
	 */
	public void addChildElement(IConfigElement aChildElement) 
	{
		mChilds.add(aChildElement);
	}
}
