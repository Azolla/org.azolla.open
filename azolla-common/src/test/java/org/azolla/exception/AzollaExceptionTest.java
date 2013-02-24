/*
 * @(#)AzollaExceptionTest.java		Created at 2013-2-24
 * 
 * Copyright (c) 2011-2013 azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.exception;

import org.azolla.exception.code.AzollaCode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Strings;

/**
 * The coder is very lazy, nothing to write for this AzollaExceptionTest class
 *
 * @author 	sk@azolla.org
 * @version 1.0.0
 * @since 	ADK1.0
 */
public class AzollaExceptionTest
{
	private AzollaException	ae;

	private final String	testKey		= "testKey";
	private final String	testValue	= "testValue";

	public RuntimeException runtimeException()
	{
		return new RuntimeException();
	}

	/**
	 * The coder is very lazy, nothing to write for this setUpBeforeClass method
	 * 
	 * @throws java.lang.Exception void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * The coder is very lazy, nothing to write for this tearDownAfterClass method
	 * 
	 * @throws java.lang.Exception void
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	/**
	 * The coder is very lazy, nothing to write for this setUp method
	 * 
	 * @throws java.lang.Exception void
	 */
	@Before
	public void setUp() throws Exception
	{
		ae = AzollaException.wrap(new RuntimeException()).set(testKey, testValue);
	}

	/**
	 * The coder is very lazy, nothing to write for this tearDown method
	 * 
	 * @throws java.lang.Exception void
	 */
	@After
	public void tearDown() throws Exception
	{
		if(null != ae)
		{
			//			ae.printStackTrace();
			System.out.println(ae.getCode());
		}
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#AzollaException(org.azolla.exception.code.ExceptionCoder)}.
	 */
	@Test(expected = AzollaException.class)
	public void testAzollaExceptionExceptionCoder()
	{
		Throwable t = null;
		new AzollaException(null, t);
		throw new AzollaException(AzollaCode.UNAZOLLA);
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#AzollaException(org.azolla.exception.code.ExceptionCoder, java.lang.String)}.
	 */
	@Test(timeout = 100)
	public void testAzollaExceptionExceptionCoderString()
	{
		//nothing
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#AzollaException(org.azolla.exception.code.ExceptionCoder, java.lang.Throwable)}.
	 */
	@Ignore("Not yet implemented")
	@Test
	public void testAzollaExceptionExceptionCoderThrowable()
	{
		//ignored
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#AzollaException(org.azolla.exception.code.ExceptionCoder, java.lang.String, java.lang.Throwable)}.
	 */
	@Ignore("Not yet implemented")
	@Test
	public void testAzollaExceptionExceptionCoderStringThrowable()
	{
		//ignored
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#wrap(java.lang.Throwable)}.
	 */
	@Test
	public void testWrapThrowable()
	{
		AzollaException.wrap(runtimeException());
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#wrap(java.lang.Throwable, org.azolla.exception.code.ExceptionCoder)}.
	 */
	@Test
	public void testWrapThrowableExceptionCoder()
	{
		//		AzollaException.wrap(null, null);
		AzollaException ae1 = AzollaException.wrap(new RuntimeException(), null);
		AzollaException ae2 = AzollaException.wrap(new AzollaException(null, null, null), null);
		AzollaException.wrap(runtimeException(), AzollaCode.UNAZOLLA);
		System.out.println(ae1);
		System.out.println(ae2);
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#printStackTrace(java.io.PrintStream)}.
	 */
	@Test
	public void testPrintStackTracePrintStream()
	{
		//		ae.printStackTrace(pw);
		ae.printStackTrace();
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#printStackTrace(java.io.PrintWriter)}.
	 */
	@Test
	public void testPrintStackTracePrintWriter()
	{
		ae.printStackTrace();
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#get(java.lang.String)}.
	 */
	@Test
	public void testGet()
	{
		Assert.assertEquals(testValue, ae.get(testKey));
		Assert.assertSame(testValue, ae.get(testKey));
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#set(java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testSet()
	{
		//		ae.set(null, null);	//error
		ae.set("", null);
		System.out.println(ae.get(""));
		ae.set(Strings.nullToEmpty(null), "");
		System.out.println(ae.get(""));
		System.out.println(Strings.nullToEmpty(null));
		System.out.println(ae.get(""));
		ae.set(testKey, testValue);
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#getCode()}.
	 */
	@Test
	public void testGetCode()
	{
		Assert.assertEquals(AzollaCode.AZOLLA, ae.getCode());
		Assert.assertSame(AzollaCode.AZOLLA, ae.getCode());
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#setCode(org.azolla.exception.code.ExceptionCoder)}.
	 */
	@Test
	public void testSetCode()
	{
		ae.setCode(AzollaCode.UNAZOLLA);
	}

	/**
	 * Test method for {@link org.azolla.exception.AzollaException#getProperties()}.
	 */
	@Test
	public void testGetProperties()
	{
		ae.getProperties();
	}
}
