package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		// Add each character to an array, reversing the order of the characters 
		// by adding the characters from the front of the string to the back of the
		// array. 
		char[] characters = new char[string.length()];
		
		for (int i=0; i<string.length(); i++) {
			characters[characters.length-1-i] = string.charAt(i);
		}
		
		// Return a string created from the character array
		return String.valueOf(characters);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// Holds the acronym as we construct it
		StringBuilder acronym = new StringBuilder();
		
		// A boolean to specify if the next letter we see in the string should 
		// be added to the acronym. Default to true as the first letter should 
		// be added to the acronym. 
		boolean addNextLetter = true;
		
		
		// Loop through each character in the string adding the first letter of 
		// word to the acronym. Words are delimited by non-letter characters. 
		for (int i=0; i<phrase.length(); i++) {
			// If we are not in the acronym character accepting state, change back to 
			// the acronym character accepting state if we encounter a non-letter. 
			// Also, add a special case for the "'" character, as usually a part of 
			// words, and so shouldn't be counted as space between words
			if (!addNextLetter) {
				if (!Character.isAlphabetic(phrase.charAt(i)) && phrase.charAt(i) != '\'') {
					addNextLetter = true;
				}
			}
			// Otherwise, we are in the accepting state, which means that the next
			// letter we encounter should be converted to uppercase and added to 
			// the acronym. We then switch to the not accepting state
			else if (Character.isAlphabetic(phrase.charAt(i))) {
				acronym.append(Character.toUpperCase(phrase.charAt(i)));
				addNextLetter = false;
			}
		}
		
		
		return acronym.toString();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			// Return true if all sides are equal
			return (sideOne == sideTwo) && (sideTwo == sideThree);
		}

		public boolean isIsosceles() {
			// Return true if two sides are equal, and one is not equal to the 
			// other two. 
			if ((sideOne == sideTwo) && (sideOne != sideThree)) {
				return true;
			} else if (sideOne == sideThree && sideOne != sideTwo) {
				return true;
			} else if (sideTwo == sideThree && sideTwo != sideOne) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			// Return true if none of the sides are equal
			return (sideOne != sideTwo) 
					&& (sideTwo != sideThree) 
					&& (sideThree != sideOne);
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		final int P_NUMBER_LENGTH = 10;
//		System.out.println("Input Number:  " + string);
		// First, remove all non number characters. 
		String filteredString = string.replaceAll("[^0-9]+", "");
		
		// If we have less than 10 numbers, throw an illegal argument exception
		if (filteredString.length() < P_NUMBER_LENGTH) {
			throw new IllegalArgumentException("Phone Numbers must have at least " 
											+ P_NUMBER_LENGTH 
											+ " digits: " 
											+ string);
		}
		
		// If we have more than 11 numbers, throw an exception
		if (filteredString.length() > P_NUMBER_LENGTH + 1) {
			throw new IllegalArgumentException("Phone Numbers must have at most " 
												+ (P_NUMBER_LENGTH + 1)
												+ " digits: " 
												+ string);
		}
		
		// If we have more than 10 numbers, remove the country code number
		if (filteredString.length() > P_NUMBER_LENGTH) {
			filteredString = filteredString.substring(filteredString.length() - P_NUMBER_LENGTH);
		}
//		System.out.println("Output Number: " + filteredString);
		
		return filteredString;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// A map to hold the occurrences of the different words that occurred 
		HashMap<String, Integer> wordCounts = new HashMap<>();
		
		// A scanner that is used to parse the contents of the string 
		Scanner parser = new Scanner(string);
		// Set to delimit on non letter characters, except "'" as it is part of words
		parser.useDelimiter("[^a-zA-Z']+");
		String parsedString = "";
		
		// Loop through all of the words, adding them to the map. 
		while (parser.hasNext()) {
			parsedString = parser.next();
			
			// If the string is in the map, increment the count by putting a 
			// new mapping in place of the old integer incremented by 1. 
			// This must be done like this as Integer objects are immutable.
			if (wordCounts.containsKey(parsedString)) {
				wordCounts.put(parsedString, wordCounts.get(parsedString) + 1);
			}
			// Otherwise, there is no mapping, so create one, initialized to 1 occurrence 
			else {
				wordCounts.put(parsedString, 1);
			}
		}
		
		// Don't forget to close the scanner
		parser.close();
		
		return wordCounts;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			// In order to perform a binary search, elements must have some notion 
			// of which is greater or otherwise be comparable. Therefore, the list 
			// must be of comparable things. It will throw a ClassCastException if 
			// T is not a comparable type. 
			List<Comparable<T>> list = (List<Comparable<T>>) sortedList;
			Comparable<T> searchFor = (Comparable<T>) t;
			
			return indexOf(list, searchFor, 0, sortedList.size()-1);
		}
		
		/** 
		 * Provided with an object {@code t} to search for, searches the sortedList 
		 * for the specified object using a binary search from lowIndex to highIndex.
		 * <br/><br/>
		 * Note: This function assumes random access to the list elements, and a 
		 * sorted list. 
		 * @param list - The sorted list of comparable things.
		 * @param searchFor - The comparable object to search for 
		 * @param lowIndex - The lower bound to include in the search, inclusive 
		 * @param highIndex - The upper bound to include in the search, inclusive 
		 * @return The index of {@code t} or -1 if t was not found. 
		 */
		private int indexOf(List<Comparable<T>> list, Comparable<T> searchFor, 
							int lowIndex, int highIndex) 
		{
			// Use a in the midpoint math to prevent possible overflow if both 
			// lowIndex and highIndex are above 2^30
			int midIndex = (int)((long)(lowIndex + highIndex) / 2L);
			
			// Get the result of calling compareTo on the object we are searching for 
			// with the object at midIndex
			int comparison = searchFor.compareTo(sortedList.get(midIndex));
			
			// If the midIndex is at the object we are searching for, return midIndex
			if (comparison == 0) {
				return midIndex;
			}
			
			// If midIndex doesn't contain the object we seek and highIndex has met or 
			// passed lowIndex, return -1 as the object isn't in the list. 
			if (highIndex <= lowIndex) {
				return -1;
			}
			
			// Note: recursion is fine here. We won't blow the stack with 
			// log2(2^31 elements) = 31 stack frames
			
			// Otherwise, if the object we are searching for is less than the element at
			// mid index, recursively call indexOf with the highIndex set to midIndex - 1
			// as we have eliminated the higher half of the list. 
			if (comparison < 0) {
				return indexOf(list, searchFor, lowIndex, midIndex - 1);
			}
			
			// Otherwise, the object we are searching for is greater than the element at
			// mid index, so recursively call indexOf with lowIndex set to midIndex + 1
			// as we have eliminated the lower half of the list. 
			return indexOf(list, searchFor, midIndex + 1, highIndex);
		}
		
		
		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		// TODO Write an implementation for this method declaration
		return null;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// There are a few solutions, one with string manipulation that is more 
		// scalable. However, I figured that the math library would be faster. 
		// Besides, it's just a demo anyway. 
		
		// First, get the number of digits in the number. This is easy as we 
		// can cast to double without loss of precision, take the log10 of 
		// this base 10 number, and truncate and +1 to get the result. 
		int digits = ((int)Math.log10(Math.abs((double)input))) + 1;
		
		// The sum of the powers of digits. Keep this as a double as results might 
		// be larger than an integer can hold. 
		double armstrongSum = 0;
		
		// Holds an individual digit of the input number
		int digit = 0;
		
		// Next, compare each individual digit from the lowest to highest digit
		for (int i=0; i<digits; i++) {
			// Use (number % 10^(i+1)) / 10^i to get each individual digit in 
			// the number. 
			digit = (int)(input % Math.pow(10, i+1) / Math.pow(10, i));
			
			// Then add the digit raised to the power of total digits
			// Example: 9^9 is already larger than an integer can hold. 
			armstrongSum += Math.pow(digit, digits);
		}
		
		// If the armstrongSum is equal to the input, the input number is an 
		// Armstrong number. 
		return armstrongSum == (double)input;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		/*
		 * THE FLIPPEN DOCUMENTATION COULD HAVE SAID: 
		 * "Compute all combinations of prime factors in sorted order" 
		 * BUT NO! IT JUST SAID: 
		 * "Compute the prime factors of a given natural number"
		 * SO I DID! AND ALL THAT WORK MAKING AN OPTIMIZED FACTORS 
		 * FUNCTION, WASTED!!!
		 */
		
		// Get the factors of l
		LinkedList<Long> factors = getFactors(l);
		System.out.println("Before: " + factors);
		
		
		// For each factor, if a factor is not a prime number, defined as having 
		// two factors, remove it. 
		for (Iterator<Long> it = factors.iterator(); it.hasNext();) {
			if (getFactors(it.next()).size() != 2) {
				it.remove();
			}
		}
		
		// Sort the elements as the test cases expect sorted order. This is much more 
		// efficient to sort after the fact than it is to use an unoptimized getFactors 
		// method that produces a sorted result. 
		ArrayList<Long> aFactors = new ArrayList<>(factors);
		Collections.sort(aFactors);
		
		System.out.println("After: " + aFactors);
		return aFactors;
	}
	
	
	/** 
	 * Provided a long, computes the factors of said long. 
	 * Negative numbers not implemented. 
	 * @param l - The long to compute the factors of. 
	 * @return A list of factors
	 */
	private LinkedList<Long> getFactors(long l) {
		// The list of factors
		LinkedList<Long> factors = new LinkedList<>();
		
		// Get the square root of the long, as there will never be a factor greater than
		// its square root. The loss of precision is accounted by incrementing by one, which
		// will cover cases where we might miss a number if l > 2^52. 
		long sqrt = (long) Math.sqrt(l) + 1L;
		
		// Go through each number from 1 up to the square root and check to see if it is 
		// a factor of l. If it is, add it and its complementary factor to the list
		for (long i=1; i<sqrt; i++) {
			if (l % i == 0) {
				factors.add(i);
				
				// Only add the complementing factor if it isn't a perfect square
				if (i * i != l) {
					factors.add(l / i);
				}
			}
		}
		
		
		return factors;
	}
	
	
	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			// TODO Write an implementation for this method declaration
			return null;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			// TODO Write an implementation for this method declaration
			return null;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			return null;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// TODO Write an implementation for this method declaration
		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
