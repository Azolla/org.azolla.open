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
 * 文件帮助类
 * 
 * 文件对象：即Java中的File,可以是文件文件也可以是文件夹
 * 文件：属于Java中的File,但不包含文件夹
 * 文件夹：属于Java中的File,可包含文件
 * 文件路径：是Java中的String,是文件在磁盘上的路径字符串
 * 文件夹路径：是Java中的String,是文件夹在磁盘上的路径字符串
 *
 * @author 	sk@azolla.org
 * @version 1.0.0
 * @since 	ADK1.0
 */
public class FileHelper
{
	public static final String	ILLEGAL_FILENAME_REGEX	= "[{/\\\\:*?\"<>|}]";

	/**
	 * 根据文件对象获得其下所有文件路径(包括子文件夹的文件路径)
	 * 
	 * 下列情况返回空列表
	 * 1.文件对象在磁盘上不存在
	 * 2.文件对象为空文件夹
	 * 
	 * 如果文件对象为文件则返回包含该文件路径的文件路径列表
	 * 
	 * @param 	file			文件对象
	 * @return 	List<String>	文件路径列表(不包含文件夹路径)
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
	 * 根据文件路径获得其下所有文件路径(包括子文件夹的文件路径)
	 * 
	 * 下列情况返回空列表
	 * 1.文件路径在磁盘上不存在
	 * 2.文件路径为空文件夹
	 * 
	 * 如果文件路径为文件则返回包含该文件路径的文件路径列表
	 * 
	 * @param 	path			文件路径
	 * @return 	List<String>	文件路径列表(不包含文件夹路径)
	 */
	public static List<String> allFilePaths(String path)
	{
		return allFilePaths(new File(path));
	}

	/**
	 * 根据文件对象获得其下所有文件(包括子文件夹的文件)
	 * 
	 * 下列情况返回空列表
	 * 1.文件对象在磁盘上不存在
	 * 2.文件对象为空文件夹
	 * 
	 * 如果文件对象为文件则返回包含该文件的文件列表
	 * 
	 * @param 	file		文件对象
	 * @return 	List<File>	文件列表
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
	 * 根据文件路径获得其下所有文件(包括子文件夹的文件)
	 * 
	 * 下列情况返回空列表
	 * 1.文件路径在磁盘上不存在
	 * 2.文件路径为空文件夹
	 * 
	 * 如果文件路径为文件则返回包含该文件的文件列表
	 * 
	 * @param 	path		文件路径
	 * @return 	List<File> 	文件列表
	 */
	public static List<File> allFiles(String path)
	{
		return allFiles(new File(path));
	}

	/**
	 * 根据文件对象删除其下所有空文件(包括子文件夹的空文件)
	 * 
	 * 如果文件对象为文件,且为空,则删除该文件
	 * 
	 * @param 	file	文件对象
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
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
	 * 根据文件路径删除其下所有空文件(包括子文件夹的空文件)
	 * 
	 * 如果文件路径为文件,且为空,则删除该文件
	 * 
	 * @param 	path	文件路径
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
	 */
	public static boolean delAllEmptyFiles(String path)
	{
		return delAllEmptyFiles(new File(path));
	}

