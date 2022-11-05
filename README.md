# com.github.svaponi:friday:1.0

This project provide a solution for following code challenge. 

## CODE CHALLENGE

We kindly ask you to solve the following code challenge as part of our selection process.

### Addressline

An address provider returns addresses only with concatenated street names and numbers. Our own system on the other hand has separate fields for street name and street number.

Input: string of address

Output: string of street and string of street-number

1. Write a simple program that does the task for the most simple cases, e.g.
	- “Winterallee 3” -> {“ Winterallee”, “3”}
	- “Musterstrasse 45” -> { “Musterstrasse”, “45”}
	- “Blaufeldweg 123B” -> {“Blaufeldweg”, “123B”}

2. Consider more complicated cases
	- “Am Bächle 23” -> {“Am Bächle”, “23”}
	- “Auf der Vogelwiese 23 b” -> {“Auf der Vogelwiese”, ”23 b”}

3. BONUS: Consider other countries (complex cases)
	- “4, rue de la revolution” -> {"rue de la revolution", "4"}
	- “200 Broadway Av” -> {"Broadway Av", "200"}
	- “Calle Aduana, 29” -> {“Calle Aduana”, “29”}
	- “Calle 39 No 1540” -> {“Calle 39”, “No 1540”}

### Your Task:

Write a running application in the programming language of your choice including test cases and send the source code back to [***@friday.de](mailto:[***@friday.de).


## SOLUTION

Since we do not know in advance what structure the addresses we are dealing with might have, we provide a
solution as flexible as possible in order to handle all various cases that we might bump into.

The solution is based on **_Regular Expressions_**.

The basic idea is to process the unformatted address returned by the provider against a list of regexps: the first regex that has a match provides the solution!

Moreover, since the order of street and number in the unformatted address might be inverted respect what we want, each regex comes with a pair of indexes 
to map each group to the correct component of the address (street and number).

Regex and mappings are defined in `src/main/resources/AddressProcessor.tsv` (tab separated values file). Each line of the file define a specific configuration 
for `com.github.svaponi.friday.AddressParser`, and it is composed of 3 values separated by tabs:

1. Regex pattern
2. Index of the regex group containing the name of the street
3. Index of the regex group containing the street number

The order of the lines in the file correspond to the order of execution.

### Test

Test data are customizable as well. All `.tsv` files contained in `src/test/resources/test-data` are parsed to collect test data. Each line within 
the files is a test-case. Each line is composed of 3 values separated by tabs:

1. Unformatted address (value hypothetically coming from the address provider)
2. Correct street name
3. Correct street number

### How to

Execute this command to run tests:

```bash
$ mvn test
```
