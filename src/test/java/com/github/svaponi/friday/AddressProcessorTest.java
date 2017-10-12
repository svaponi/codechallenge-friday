package com.github.svaponi.friday;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.github.svaponi.friday.testdata.TestDataDto;
import com.github.svaponi.friday.testdata.TestDataReader;

/**
 * Main test class. To provide as much flexibility as possible we define our test-cases accordingly to configuration files.
 */
@RunWith(Parameterized.class)
public class AddressProcessorTest
{

	/*
		Test parameterization
	 */

	/**
	 * Inspects all files inside src/test/java/test-data and provide a collection of arguments needed to build our test-cases.
	 *
	 * @return collection of arguments to initialize all test-cases.
	 */
	@Parameterized.Parameters
	public static Collection<Object[]> data()
	{

		URL res = AddressProcessorTest.class.getClassLoader().getResource("");
		File dir = new File(res.getFile(), "test-data");
		if (dir.isDirectory())
		{
			return Arrays.asList(dir.listFiles()).stream().flatMap(child -> new TestDataReader(child).data().stream()).map(
				simpleTest -> new Object[] { simpleTest }).collect(Collectors.toList());
		}
		else
		{
			throw new IllegalStateException("Missing test-data directory");
		}
	}

	/*
		Test implementation
	 */

	private AddressProcessor client = new AddressProcessor();
	private TestDataDto test;

	public AddressProcessorTest(TestDataDto test)
	{
		this.test = test;
	}

	@Test
	public void test() throws Exception
	{
		String[] processedAddressComponents = client.process(test.unformattedAddress);
		verify(test.addressComponents, processedAddressComponents);
	}

	private void verify(String[] addressComponents, String[] processedAddressComponents)
	{
		boolean ok = Arrays.deepEquals(addressComponents, processedAddressComponents);
		System.out.printf("%-40s equals %-40s ? %s \n", format(addressComponents), format(processedAddressComponents), ok);
		Assert.assertTrue(ok);
	}

	private String format(String[] components)
	{
		return String.format("{\"%s\", \"%s\"}", components[0], components[1]);
	}

}