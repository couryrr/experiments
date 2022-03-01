package com.dappercloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StringCalculatorTest {

	@Test
	public void addEmptyStringTest() throws Exception {

		int result = StringCalculator.addString("");
		assertEquals("String input blank is 0", 0, result);
	}

	@Test
	public void addEmptyWhiteSpaceStringTest() throws Exception {

		int result = StringCalculator.addString("  ");
		assertEquals("String input blank is 0", 0, result);
	}

	@Test
	public void addSingleStringTest() throws Exception {
		int result = StringCalculator.addString("5");
		assertEquals("String input 5 is 5", 5, result);
	}

	@Test
	public void addTwoStringTest() throws Exception {
		int result = StringCalculator.addString("5,2");
		assertEquals("String input 5,2 is 7", 7, result);
	}

	@Test
	public void addTwoFirstEmptyStringTest() throws Exception {
		int result = StringCalculator.addString(",2");
		assertEquals("String input ,2 is 2", 2, result);
	}

	@Test
	public void addTwoSecondEmptyStringTest() throws Exception {
		int result = StringCalculator.addString("5,");
		assertEquals("String input 5, is 5", 5, result);
	}

	@Test
	public void addMultipleNumbersStringTest() throws Exception {
		int result = StringCalculator.addString("5,2,3");
		assertEquals("String input 5,2,3 is 10", 10, result);
	}

	@Test
	public void addMultipleNumbersNewLineStringTest() throws Exception {
		int result = StringCalculator.addString("5\n2\n3");
		assertEquals("String input 5,2,3 is 10", 10, result);
	}

	@Test
	public void addMultipleNumbersNewLineAndCommaStringTest() throws Exception {
		int result = StringCalculator.addString("5,2\n3");
		assertEquals("String input 5,2,3 is 10", 10, result);
	}

	@Test
	public void addMultipleNumbersNewLineAndCommaAndCustomStringTest() throws Exception {
		int result = StringCalculator.addString("//[;]\n5,2\n3;2,3");
		assertEquals("String input 5,2,3,2,3 is 15", 15, result);
	}

	@Test
	public void addWithNegatveNumbersNewLineAndCommaAndCustomStringTest(){
		try {
			StringCalculator.addString("//[;]\n5,2\n3;-2,3");
			fail("Expected to have an exception here");

		} catch (Exception e) {
			assertEquals("There should be an exception", "Negatives not allowed [-2]",e.getMessage());
		}
	}
	
	@Test
	public void addWithMultipleNegatveNumbersTest(){
		try {
			StringCalculator.addString(",2\n3,-2,3,-6,8");
			fail("Expected to have an exception here");

		} catch (Exception e) {
			assertEquals("There should be an exception", "Negatives not allowed [-2,-6]",e.getMessage());
		}
	}
	
	@Test
	public void addWithNoNegatveNumbersNewLineCommaAndCustomStringTest(){
		try {
			int result = StringCalculator.addString("//[;]\n5,2\n3;2,3");
			assertEquals("5+2+3+2+3", 15,result);

		} catch (Exception e) {
			fail("Do not expecte to have an exception here");
		}
	}
	
	@Test
	public void addSkips1001NumberTest() throws Exception {
		int result = StringCalculator.addString("5,1001\n3\n2,3");
		assertEquals("5+3+2+3", 13,result);
	}
	
	@Test
	public void addAllow1000NumbersTest() throws Exception {
		int result = StringCalculator.addString("5,1000\n3,2,3");
		assertEquals("5+1000+3+2+3", 1013,result);
	}

	@Test
	public void parseEmptyTest() {
		int result = StringCalculator.parse("");
		assertEquals("String input blank is 0", 0, result);
	}

	@Test
	public void parseEmptyWhiteSpaceTest() {
		int result = StringCalculator.parse(" ");
		assertEquals("String input white space is 0", 0, result);
	}

	@Test
	public void parseNumberTest() {
		int result = StringCalculator.parse("5");
		assertEquals("String input 5 is 5", 5, result);
	}

	@Test
	public void parseNegativeNumberTest() {
		int result = StringCalculator.parse("-5");
		assertEquals("String input -5 is -5", -5, result);
	}

	@Test
	public void splitEmptyStringTest() {
		String[] result = StringCalculator.split("","[\\n,]");
		assertEquals("Empty input will have a size of 1", 1, result.length);
		assertEquals("Empty input only value will be blank", "", result[0]);
	}

	@Test
	public void splitSingleStringTest() {
		String[] result = StringCalculator.split("5","[\\n,]");
		assertEquals("Single input will have a size of 1", 1, result.length);
		assertEquals("Single input only value will be it", "5", result[0]);
	}

	@Test
	public void splitTwoStringTest() {
		String[] result = StringCalculator.split("5,2","[\\n,]");
		assertEquals("Two inputs will have a size of 2", 2, result.length);
		assertEquals("Input[0] value will be it", "5", result[0]);
		assertEquals("Input[1] value will be it", "2", result[1]);
	}

	@Test
	public void splitTwoOneBlankStringTest() {
		String[] result = StringCalculator.split("5, ","[\\n,]");
		assertEquals("Two inputs will have a size of 2", 2, result.length);
		assertEquals("Input[0] value will be it", "5", result[0]);
		assertEquals("Input[1] value will be it", " ", result[1]);
	}

	@Test
	public void splitTwoOneEmptyStringTest() {
		String[] result = StringCalculator.split("25,","[\\n,]");
		assertEquals("Two inputs will have a size of 1", 1, result.length);
		assertEquals("Input[0] value will be it", "25", result[0]);
	}

	@Test
	public void splitMoreThanTwoStringTest() {
		String[] result = StringCalculator.split("25,55,16","[\\n,]");
		assertEquals("Two inputs will have a size of 3", 3, result.length);
		assertEquals("Input[0] value will be it", "25", result[0]);
		assertEquals("Input[1] value will be it", "55", result[1]);
		assertEquals("Input[2] value will be it", "16", result[2]);
	}

	@Test
	public void splitMoreThanTwoOneEmptyStringTest() {
		String[] result = StringCalculator.split("25,,16","[\\n,]");
		assertEquals("Two inputs will have a size of 3", 3, result.length);
		assertEquals("Input[0] value will be it", "25", result[0]);
		assertEquals("Input[1] value will be it", "", result[1]);
		assertEquals("Input[2] value will be it", "16", result[2]);
	}

	@Test
	public void splitWithNewLineStringTest() {
		String[] result = StringCalculator.split("25\n16","[\\\n,]");
		assertEquals("Two inputs will have a size of 2", 2, result.length);
		assertEquals("Input[0] value will be it", "25", result[0]);
		assertEquals("Input[1] value will be it", "16", result[1]);
	}

	@Test
	public void splitWithNewLineAndCommaStringTest() {
		String[] result = StringCalculator.split("25\n16,12","[\\\n,]");
		assertEquals("Two inputs will have a size of 3", 3, result.length);
		assertEquals("Input[0] value will be it", "25", result[0]);
		assertEquals("Input[1] value will be it", "16", result[1]);
		assertEquals("Input[2] value will be it", "12", result[2]);
	}

	@Test
	public void checkForDelimiterTest() {
		String result = StringCalculator.getDelimiter("//[;]\n1;2");
		assertEquals("Value should be ;", "[\\n,]|\\Q;\\E", result);
	}

	@Test
	public void checkForLongDelimiterTest() {
		String result = StringCalculator.getDelimiter("//[--]\n1--2");
		assertEquals("Value should be --", "[\\n,]|\\Q--\\E", result);
	}

	@Test
	public void checkForNoDelimiterTest() {
		String result = StringCalculator.getDelimiter("1,2");
		assertEquals("Value should be base", "[\\n,]", result);
	}
	
	@Test
	public void checkForTwoDelimiterTest() {
		String result = StringCalculator.getDelimiter("//[--][***]\\n1,2");
		assertEquals("Value should be base", "[\\n,]|\\Q--\\E|\\Q***\\E", result);
	}

}
