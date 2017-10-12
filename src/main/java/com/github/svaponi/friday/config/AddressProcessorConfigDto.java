package com.github.svaponi.friday.config;

import java.util.regex.Pattern;

/**
 * Data Transfer Object to hold the logic to parse inputs.
 */
public class AddressProcessorConfigDto
{
	// We use public static fields for convenience

	/**
	 * Regex to extract components from input
	 */
	public final Pattern regex;

	/**
	 * Index of the regex matcher group containing the Street
	 */
	public final short streetGroupIndex;

	/**
	 * Index of the regex matcher group containing the Number
	 */
	public final short numberGroupIndex;

	public AddressProcessorConfigDto(String pattern, short streetGroupIndex, short numberGroupIndex)
	{
		this.regex = Pattern.compile(pattern);
		this.streetGroupIndex = streetGroupIndex;
		this.numberGroupIndex = numberGroupIndex;
	}
}