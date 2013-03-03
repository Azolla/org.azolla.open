/*
 * @(#)FileHelper.java		Created at 2013-2-24
 * 
 * Copyright (c) 2011-2013 azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Help for file
 * 
 * The concept about some object:
 * 1.file/path		:<code>File</code>/<code>String</code> object, maybe document,maybe directory and maybe null.
 * 2.document		:<code>File</code>/<code>String</code> object, but it does not contain directory
 * 3.directory		:<code>File</code>/<code>String</code> object, just directory
 * 
 *
 * @author 	sk@azolla.org
 * @version 1.0.0
 * @since 	ADK1.0
 */
public class FileHelper
{
	public static final String	ILLEGAL_FILENAME_REGEX	= "[{/\\\\:*?\"<>|}]";

	/**
	 * @see org.azolla.io.FileHelper#allFiles(File)
	 */
	public static List<String> allFilePaths(File file)
	{
		return Lists.transform(allFiles(file), new Function<File, String>()
			{
				@Override
				public String apply(File input)
				{
					try
					{
						return input.getCanonicalPath();
					}
					catch(IOException e)
					{
						return input.getAbsolutePath();
					}
				}
			});
	}

	/**
	 * @see org.azolla.io.FileHelper#allFilePaths(File)
	 */
	public static List<String> allFilePaths(String path)
	{
		return allFilePaths(new File(path));
	}

	/**
	 * Return all document under this directory and sub directory.
	 * if the file is document return it.
	 * 
	 * Return empty when:
	 * 1.file not exist
	 * 2.file is an empty directory
	 * 
	 * Throw exception with the file is null.
	 * 
	 * @param file document or directory
	 * @return the container with document
	 */
	public static List<File> allFiles(File file)
	{
		Preconditions.checkNotNull(file);
		List<File> allFiles = Lists.newArrayList();
		if(file.exists())
		{
			if(file.isDirectory() && file.list() != null)
			{
				for(File f : file.listFiles())
				{
					allFiles.addAll(allFiles(f));
				}
			}
			else
			{
				allFiles.add(file);
			}
		}
		return allFiles;
	}

	/**
	 * @see org.azolla.io.FileHelper#allFiles(File)
	 */
	public static List<File> allFiles(String path)
	{
		return allFiles(new File(path));
	}

	/**
	 * Delete all empty document under this directory and sub directory.
	 * if the file is empty document delete it.
	 * 
	 * return false when some empty document delete failure,but delete is go on
	 * 
	 * @param file document or directory
	 * @return will return true with out false when some empty document delete failure
	 */
	public static boolean delAllEmptyFiles(File file)
	{
		Preconditions.checkNotNull(file);
		boolean ret = true;
		if(file.exists())
		{
			if(file.isDirectory() && file.list() != null)
			{
				for(File f : file.listFiles())
				{
					ret = ret && delAllEmptyFiles(f);
				}
			}
			else
			{
				if(file.length() == 0)
				{
					ret = ret && file.delete();
				}
			}
		}
		return ret;
	}

	/**
	 * @see org.azolla.io.FileHelper#delAllEmptyFiles(File)
	 */
	public static boolean delAllEmptyFiles(String path)
	{
		return delAllEmptyFiles(new File(path));
	}

	/**
	 * Delete file sub files and  under this directory and sub directory.
	 * if this file is document or empty directory delete it.
	 * 
	 * @param file document or directory
	 * @return will return true with out false when some file delete failure
	 */
	public static boolean delDirectory(File file)
	{
		Preconditions.checkNotNull(file);
		boolean ret = true;
		if(file.exists())
		{
			if(file.isDirectory() && file.list() != null)
			{
				for(File f : file.listFiles())
				{
					ret = ret && delDirectory(f);
				}
				ret = ret && file.delete();
			}
			else
			{
				ret = ret && file.delete();
			}
		}
		return ret;
	}

	/**
	 * @see org.azolla.io.FileHelper#delDirectory(File)
	 */
	public static boolean delDirectory(String path)
	{
		return delDirectory(new File(path));
	}

