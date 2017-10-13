package com.github.svaponi.friday.testdata;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

/**
 * The service lazily builds {@link TestDataDto} from a file.
 */
public class TestDataReader
{

	private File file;
	private List<TestDataDto> collection;

	/**
	 * Initializes a reader with the reference of the file containing data to build {@link TestDataDto}s.
	 *
	 * @param file
	 */
	public TestDataReader(File file)
	{
		this.file = file;
	}

	/**
	 * Reads data from the file and creates a {@link TestDataDto} for each line of the file.
	 *
	 * @return a list of {@link TestDataDto}
	 */
	public List<TestDataDto> data()
	{
		if (collection == null)
		{
			try
			{
				collection = FileUtils.readLines(file, "UTF-8").stream().filter(line -> {
					return line != null && !line.isEmpty();
				}).map(line -> {
					return line.split("\t");
				}).filter(values -> {
					return values.length >= 3;
				}).map(values -> {
					return new TestDataDto(values[0], values[1], values[2]);
				}).collect(Collectors.toList());
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return Collections.emptyList();
			}
		}
		return collection;
	}
}