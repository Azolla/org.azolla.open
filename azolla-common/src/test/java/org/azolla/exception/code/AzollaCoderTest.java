/*
 * @(#)AzollaCoderTest.java		Created at 2013-2-24
 * 
 * Copyright (c) 2011-2013 azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.exception.code;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The coder is very lazy, nothing to write for this AzollaCoderTest class
 *
 * @author 	sk@azolla.org
 * @version 1.0.0
 * @since 	ADK1.0
 */
public class AzollaCoderTest
{

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
	}

	/**
	 * The coder is very lazy, nothing to write for this tearDown method
	 * 
	 * @throws java.lang.Exception void
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link org.azolla.exception.code.AzollaCode#getCode()}.
	 */
	@Test
	public void testGetCode()
	{
		Assert.assertEquals(AzollaCode.valueOf("UNAZOLLA"), AzollaCode.UNAZOLLA);
		Assert.assertSame(AzollaCode.valueOf("UNAZOLLA"), AzollaCode.UNAZOLLA);
		Assert.assertNotEquals(100, AzollaCode.UNAZOLLA);
		System.out.println(AzollaCode.UNAZOLLA.getCode());
	}

}