	/**
	 * Delete all empty document under this directory.
	 * if the file is empty document delete it.
	 * 
	 * return false when some empty document delete failure,but delete is go on
	 * 
	 * @param file document or directory
	 * @return will return true with out false when some empty document delete failure
	 */
	public static boolean delEmptyFiles(File file)
	{
		Preconditions.checkNotNull(file);
		boolean ret = true;
		if(file.exists())
		{
			if(file.isDirectory() && file.list() != null)
			{
				for(File f : file.listFiles())
				{
					if(!f.isDirectory())
					{
						if(f.length() == 0)
						{
							ret = ret && f.delete();
						}
					}
				}
			}
			else
			{
				if(file.length() == 0)
				{
					ret = ret && file.delete();
				}
			}
		}
		return ret;
	}

	/**
	 * @see org.azolla.io.FileHelper#delEmptyFiles(File)
	 */
	public static boolean delEmptyFiles(String path)
	{
		return delEmptyFiles(new File(path));
	}

	/**
	 * Delete all document under this directory.
	 * if the file is document delete it.
	 * 
	 * @param file document or directory
	 * @return will return true with out false when some empty document delete failure
	 */
	public static boolean delFiles(File file)
	{
		Preconditions.checkNotNull(file);
		boolean ret = true;
		if(file.exists())
		{
			if(file.isDirectory() && file.list() != null)
			{
				for(File f : file.listFiles())
				{
					if(!f.isDirectory())
					{
						ret = ret && f.delete();
					}
				}
			}
			else
			{
				ret = ret && file.delete();
			}
		}
		return ret;
	}

	/**
	 * @see org.azolla.io.FileHelper#delFiles(File)
	 */
	public static boolean delFiles(String path)
	{
		return delFiles(new File(path));
	}

	/**
	 * filter files by file type.
	 * 
	 * @param fileType file type
	 * @param files	file list
	 * @return  file list of the file end with file type
	 */
	public static List<File> getFilesByType(String fileType, List<File> files)
	{
		Preconditions.checkNotNull(files);
		List<File> fileList = Lists.newArrayList();
		if(Strings.isNullOrEmpty(fileType))
		{
			return fileList;
		}
		for(File f : files)
		{
			if(!f.isDirectory())
			{
				if(fileType.equalsIgnoreCase(getFileType(f)))
				{
					fileList.add(f);
				}
			}
		}
		return fileList;
	}

	/**
	 * @see org.azolla.io.FileHelper#getFilesByType(String, List<File>)
	 * @see org.azolla.io.FileHelper#allFiles(String)
	 */
	public static List<File> getFilesByType(String fileType, String path)
	{
		return getFilesByType(fileType, allFiles(path));
	}

	/**
	 * @see org.azolla.io.FileHelper#getFileType(String)
	 */
	public static String getFileType(File file)
	{
		Preconditions.checkNotNull(file);
		return getFileType(file.getName());
	}

	/**
	 * return type of file by file name
	 * example:test.txt -> txt
	 * 
	 * @param fileName file name
	 * @return type of file
	 */
	public static String getFileType(String fileName)
	{
		Preconditions.checkNotNull(fileName);
		int lastPointIndex = fileName.lastIndexOf(".");
		if(-1 == lastPointIndex)
		{
			return fileName;
		}
		else
		{
			return fileName.substring(lastPointIndex + 1);
		}
	}

	/**
	 * @see org.azolla.io.FileHelper#toLegalFileName(String, String)
	 */
	public static String toLegalFileName(String fileName)
	{
		return toLegalFileName(fileName, null);
	}

	/**
	 * transform legal file name
	 * 
	 * @param fileName file name
	 * @param legalString legal string, if null or empty to "_"
	 * @return legal file name
	 */
	public static String toLegalFileName(String fileName, String legalString)
	{
		Preconditions.checkNotNull(fileName);
		return fileName.replaceAll(ILLEGAL_FILENAME_REGEX, Strings.isNullOrEmpty(legalString) ? "_" : legalString);
	}
}
