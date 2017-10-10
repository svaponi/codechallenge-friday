package com.github.svaponi.friday;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CodeChallengeTest
{

	private CodeChallenge client = new CodeChallenge();

	static int MAX_LENGTH;

	static {
		int simpleMax = TestData.simple.stream().mapToInt(t->t.address.length()).max().getAsInt();
		int complicatedMax = TestData.complicated.stream().mapToInt(t->t.address.length()).max().getAsInt();
		int complexMax = TestData.complex.stream().mapToInt(t->t.address.length()).max().getAsInt();
		MAX_LENGTH = Math.max(simpleMax, Math.max(complicatedMax, complexMax)) + 7;
	}

	@Test
	public void testSimple() throws Exception
	{
		TestData.simple.stream().forEach(test -> {
			String[] processedAddressComponents = client.process(test.address);
			verify(test.addressComponents, processedAddressComponents);
		});
	}

	@Test
	public void testComplicated() throws Exception
	{
		TestData.complicated.stream().forEach(test -> {
			String[] processedAddressComponents = client.process(test.address);
			verify(test.addressComponents, processedAddressComponents);
		});
	}

	@Test
	public void testComplex() throws Exception
	{
		TestData.complex.stream().forEach(test -> {
			String[] processedAddressComponents = client.process(test.address);
			verify(test.addressComponents, processedAddressComponents);
		});
	}

	private void verify(String[] addressComponents, String[] processedAddressComponents)
	{
		boolean ok = Arrays.deepEquals(addressComponents, processedAddressComponents);
		System.out.printf("%-" + MAX_LENGTH + "s equals %-" + MAX_LENGTH + "s ? %s \n", format(addressComponents), format(processedAddressComponents), ok);
		Assert.assertTrue(ok);
	}

	private String format(String[] components)
	{
		return String.format("{\"%s\", \"%s\"}", components[0], components[1]);
	}

}