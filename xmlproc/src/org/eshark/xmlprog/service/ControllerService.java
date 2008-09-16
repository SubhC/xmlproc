package org.eshark.xmlprog.service;

import static org.eshark.xmlprog.cache.ClassCache.getClassCache;

import org.eshark.xmlprog.api.IConfigElement;
import org.eshark.xmlprog.api.IGenericHandler;
import org.eshark.xmlprog.exception.XMLProgLogicException;

/**
 * @author Subhasish Chattopadhyay
 */

public class ControllerService 
{
	public static void executeNode(IConfigElement aConfigElement) throws XMLProgLogicException
	{
		// get the node name
		String lName							=	aConfigElement.getName();
		if(lName == null) return;
		
		IGenericHandler lGenericTag;
		try
		{
			lGenericTag = (IGenericHandler)getClassCache().getAndInstantiate(lName);
		} 
		catch (InstantiationException E)
		{
			throw new XMLProgLogicException(E);
		} 
		catch (IllegalAccessException E)
		{
			throw new XMLProgLogicException(E);
		}
		if(lGenericTag == null) 
			throw new XMLProgLogicException("No Class found :: " + lName);
		lGenericTag.execute(aConfigElement);
	}
}
