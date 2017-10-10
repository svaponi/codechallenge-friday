package com.github.svaponi.friday;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service to handle the challenge
 */
public class CodeChallenge
{
	/*
		Zeichen     Unicode
	------------------------------
		Ä, ä        \u00c4, \u00e4
		Ö, ö        \u00d6, \u00f6
		Ü, ü        \u00dc, \u00fc
		ß           \u00df
	*/

	private static Pattern CASE1 = Pattern.compile("^([\\d]+)[,]?[\\s](.*)$");
	private static Pattern CASE2 = Pattern.compile("^([a-z\\u00e4\\u00f6\\u00fcA-Z\\u00c4\\u00d6\\u00dc\\u00df\\s\\d]+)[\\s](No\\s.*)$");
	private static Pattern CASE3 = Pattern.compile("^([a-z\\u00e4\\u00f6\\u00fcA-Z\\u00c4\\u00d6\\u00dc\\u00df\\s]+)[,]?[\\s]([\\d].*)$");

	/**
	 * The method processes the unformatted address and extract the required components.
	 *
	 * @param address unformated address
	 * @return String[] 1: Street, 2: Number
	 */
	public String[] process(String address)
	{

		String[] solution = null;
		boolean found = false;
		Matcher m;

		m = CASE1.matcher(address);
		if (m.matches())
		{
			solution = new String[] { m.group(2), m.group(1) };
			found = true;
		}

		if (!found)
		{
			m = CASE2.matcher(address);
			if (m.matches())
			{
				solution = new String[] { m.group(1), m.group(2) };
				found = true;
			}
		}

		if (!found)
		{
			m = CASE3.matcher(address);
			if (m.matches())
			{
				solution = new String[] { m.group(1), m.group(2) };
				found = true;
			}
		}

		if (!found)
		{
			solution = new String[2];
		}

		return solution;
	}
}
