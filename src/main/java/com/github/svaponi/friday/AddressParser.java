package com.github.svaponi.friday;

import java.util.regex.Matcher;

import com.github.svaponi.friday.config.AddressParserConfigDto;

/**
 * Parses the unformatted addresses accordingly to the logic implemented by {@link AddressParserConfigDto}
 */
public class AddressParser
{

	private AddressParserConfigDto conf;

	public AddressParser(AddressParserConfigDto conf)
	{
		this.conf = conf;
	}

	/**
	 * Processes the unformatted address and extract the required components (street and number).
	 *
	 * @param address unformatted address
	 * @return String[] containing street and number ([0]: street, [1]: number)
	 */
	public String[] process(String address)
	{
		Matcher m = conf.regex.matcher(address);
		if (m.matches())
		{
			String number = m.group(conf.numberGroupIndex);
			String street = m.group(conf.streetGroupIndex);
			return new String[] { street, number };
		}

		return null;
	}
}
