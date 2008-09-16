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

package org.eshark.xmlprog.test;

import org.eshark.xmlprog.api.IConfigElement;
import org.eshark.xmlprog.cache.ClassCache;
import org.eshark.xmlprog.exception.XMLProgException;
import org.eshark.xmlprog.service.ExecutorService;
import org.eshark.xmlprog.util.ConfigUtil;

/**
 * Project Name  :: XMLProgramming
 * File Name     :: TestExecutorService.java
 * Created on    :: Mar 18, 2008 12:48:20 PM
 * @author       :: Subhasish Chattopadhyay
 * Purpose       :: 
 *
 * ********************************************************************
 *                         File Change History                        *
 **********************************************************************
 *       Date        |                 Description                    |
 *---------------------------------------------------------------------
 *   Mar 18, 2008 |                  Created                       |
 *---------------------------------------------------------------------
 */
public class TestExecutorService
{

	/**
	 * @author       :: Subhasish Chattopadhyay
	 * Introduced on :: Mar 18, 2008 12:48:20 PM
	 * Purpose       ::
	 *
	 * @version      :: 
	 * @param args
	 */
	public static void main(String[] args)throws XMLProgException
	{
		ClassCache.getClassCache().loadFromXML(null);
		ExecutorService	es	=	ExecutorService.getExecutorService();
		IConfigElement	ce	=	ConfigUtil.loadConfigFromXML("C:\\saschat\\installed\\eclipseEuropa\\workspace\\XMLProgramming\\xmls\\demo.logic.xml");
		es.setConfigElement(ce);
		es.executeLogic("example3");
	}

}
