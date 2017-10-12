package com.github.svaponi.friday.utils;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

/**
 * Utility to handle .tsv file
 */
public class TabSeparatedValuesFileUtil
{
	// Ignore lines starting with #
	private static final boolean ALLOW_COMMENTS = true;

	/**
	 * Loads the file into memory and converts every non-empty line in an array of String.
	 *
	 * @param file file to load
	 * @return a list of String[]. Each element of the list is a line of the file. Each element of the array is a value of the current line.
	 */
	public static List<String[]> loadValues(File file)
	{
		try
		{
			return FileUtils.readLines(file, "UTF-8").stream().filter(line -> {
				return line != null && !line.isEmpty() && !(ALLOW_COMMENTS && line.startsWith("#"));
			}).map(line -> {
				return line.split("\t");
			}).collect(Collectors.toList());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	/**
	 * Loads the file located by path into memory and converts every non-empty line in an array of String.
	 *
	 * @param path locates the file to load
	 * @return a list of String[]. Each element of the list is a line of the file. Each element of the array is a value of the current line.
	 */
	public static List<String[]> loadValues(String path)
	{
		File file = lookForFile(path);
		return loadValues(file);
	}

	private static File lookForFile(String path)
	{
		if (path == null || path.isEmpty())
		{
			throw new IllegalArgumentException("Null path");
		}
		if (!path.toLowerCase().endsWith(".tsv"))
		{
			throw new IllegalArgumentException("Unsupported format [" + path + "]");
		}
		URL res = TabSeparatedValuesFileUtil.class.getClassLoader().getResource(path);
		if (res == null)
		{
			throw new IllegalArgumentException("Missing file [" + path + "]");
		}
		File file = new File(res.getFile());
		if (!file.exists())
		{
			throw new IllegalArgumentException("Missing file [" + file.getPath() + "]");
		}
		else if (file.isDirectory())
		{
			throw new IllegalArgumentException("Not a file [" + file.getPath() + "]");
		}
		return file;
	}
}