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

import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.eshark.xmlprog.service.ControllerService.executeNode;

import java.util.Iterator;

import org.eshark.xmlprog.api.AbstractSelectionHandler;
import org.eshark.xmlprog.api.IConfigElement;
import org.eshark.xmlprog.exception.XMLProgLogicException;

/**
 * Project Name  :: XMLProgramming
 * File Name     :: If.java
 * Created on    :: Mar 19, 2008 3:55:27 PM
 * @author       :: Subhasish Chattopadhyay
 * Purpose       :: 
 *
 * ********************************************************************
 *                         File Change History                        *
 **********************************************************************
 *       Date        |                 Description                    |
 *---------------------------------------------------------------------
 *      Mar 19, 2008 |                  Created                       |
 *---------------------------------------------------------------------
 */
public class If extends AbstractSelectionHandler
{
	//RELATIONAL
	// =
	final String	REL_EQ				= "EQ";
	//	 =
	final String	REL_NOT_EQ			= "NE";
	// <
	final String	REL_LESS_THAN		= "LT";
	// >
	final String	REL_GREATER_THAN	= "GT";
	// <=
	final String	REL_LESS_THAN_EQ	= "LE";
	// >=
	final String	REL_GREATER_THAN_EQ	= "GE";

	//final String EMPTY_STRING									=	"";
	final String	ATTR_L_VALUE		= "lvalue";
	final String	ATTR_R_VALUE		= "rvalue";
	final String	ATTR_OPERATOR		= "operator";
	final String	ATTR_OR_MARKER		= "ormarker";
	final String	IF_TAG				= "if";
	final String	ELSE_TAG			= "else";

	/* (non-Javadoc)
	 * @see org.eshark.xmlprog.api.IGeneric#execute(org.eshark.xmlprog.api.IConfigElement)
	 * Purpose to override   ::
	 *
	 */
	public void evaluate(IConfigElement aConfigElement)throws XMLProgLogicException
	{
		if (aConfigElement == null)
			return;
		//final result to return
		boolean result = false;
		// has or then the marker
		String ormarker = "";
		//left value -> left operand
		String lValue = "";
		//right value -> left operand
		String rValue = "";
		// operator
		String operator = "";

		//get all attributes value
		lValue = aConfigElement.getAttributeValue(ATTR_L_VALUE);
		rValue = aConfigElement.getAttributeValue(ATTR_R_VALUE);
		operator = aConfigElement.getAttributeValue(ATTR_OPERATOR);
		ormarker = aConfigElement.getAttributeValue(ATTR_OR_MARKER);

		// check for mandatory attributes
		if ((lValue == null || EMPTY.equals(lValue.trim()))
				|| (rValue == null /* || EMPTY_STRING.equals(lValue.trim())*/)
				|| (operator == null || EMPTY.equals(operator.trim())))
			throw new XMLProgLogicException("Invalid If definition.");

		//When all are fine then trim the LValue and RValue
		lValue = lValue.trim();
		lValue	=	aConfigElement.getAttributeValue(lValue)!=null?aConfigElement.getAttributeValue(lValue):lValue;
		
		rValue = rValue.trim();
		rValue	=	aConfigElement.getAttributeValue(rValue)!=null?aConfigElement.getAttributeValue(rValue):rValue;
		
		//if all went well then evalue the condition
		// check for multiple rvalue or not
		if (ormarker == null || EMPTY.equals(ormarker))
			result = evaluateAsPerOperator(lValue, rValue, operator);
		else
			result = evaluateAsPerOperator(lValue, rValue, operator, ormarker);
		if (result)
			executeTrue(aConfigElement);
		else
			executeElse(aConfigElement);
	}

	/**
	 * Perform the else part
	 * @param aConfigElement
	 * @throws Exception
	 */
	private void executeElse(IConfigElement aConfigElement)
			throws XMLProgLogicException
	{
		if (aConfigElement == null)
			return;
		IConfigElement element = aConfigElement.getChildElement(ELSE_TAG);
		if (element == null)
			return;
		// get all Child Nodes of the else node
		IConfigElement tempElement = null;
		for (Iterator<IConfigElement> itrChilds = element.getChildElements(); itrChilds != null
				&& itrChilds.hasNext();)
		{
			tempElement = (IConfigElement) itrChilds.next();
			executeNode(tempElement);
		}
	}

	/**
	 * Perform the true part
	 * @param aConfigElement
	 * @throws Exception
	 */
	private void executeTrue(IConfigElement aConfigElement)
			throws XMLProgLogicException
	{
		if (aConfigElement == null)
			return;
		IConfigElement tempElement = null;
		// get all Child Nodes of the if tag other than else node
		for (Iterator<IConfigElement> itrChilds = aConfigElement
				.getChildElements(); itrChilds != null && itrChilds.hasNext();)
		{
			tempElement = (IConfigElement) itrChilds.next();
			if (ELSE_TAG.equals(tempElement.getName().trim()))
				continue;
			executeNode(tempElement);
		}
	}