	/**
	 * 根据文件对象删除其下所有文件(包括子文件夹的文件)
	 * 
	 * 如果文件对象为文件,则删除该文件
	 * 
	 * @param 	file	文件对象
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
	 */
	public static boolean delAllFiles(File file)
	{
		Preconditions.checkNotNull(file);
		boolean ret = true;
		if(file.exists())
		{
			if(file.isDirectory() && file.list() != null)
			{
				for(File f : file.listFiles())
				{
					ret = ret && delAllFiles(f);
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
	 * 根据文件路径删除其下所有文件(包括子文件夹的文件)
	 * 
	 * 如果文件路径为文件,则删除该文件
	 * 
	 * @param 	path	文件路径
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
	 */
	public static boolean delAllFiles(String path)
	{
		return delAllFiles(new File(path));
	}

	/**
	 * 删除文件对象包括其下所有文件对象
	 * 
	 * @param 	dir		文件对象
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
	 */
	public static boolean delDir(File dir)
	{
		Preconditions.checkNotNull(dir);
		boolean ret = true;
		if(dir.exists())
		{
			if(dir.isDirectory() && dir.list() != null)
			{
				for(File f : dir.listFiles())
				{
					ret = ret && delDir(f);
				}
				ret = ret && dir.delete();
			}
			else
			{
				ret = ret && dir.delete();
			}
		}
		return ret;
	}

	/**
	 * 删除文件路径包括其下所有文件对象
	 * 
	 * @param 	dir		文件路径
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
	 */
	public static boolean delDir(String dir)
	{
		return delDir(new File(dir));
	}

	/**
	 * 根据文件对象删除其下所有空文件(不包括子文件夹的空文件)
	 * 
	 * 如果文件对象为文件,且为空,则删除该文件
	 * 
	 * @param 	file	文件对象
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
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
	 * 根据文件路径删除其下所有空文件(不包括子文件夹的空文件)
	 * 
	 * 如果文件路径为文件,且为空,则删除该文件
	 * 
	 * @param 	path	文件路径
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
	 */
	public static boolean delEmptyFiles(String path)
	{
		return delEmptyFiles(new File(path));
	}

	/**
	 * 根据文件对象删除其下所有文件(不包括子文件夹的文件)
	 * 
	 * 如果文件对象为文件,则删除该文件
	 * 
	 * @param 	file	文件对象
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
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
	 * 
	 * 根据文件路径删除其下所有文件(不包括子文件夹的文件)
	 * 
	 * 如果文件路径为文件,则删除该文件
	 * 
	 * @param 	path	文件路径
	 * @return 	boolean	结果:全部删除成功为true,如有失败为false
	 */
	public static boolean delFiles(String path)
	{
		return delFiles(new File(path));
	}

	/**
	 * 根据文件类型及文件对象列表获得其下所有属于该类型的文件(包括子文件夹的文件)
	 * 
	 * @param 	fileType	文件类型
	 * @param 	files		文件对象列表
	 * @return 	List<File>	指定文件类型的文件列表
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
	 * 根据文件类型及文件路径获得其下所有属于该类型的文件(包括子文件夹的文件)
	 * 
	 * @param 	fileType	文件类型
	 * @param 	path		文件路径
	 * @return 	List<File>	指定文件类型的文件列表
	 */
	public static List<File> getFilesByType(String fileType, String path)
	{
		return getFilesByType(fileType, allFiles(path));
	}

	/**
	 * 根据文件对象获得文件类型(test.txt->txt)
	 * 
	 * @param 	file	文件对象
	 * @return 	String	文件类型
	 */
	public static String getFileType(File file)
	{
		Preconditions.checkNotNull(file);
		try
		{
			return getFileType(file.getCanonicalPath());
		}
		catch(IOException e)
		{
			return getFileType(file.getAbsolutePath());
		}
	}

	/**
	 * 
	 * 根据文件路径获得文件类型(test.txt->txt)
	 * 
	 * @param 	filePath	文件路径
	 * @return 	String		文件类型
	 */
	public static String getFileType(String filePath)
	{
		Preconditions.checkNotNull(filePath);
		int lastPointIndex = filePath.lastIndexOf(".");
		return filePath.substring(lastPointIndex + 1);
	}

	/**
	 * 非法文件名转换为合法文件名,非法字符用"_"替代
	 * 
	 * @param 	fileName	可能含非法字符的文件名
	 * @return 	String		不含非法字符的文件名
	 */
	public static String toLegalFileName(String fileName)
	{
		return toLegalFileName(fileName, "_");
	}

	/**
	 * 非法文件名转换为合法文件名,非法字符用合法字符({@code legalString})替代
	 * 
	 * @param 	fileName	可能含非法字符的文件名
	 * @param 	legalString	合法字符
	 * @return 	String		不含非法字符的文件名
	 */
	public static String toLegalFileName(String fileName, String legalString)
	{
		Preconditions.checkNotNull(fileName);
		return fileName.replaceAll(ILLEGAL_FILENAME_REGEX, legalString);
	}
}
