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

package org.eshark.xmlprog.service;

import java.util.Iterator;

import org.eshark.xmlprog.api.IConfigElement;
import org.eshark.xmlprog.exception.XMLProgException;

/**
 * Project Name :: XMLProgramming File Name :: ExecutorService.java Created on ::
 * Mar 17, 2008 7:55:27 PM
 * 
 * @author :: Subhasish Chattopadhyay Purpose ::
 * 
 * ******************************************************************** File
 * Change History *
 * ********************************************************************* Date |
 * Description |
 * --------------------------------------------------------------------- Mar 17,
 * 2008 | Created |
 * ---------------------------------------------------------------------
 */
public class ExecutorService
{
	private static ExecutorService	EXECUTOR_SERVICE	= null;
	private IConfigElement			mConfigElement		= null;

	private ExecutorService()
	{
		// Do nothing
	}

	public static ExecutorService getExecutorService()
	{
		if (EXECUTOR_SERVICE == null)
			EXECUTOR_SERVICE = new ExecutorService();
		return EXECUTOR_SERVICE;
	}

	// GETTER N' SETTER
	/**
	 * This returns the value of configElement of type IConfigElement
	 * 
	 * @return the configElement
	 */
	public IConfigElement getConfigElement()
	{
		return mConfigElement;
	}

	/**
	 * This sets the value of configElement by aConfigElement
	 * 
	 * @param aConfigElement
	 *            the configElement to set
	 */
	public void setConfigElement(IConfigElement aConfigElement)
	{
		mConfigElement = aConfigElement;
	}

	public void executeLogic(IConfigElement aConfigElement)
			throws XMLProgException
	{
		if (aConfigElement == null)
			throw new XMLProgException("Logic not present");
		Iterator<IConfigElement> childrens = aConfigElement.getChildElements();
		if (childrens == null)
			throw new XMLProgException("Logic has no statements.");
		while (childrens.hasNext())
		{
			ControllerService.executeNode((IConfigElement) childrens.next());
		}
	}

	public void executeLogic(String aLogicName) throws XMLProgException
	{
		IConfigElement lConfigElement = null;
		lConfigElement = mConfigElement.getChildElement(aLogicName);
		if (lConfigElement == null)
			throw new XMLProgException("Logic not present");
		//System.out.println(ReflectionToStringBuilder.toString(lConfigElement));
		executeLogic(lConfigElement);
	}
}
