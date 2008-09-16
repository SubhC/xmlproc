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

package org.eshark.xmlprog.api;

import org.eshark.xmlprog.exception.XMLProgLogicException;

/**
 * Project Name  :: XMLProgramming
 * File Name     :: AbstractSelectionHandler.java
 * Created on    :: Mar 28, 2008 10:13:45 AM
 * @author       :: Subhasish Chattopadhyay
 * Purpose       :: 
 *
 * ********************************************************************
 *                         File Change History                        *
 **********************************************************************
 *       Date        |                 Description                    |
 *---------------------------------------------------------------------
 *      Mar 28, 2008 |                  Created                       |
 *---------------------------------------------------------------------
 */
public abstract class AbstractSelectionHandler implements IGenericHandler
{

	/* (non-Javadoc)
	 * @see org.eshark.xmlprog.api.IGenericHandler#execute(org.eshark.xmlprog.api.IConfigElement)
	 * Purpose to override   ::
	 *
	 */
	public void execute(IConfigElement aConfigElement)throws XMLProgLogicException
	{
		evaluate(aConfigElement);
	}

	/**
	 * @author       :: Subhasish Chattopadhyay
	 * Introduced on :: Mar 28, 2008 12:20:56 PM
	 * Purpose       ::
	 *
	 * @version      :: 
	 * @param aConfigElement
	 */
	public abstract void evaluate(IConfigElement aConfigElement)throws XMLProgLogicException;
}
