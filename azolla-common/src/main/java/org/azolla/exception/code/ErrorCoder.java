/*
 * @(#)ExceptionCoder.java		Created at 2013-2-23
 * 
 * Copyright (c) 2011-2013 azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.exception.code;

/**
 * Exception Code
 * 
 * <p>[101,999]				:Third core code
 * <p>[100001,999999] 		:Third Component code
 * <p>[100000001,999999999]	:Third Application code
 * 
 * <p>[11,99]				:For Azoll Core
 * <p>[10001,99999]			:For Azolla Component
 * <p>[10000001,99999999]	:For Azolla Application
 * 
 * @author 	sk@azolla.org
 * @version 1.0.0
 * @since 	ADK1.0
 */
public interface ErrorCoder
{
	int getCode();
}
