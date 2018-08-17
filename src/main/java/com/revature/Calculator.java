package com.revature;

public class Calculator {
	
	/** 
	 * We're going to make an add method which accepts a string and returns 
	 * an int. 
	 * 
	 * We want this method to take 0, 1, 2, etc. 
	 */
	public static int add(String str) {
		int sum = 0;
		int number = 0;
		
		String delimiters = "[\n,]";
		
		// Special case if string is empty
		if (str.isEmpty()) { 
			return sum;
		}
		
		int newLineIndex = str.indexOf('\n');
		
		// If the string contains a newline character, check to see if 
		// the first line starts with a //. If so, single all characters
		// except for brackets will be considered as a delimiter. 
		if (str.startsWith("//") && newLineIndex != -1) {
			delimiters = str.substring(2, newLineIndex).replace("[", "").replace("]", "");
			str = str.substring(newLineIndex+1);
		}
		
		String[] numbStrings = str.split(delimiters);
		
		System.out.print("Input: " + str + "  Output: ");
		for (int i=0; i<numbStrings.length; i++) {
			System.out.print(numbStrings[i] + ", ");
			try {
				number = Integer.parseInt(numbStrings[i]);
			} catch(NumberFormatException ex) {
				return -1;
			}
			
			// If the number is negative, throw 
			if (number < 0) {
				throw new IllegalArgumentException("Cannot have negtive numbers");
			}
			
			// Ignore numbers greater than 1000
			if (number<= 1000) { 
				sum += number;
			}
		}
		System.out.println("");
		
		return sum;
	}
	
}
