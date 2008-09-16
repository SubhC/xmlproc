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

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:subhasishchat@gmail.com">Subhasish Chattopadhyay</a>
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings({"unchecked"})
public interface XMLCache extends Serializable 
{
	final String EXTERNAL_XML_VERSION 	= 	"1.0";
	
    public Object clone() throws CloneNotSupportedException;
	
	public boolean containsKey(String aKey);
	public boolean containsValue(Class aValue);
	public boolean empty();
	
	public Set<String> keySet();
	public Set<Map.Entry<String, Class>> entrySet();
	
	public int size();
	
	public Object remove(String aKey);
	public Object get(String aKey);
	public Object put(String aKey, Class aValue);
	
	public void clear();
	public void putAll(Map<String,Class> aMap);
	
	public Collection<Class> values();
}
