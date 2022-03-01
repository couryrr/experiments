package com.dappercloud;

public class Runner {

	public static void main(String[] args) {
		try {
			int result = StringCalculator.addString("");
			System.out.println("Blanks expect to be 0 = " + result);
			
			result = StringCalculator.addString("5");
			System.out.println("Single values are themselves 5 = " + result);
			
			result = StringCalculator.addString("1001");
			System.out.println("Skip 1001 0 = " + result);
			
			result = StringCalculator.addString("1000");
			System.out.println("Let 1000 go 1000 = " + result);
			
			result = StringCalculator.addString("5,1,5,6,9,88,2");
			System.out.println("Add with commas 116 = " + result);
			
			result = StringCalculator.addString("5\n1\n5\n6\n9\n88\n2");
			System.out.println("Add with new lines 116 = " + result);
			
			result = StringCalculator.addString("5\n1\n5,6\n9\n88,2");
			System.out.println("Mix both commas and new lines 116 = " + result);
			
			result = StringCalculator.addString("//[#]\n5#1\n5,6\n9\n88,2");
			System.out.println("Mix both commas and new lines and create your own 116 = " + result);
			
			result = StringCalculator.addString("//[#][%%%]\n5#1\n5,6%%%9\n88,2");
			System.out.println("Mix both commas and new lines and create multi-groups own 116 = " + result);
			
			result = StringCalculator.addString("//[#][%%%]\n5#1\n5,6%%%-9\n88,2");
			
		} catch (Exception e) {
			System.out.println("No negatives though -9 = " + e.getMessage());
		}

	}

}
