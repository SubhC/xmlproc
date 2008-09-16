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

import java.io.Serializable;
import java.util.Iterator;

/**
 * Project Name  :: XMLProgramming
 * File Name     :: IConfigElement.java
 * Created on    :: Feb 29, 2008 7:12:49 PM
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
public interface IConfigElement extends Serializable
{

public String getName();

public IConfigElement getParent();

public void setParent(IConfigElement iconfigelement);

public String getValue();

public Boolean getValueAsBoolean();

public Integer getValueAsInteger();

public String getAttributeValue(String s);

public Boolean getAttributeValueAsBoolean(String s);

public Integer getAttributeValueAsInteger(String s);

public void setAttributeValue(String s, String s1);

public void setAttributeValue(String s, boolean flag);

public void setAttributeValue(String s, int i);

public Iterator<String> getAttributeNames();

public Iterator<IConfigElement> getChildElements();

public Iterator<IConfigElement> getChildElements(String s);

public IConfigElement getChildElement(String s);

public String getChildValue(String s);

public Boolean getChildValueAsBoolean(String s);

public Integer getChildValueAsInteger(String s);

public IConfigElement getDescendantElement(String s);

public String getDescendantValue(String s);

public Boolean getDescendantValueAsBoolean(String s);

public Integer getDescendantValueAsInteger(String s);

public void addChildElement(IConfigElement iconfigelement);
}