package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
	
	
	@Test 
	public void emptyStringReturnsZero() {
		//assertEquals(actual, expected)
		int sum = Calculator.add("");
		assertEquals(0, sum);
	}
	
	
	@Test 
	public void oneNumberReturnsItself() {
		//assertEquals(actual, expected)
		int sum = Calculator.add("42");
		assertEquals(42, sum);
	}
	
	@Test
	public void twoNumbersReturnsSum() {
		int sum = Calculator.add("22,79");
		assertEquals(101,sum);
	}
	
	
	@Test
	public void threeLargeNumbersReturnsNothing() {
		int sum = Calculator.add("1294321,4684,21354");
		assertEquals(0,sum);
	}
	
	@Test
    public void newLineDelimiterTwo() {
        assertEquals(3, Calculator.add("1\n2"));
    }
    
    @Test
    public void newLineDelimiterThree() {
        assertEquals(Calculator.add("1\n2\n3"), 6);
        assertEquals(Calculator.add("1\n3\n2"), 6);
        assertEquals(Calculator.add("2\n1\n3"), 6);
        assertEquals(Calculator.add("2\n3\n1"), 6);
        assertEquals(Calculator.add("3\n1\n2"), 6);
        assertEquals(Calculator.add("3\n1\n2"), 6);
    }
	
    
    @Test(expected=IllegalArgumentException.class)
    public void negativeNumber() {
        Calculator.add("1\n-2");
    }
    
    @Test
    public void numbersGreaterThanThousand() {
        assertEquals(Calculator.add("1\n2000"), 1);
    }
    
    
    
    
    @Test
    public void singleCharDelimiter() {
        assertEquals(Calculator.add("//#\n3#6"), 9);
    }
    
    @Test
    public void multiCharDelimiter() {
        assertEquals(Calculator.add("//[###]\n2###6"), 8);
    }
//	@Test(expected=NumberFormatException.class)
//	public void badInputThrowsException() {
//		int sum = Calculator.add("22sadf");
//	}
//	
//	@Test(expected=NumberFormatException.class)
//	public void badInputsThrowsException() {
//		int sum = Calculator.add("22,79sadf");
//	}
}






