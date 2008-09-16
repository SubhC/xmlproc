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
 * File Name     :: IGenericHandler.java
 * Created on    :: Mar 9, 2008 11:32:06 AM
 * @author       :: Subhasish Chattopadhyay
 * Purpose       :: 
 *
 * ********************************************************************
 *                         File Change History                        *
 **********************************************************************
 *       Date        |                 Description                    |
 *---------------------------------------------------------------------
 *   Mar 9, 2008     |                  Created                       |
 *---------------------------------------------------------------------
 */
public interface IGenericHandler
{
	String TAG_NAME								= 	"name";

	/**
	 * This method declares the contract generic
	 * @param aConfigElement the Set ConfigElement
	 * @throws Exception the abnormal situations
	 */
	public void execute(IConfigElement aConfigElement) throws XMLProgLogicException;
}
