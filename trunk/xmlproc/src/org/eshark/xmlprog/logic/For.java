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

package org.eshark.xmlprog.logic;

import static org.eshark.xmlprog.service.ControllerService.executeNode;

import java.util.Iterator;

import org.eshark.xmlprog.api.AbstractIterationHandler;
import org.eshark.xmlprog.api.IConfigElement;
import org.eshark.xmlprog.exception.XMLProgLogicException;

/**
 * Project Name :: XMLProgramming File Name :: For.java Created on :: Mar 21,
 * 2008 11:14:49 PM
 * 
 * @author :: Subhasish Chattopadhyay Purpose ::
 * 
 * ******************************************************************** File
 * Change History *
 * ********************************************************************* Date |
 * Description |
 * --------------------------------------------------------------------- Mar 21,
 * 2008 | Created |
 * ---------------------------------------------------------------------
 */
public class For extends AbstractIterationHandler
{
	// final String ATTR_VAR = "var";
	final String	ATTR_START	= "start";
	final String	ATTR_END	= "end";
	final String	ATTR_STEP	= "step";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eshark.xmlprog.api.IGeneric#execute(org.eshark.xmlprog.api.IConfigElement)
	 *      Purpose to override ::
	 * 
	 */
	public void iterate(IConfigElement aConfigElement)
			throws XMLProgLogicException
	{
		if (aConfigElement == null)
			return;
		// int var = aConfigElement.getAttributeValueAsInteger(ATTR_VAR);
		int start = aConfigElement.getAttributeValueAsInteger(ATTR_START);
		int end = aConfigElement.getAttributeValueAsInteger(ATTR_END);
		int step = aConfigElement.getAttributeValueAsInteger(ATTR_STEP);

		IConfigElement tempElement = null;
		for (; start <= end; start += step)
		{
			// get all Child Nodes and executes them
			for (Iterator<IConfigElement> itrChilds = aConfigElement
					.getChildElements(); itrChilds != null
					&& itrChilds.hasNext();)
			{
				tempElement = (IConfigElement) itrChilds.next();
				tempElement.setAttributeValue(ATTR_START, start);
				tempElement.setAttributeValue(ATTR_END, end);
				tempElement.setAttributeValue(ATTR_STEP, step);
				executeNode(tempElement);
			}
		}
	}
}
