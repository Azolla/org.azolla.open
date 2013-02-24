/*
 * @(#)AzollaCode.java		Created at 2013-2-23
 * 
 * Copyright (c) 2011-2013 azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.exception.code;

/**
 * Azolla Exception Code
 * 
 * <p>XXX		:core code
 * <p>XXXXXX 	:component code
 * <p>XXXXXXXXX	:application code
 * 
 * <p>XXXXX		:For Azolla Component (^_^)
 * <p>XXXXXXXX	:For Azolla Application (^=^)
 *
 * @author 	sk@azolla.org
 * @version 1.0.0
 * @since 	ADK1.0
 */
public enum AzollaCode implements ExceptionCoder
{

	UNAZOLLA(000);	// This code is for exception out of Azolla

	private final int	code;

	/**
	 * This is a constructor
	 *
	 */
	private AzollaCode(int code)
	{
		this.code = code;
	}

	/**
	 * @see org.azolla.exception.code.ExceptionCoder#getCode()
	 * @return
	 */
	@Override
	public int getCode()
	{
		return code;
	}

}
