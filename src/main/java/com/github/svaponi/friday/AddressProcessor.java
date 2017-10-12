package com.github.svaponi.friday;

import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import com.github.svaponi.friday.config.AddressProcessorConfigDto;
import com.github.svaponi.friday.utils.TabSeparatedValuesFileUtil;

/**
 * The processor parse unformatted addresses to provide a meaningful components (Street and Number)
 */
public class AddressProcessor
{
	/*
		Since we do not know what structure might have addresses around the world, we provide a
		flexible and configurable solution to handle various cases.

		The solution is based on Regular Expression.

		The basic idea is to process the unformatted address against an ordered list of Regular Expressions,
		each one with a specific mapping to extract street and number from result groups.

		The first RegEx that matches provide the solution.

		RegExs and mappings are configurable from src/main/resources/config.tsv (tab separated values)
	 */

	private List<AddressProcessorConfigDto> configs;

	public AddressProcessor()
	{
		this.configs = TabSeparatedValuesFileUtil.loadValues(getClass().getSimpleName() + ".tsv").stream().map(values -> {
			return new AddressProcessorConfigDto(values[0], Short.parseShort(values[1]), Short.parseShort(values[2]));
		}).collect(Collectors.toList());
	}

	/**
	 * Processes the unformatted address and extract the required components (street and number).
	 *
	 * @param address unformatted address
	 * @return String[] containing street and number ([0]: street, [1]: number)
	 */
	public String[] process(String address)
	{

		String[] solution = null;
		Matcher m;

		for (AddressProcessorConfigDto conf : configs)
		{
			m = conf.regex.matcher(address);
			if (m.matches())
			{
				String number = m.group(conf.numberGroupIndex);
				String street = m.group(conf.streetGroupIndex);
				solution = new String[] { street, number };
				break;
			}
		}

		if (solution == null)
		{
			solution = new String[] { "?", "?" };
		}

		return solution;
	}
}
