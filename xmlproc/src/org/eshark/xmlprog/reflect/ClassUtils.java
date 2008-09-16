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

package org.eshark.xmlprog.reflect;
import static org.apache.commons.lang.StringUtils.isEmpty;
import org.eshark.xmlprog.exception.XMLProgException;

/**
 * Project Name  :: XMLProgramming
 * File Name     :: ClassUtils.java
 * Created on    :: Mar 17, 2008 12:04:41 PM
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
public class ClassUtils
{
	/**
     * <p>Return the <code>Class</code> object for the specified fully
     * qualified class name, from this web application's class loader.
     *
     * @SuppressWarnings("unchecked")
     * @param className Fully qualified class name
     * @throws ClassNotFoundException if the specified class cannot be loaded
     */
	@SuppressWarnings({"unchecked", "deprecation"})
    public static Class getAppClass(String className)throws XMLProgException,ClassNotFoundException {
    	if ( isEmpty(className)) 
    	{
            throw new XMLProgException("Invalid Class Name : "+className);
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if (classLoader == null) 
        {
            classLoader = ClassUtils.class.getClassLoader();
        }

        return (classLoader.loadClass(className));
    }

    /**
     * <p>Return a new instance of the specified fully qualified class name,
     * after loading the class (if necessary) from this web application's
     * class loader.</p>
     *
     * @param className Fully qualified class name
     * @throws ClassNotFoundException if the specified class cannot be loaded
     * @throws IllegalAccessException if this class is not concrete
     * @throws InstantiationException if this class has no zero-arguments
     *                                constructor
     */
    public static Object getClassInstance(String className)throws XMLProgException,ClassNotFoundException
    , IllegalAccessException, InstantiationException 
    {
    	return (getAppClass(className).newInstance());
    }
}
