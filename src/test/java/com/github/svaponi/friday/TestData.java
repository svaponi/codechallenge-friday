package com.github.svaponi.friday;

import java.util.Arrays;
import java.util.List;

public class TestData
{

	static List<Test> simple = Arrays.asList( //
		new Test("Winterallee 3", "Winterallee", "3"), //
		new Test("Musterstrasse 45", "Musterstrasse", "45"), //
		new Test("Blaufeldweg 123B", "Blaufeldweg", "123B"));

	static List<Test> complicated = Arrays.asList( //
		new Test("Am Bächle 23", "Am Bächle", "23"), //
		new Test("Auf der Vogelwiese 23 b", "Auf der Vogelwiese", "23 b"));

	static List<Test> complex = Arrays.asList( //
		new Test("4, rue de la revolution", "rue de la revolution", "4"), //
		new Test("200 Broadway Av", "Broadway Av", "200"), //
		new Test("Calle Aduana, 29", "Calle Aduana", "29"), //
		new Test("Calle 39 No 1540", "Calle 39", "No 1540"));

	static class Test
	{
		final String address;
		final String[] addressComponents;

		public Test(String address, String street, String number)
		{
			this.address = address;
			this.addressComponents = new String[] { street, number };
		}
	}

}