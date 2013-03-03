/*
 * @(#)ModelHelper.java		Created at 2013-2-24
 * 
 * Copyright (c) 2011-2013 azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.model;

import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.xml.bind.JAXBContext;

import org.azolla.exception.AzollaException;
import org.azolla.exception.code.AzollaCode;

import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Help for model
 *
 * @author 	sk@azolla.org
 * @version 1.0.0
 * @since 	ADK1.0
 */
public class XmlConfigures
{
	private static final LoadingCache<Class<?>, JAXBContext>	CACHE	= CacheBuilder.newBuilder().softValues()
																				.build(new ModelLoader());

	private static class ModelLoader extends CacheLoader<Class<?>, JAXBContext>
	{

		/**
		 * @see com.google.common.cache.CacheLoader#load(java.lang.Object)
		 * @param key
		 * @return
		 * @throws ExceptionF
		 */
		@Override
		public JAXBContext load(Class<?> key) throws Exception
		{
			return JAXBContext.newInstance(key);
		}
	}

	private static JAXBContext getJAXBContext(Class<?> clazz) throws ExecutionException
	{
		return CACHE.get(clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(Class<T> clazz, String filePath)
	{
		try
		{
			return (T) getJAXBContext(clazz).createUnmarshaller().unmarshal(new File(Strings.nullToEmpty(filePath)));
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public static <T> void marshal(T t, String filePath)
	{
		try
		{
			getJAXBContext(t.getClass()).createMarshaller().marshal(t, new File(Strings.nullToEmpty(filePath)));
		}
		catch(Exception e)
		{
			throw new AzollaException(AzollaCode.MODELHELPER_MARSHAL, e).set("t", t).set("filePath", filePath);
		}
	}
}
