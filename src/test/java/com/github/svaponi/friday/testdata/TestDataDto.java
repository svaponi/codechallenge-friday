package com.github.svaponi.friday.testdata;

/**
 * Data Transfer Object to hold test data.
 */
public class TestDataDto
{
	// We use public static fields for convenience

	/**
	 * Test unformattedAddress (address to process)
	 */
	public final String unformattedAddress;

	/**
	 * Expected result of process ([0]: street, [1]: number)
	 */
	public final String[] addressComponents;

	public TestDataDto(String address, String street, String number)
	{
		this.unformattedAddress = address;
		this.addressComponents = new String[] { street, number };
	}
}