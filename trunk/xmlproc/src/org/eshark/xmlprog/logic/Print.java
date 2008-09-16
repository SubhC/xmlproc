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

import org.eshark.xmlprog.api.AbstractStatementHandler;
import org.eshark.xmlprog.api.IConfigElement;
import org.eshark.xmlprog.exception.XMLProgLogicException;

/**
 * Project Name  :: XMLProgramming
 * File Name     :: Print.java
 * Created on    :: Mar 17, 2008 4:19:56 PM
 * @author       :: Subhasish Chattopadhyay
 * Purpose       :: 
 *
 * ********************************************************************
 *                         File Change History                        *
 **********************************************************************
 *       Date        |                 Description                    |
 *---------------------------------------------------------------------
 *   Mar 17, 2008 |                  Created                       |
 *---------------------------------------------------------------------
 */
public class Print extends AbstractStatementHandler
{
	private final String VALUE	=	"value";
	/* (non-Javadoc)
	 * @see org.eshark.xmlprog.api.IGeneric#execute(org.eshark.xmlprog.api.IConfigElement)
	 * Purpose to override   :: To give definition of Printing values on the console
	 *
	 */
	public void perform(IConfigElement aConfigElement) throws XMLProgLogicException
	{
		if(aConfigElement == null) 
			throw new XMLProgLogicException("Invalid PRINT Configuration Element : [NULL]");
		String lTempValue		=	aConfigElement.getAttributeValue(VALUE);
		
		/*
		 * Older Implementation
		 * Iterator<String> lAttrNames = aConfigElement.getAttributeNames();
		String lTempName	=	"";
		while(lAttrNames.hasNext())
		{
			if(lTempName.equals(lTempValue))
			{
				lTempValue		=	aConfigElement.getAttributeValue(lTempValue);
				break;
			}
		}*/
		//New implementation
		lTempValue	=	aConfigElement.getAttributeValue(lTempValue)!=null?aConfigElement.getAttributeValue(lTempValue):lTempValue;
		System.out.println(lTempValue);
	}
	

}
