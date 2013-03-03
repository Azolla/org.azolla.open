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
 * <p>[11,99]				:For Azoll Core
 * <p>[10001,99999]			:For Azolla Component
 * <p>[10000001,99999999]	:For Azolla Application
 *
 * @author 	sk@azolla.org
 * @version 1.0.0
 * @since 	ADK1.0
 */
public enum AzollaCode implements ErrorCoder
{
	/** This code(0) is for exception out of Azolla */
	UNAZOLLA(0),

	/** This code(11) is for exception AzollaException Error, It like your feet */
	AZOLLA(11),
	NULL(12),

	/** [11000,11999] : For azolla-common*/
	MODELHELPER_MARSHAL(11001);

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
	 * @see org.azolla.exception.code.ErrorCoder#getCode()
	 * @return
	 */
	@Override
	public int getCode()
	{
		return code;
	}

}