	/**
	 * This method evaluates the condition of the if as per the 
	 * operator present with the left operand and right operand. 
	 * @param alValue the left hand operand
	 * @param arValue the right hand operand
	 * @param anOperator the operator
	 * @return boolean the conditional evaluation
	 */
	private boolean evaluateAsPerOperator(String alValue, String arValue,
			String anOperator) throws XMLProgLogicException
	{
		/*System.out.println(
				" lValue " + alValue	+	
				" rValue "  + arValue	+
				" anOperator " + anOperator
				);*/

		//System.out.println(actLValue);
		boolean result = false;
		if (REL_EQ.equalsIgnoreCase(anOperator))
		{
			result = alValue.equalsIgnoreCase(arValue);
		} 
		else if (REL_NOT_EQ.equalsIgnoreCase(anOperator))
		{
			result = !alValue.equalsIgnoreCase(arValue);
		} 
		else if (REL_GREATER_THAN.equalsIgnoreCase(anOperator))
		{
			result = alValue.compareTo(arValue) > 0;
		} 
		else if (REL_GREATER_THAN_EQ.equalsIgnoreCase(anOperator))
		{
			result = !(alValue.compareTo(arValue) >= 0);
		} 
		else if (REL_LESS_THAN.equalsIgnoreCase(anOperator))
		{
			result = alValue.compareTo(arValue) < 0;
		} 
		else if (REL_LESS_THAN_EQ.equalsIgnoreCase(anOperator))
		{
			result = !(alValue.compareTo(arValue) <= 0);
		}
		//System.out.println("Result Is : " + result );
		return result;
	}

	/**
	 * This method evaluates the condition of the if as per the 
	 * operator present with the left operand and multi-valued right operand. 
	 * @param alValue the left hand operand
	 * @param arValue the right hand operand
	 * @param anOperator the operator
	 * @return boolean the conditional evaluation
	 */
	private boolean evaluateAsPerOperator(String alValue, String arValue,
			String anOperator, String anOrMarker) throws XMLProgLogicException
	{
		/*System.out.println(
				" lValue " + alValue	+	
				" rValue "  + arValue	+
				" anOperator " + anOperator	+
				" anOrMarker " + anOrMarker
				);*/
		boolean result = false;
		String tempStrings[] = null;

		//System.out.println(actLValue);
		if (REL_EQ.equalsIgnoreCase(anOperator))
		{
			tempStrings = arValue.split(anOrMarker);
			for (int currRValueIndx = 0; currRValueIndx < tempStrings.length; currRValueIndx++)
			{
				result = alValue.equalsIgnoreCase(tempStrings[currRValueIndx]
						.trim());
				//break if current holds true result
				if (result)
					break;
			}
		} else if (REL_NOT_EQ.equalsIgnoreCase(anOperator))
		{
			tempStrings = arValue.split(anOrMarker);
			for (int currRValueIndx = 0; currRValueIndx < tempStrings.length; currRValueIndx++)
			{
				result = !alValue.equalsIgnoreCase(tempStrings[currRValueIndx]
						.trim());
				//break if current holds true result
				if (result)
					break;
			}
		} else if (REL_GREATER_THAN.equalsIgnoreCase(anOperator))
		{
			tempStrings = arValue.split(anOrMarker);
			for (int currRValueIndx = 0; currRValueIndx < tempStrings.length; currRValueIndx++)
			{
				result = alValue.compareTo(tempStrings[currRValueIndx].trim()) > 0;
				//break if current holds true result
				if (result)
					break;
			}
		} else if (REL_GREATER_THAN_EQ.equalsIgnoreCase(anOperator))
		{
			tempStrings = arValue.split(anOrMarker);
			for (int currRValueIndx = 0; currRValueIndx < tempStrings.length; currRValueIndx++)
			{
				result = !(alValue
						.compareTo(tempStrings[currRValueIndx].trim()) >= 0);
				//break if current holds true result
				if (result)
					break;
			}
		} else if (REL_LESS_THAN.equalsIgnoreCase(anOperator))
		{
			tempStrings = arValue.split(anOrMarker);
			for (int currRValueIndx = 0; currRValueIndx < tempStrings.length; currRValueIndx++)
			{
				result = alValue.compareTo(tempStrings[currRValueIndx].trim()) < 0;
				//break if current holds true result
				if (result)
					break;
			}
		} else if (REL_LESS_THAN_EQ.equalsIgnoreCase(anOperator))
		{
			tempStrings = arValue.split(anOrMarker);
			for (int currRValueIndx = 0; currRValueIndx < tempStrings.length; currRValueIndx++)
			{
				result = !(alValue
						.compareTo(tempStrings[currRValueIndx].trim()) <= 0);
				//break if current holds true result
				if (result)
					break;
			}
		}
		//System.out.println("Result Is : " + result );
		return result;
	}
}
