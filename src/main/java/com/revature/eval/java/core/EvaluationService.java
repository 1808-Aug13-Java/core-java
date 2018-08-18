package com.revature.eval.java.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
		// The sum of points for each character
		int score = 0;
		
		// If the score table has not yet been computed, compute it. 
		if (scrabbleScoreTable == null) {
			scrabbleScoreTable = new HashMap<>();
			scrabbleScoreTable.put('a', 1);
			scrabbleScoreTable.put('e', 1);
			scrabbleScoreTable.put('i', 1);
			scrabbleScoreTable.put('o', 1);
			scrabbleScoreTable.put('u', 1);
			scrabbleScoreTable.put('l', 1);
			scrabbleScoreTable.put('n', 1);
			scrabbleScoreTable.put('r', 1);
			scrabbleScoreTable.put('s', 1);
			scrabbleScoreTable.put('t', 1);
			scrabbleScoreTable.put('d', 2);
			scrabbleScoreTable.put('g', 2);
			scrabbleScoreTable.put('b', 3);
			scrabbleScoreTable.put('c', 3);
			scrabbleScoreTable.put('m', 3);
			scrabbleScoreTable.put('p', 3);
			scrabbleScoreTable.put('f', 4);
			scrabbleScoreTable.put('h', 4);
			scrabbleScoreTable.put('v', 4);
			scrabbleScoreTable.put('w', 4);
			scrabbleScoreTable.put('y', 4);
			scrabbleScoreTable.put('k', 5);
			scrabbleScoreTable.put('j', 8);
			scrabbleScoreTable.put('x', 8);
			scrabbleScoreTable.put('q', 10);
			scrabbleScoreTable.put('z', 10);
		}
		
		// For each character, add up it's score to the current running 
		// score for the word. 
		for (int i=0; i<string.length(); i++) {
			score += scrabbleScoreTable.get(Character.toLowerCase(string.charAt(i)));
		}
		
		return score;
	}
	
	/** Allows a mapping of characters to their value in scrabble. 
	 * Lazily initialized when needed by the getScrabbleScore method. 
	 * Only one instance needs to be computed once. */
	private static HashMap<Character, Integer> scrabbleScoreTable;
	

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

		@SuppressWarnings("unchecked")
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
		// Parse the string into individual tokens based on default whitespace
		Scanner parser = new Scanner(string);
		
		// Used to construct the new return string
		StringBuilder sBuilder = new StringBuilder();
		
		// Get a list of vowel characters. An ArrayList has faster searching
		// than a HashSet for small lists. 
		Character[] vowelArray = {'a', 'e', 'i', 'o', 'u'};
		List<Character> vowels = Arrays.asList(vowelArray);
		
		// Index of the first vowel
		int vowelIndex = -1;
		
		// A token from the scanner. 
		String token;
		
		// Convert each word in the string into pig latin
		while (parser.hasNext()) {
			token = parser.next();
			
			// Find the next vowel in the token, or -1 if there isn't one
			vowelIndex = -1;
			for(int i=0; i<token.length(); i++) {
				// If the character is a vowel, we have found the index we need. 
				// Special case if we have a 'u' after a 'q'. The 'u' is not a 
				// vowel in this case. 
				if (vowels.contains(token.charAt(i))
						&& !(i != 0 
							&& token.charAt(i) == 'u' 
							&& token.charAt(i-1) == 'q')) 
				{
					vowelIndex = i;
					break;
				}
			}
			
			// If there is a vowel, break the string into pig latin
			if (vowelIndex != -1) {
				sBuilder.append(token.substring(vowelIndex));
				sBuilder.append(token.substring(0, vowelIndex));
				sBuilder.append("ay");
			}
			// Otherwise, leave the word alone if there are no vowels.
			else {
				sBuilder.append(token);
			}
			
			// If there is another token after this one, insert a space
			if (parser.hasNext()) {
				sBuilder.append(' ');
			}
		}
		
		parser.close();
		
		return sBuilder.toString();
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
		
		// A list of prime factors of l
		LinkedList<Long> primeFactors = new LinkedList<>();
		
		// Get the set of primes up to l. That way, it doesn't need to be
		// recomputed for every factor extracted.
		ArrayList<Long> primes = getPrimes(l);
		
		// Used to hold a prime factor of l
		long prime = 0;
		
		
		// Continue extracting prime factors from l until we have extracted all
		// prime factors. Also, doesn't execute for anything less than 2. 
		while (l > 1) {
			// Find a prime factor of l, by iterating through the ordered prime 
			// numbers. 
			for (int i=0; i<primes.size(); i++) {
				prime = primes.get(i);
				// Test to see if the number provided is divisible by the specified 
				// prime. If so, add that prime to the list, extract this factor by 
				// dividing l by this factor, and break this loops 
				if (l % prime == 0) {
					primeFactors.add(prime);
					l /= prime;
					break;
				}
			}
		}
		
		return primeFactors;
	}
	
	
	/**  
	 * Returns the set of prime numbers up to and including a specified long. 
	 * The primes are in their natural ordering. 
	 * @param l - The limit on prime numbers to add to the set
	 * @return The set of prime numbers up to the provided number
	 */
	private ArrayList<Long> getPrimes(long l) {
		ArrayList<Long> primes = new ArrayList<>();
		
		// TODO: This can be optimized by only checking if a number contains other
		// TODO: prime numbers as factors, instead of checking all numbers. As prime numbers
		// TODO: are all factors of composite numbers, any number that has a composite factor 
		// TODO: is also divisible by that composite number's prime factors. 
		
		// Go through each number from 2 to l inclusive, (as 0 & 1 are not prime). 
		// It needs to be inclusive as l itself might be prime. 
		// If a number is prime, add it to the set. 
		for (long i=2; i<=l; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		
		return primes;
	}
	
	
	/** Returns true if the specified number is prime. False otherwise. */
	private boolean isPrime(long l) {
		// Get the square root of the long, as there will never be a factor greater than
		// its square root. The loss of precision is accounted by incrementing by one, which
		// will cover cases where we might miss a number if l > 2^52. 
		long sqrt = (long) Math.sqrt(l) + 1L;
		
		// A count of the number of factors
		long factors = 0;
		
		// There are only two factors in a prime number, itself and 1. 
		final long FACTORS_OF_A_PRIME = 2;
		
		// Go through each number from 1 up to the square root and check to see if it is 
		// a factor of l. If it is, add 2, one for it, and one for its compliment. 
		// If there are ever more than 2 factors, it is not a prime number
		for (long i=1; i < sqrt && factors <= FACTORS_OF_A_PRIME; i++) {
			if (l % i == 0) {
				factors++;
				
				// Only add the complementing factor if it isn't a perfect square or 1
				if (i * i != l) {
					factors++;
				}
			}
		}
		
		// If there are not two factors, it is not prime.
		return factors == FACTORS_OF_A_PRIME;
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
		
		/** The number of letters in the alphabet. */
		private static final int LETTERS_IN_ALPHA = 26;
		
		/** The offset in the ASCII table that the upper case letters begin at. */
		private static final int UPPER_CASE_OFFSET = (int) 'A';
		
		/** The offset in the ASCII table that the lower case letters begin at. */
		private static final int LOWER_CASE_OFFSET = (int) 'a';
		
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			// A string builder to build up the resulting string
			StringBuilder sBuilder = new StringBuilder();
			
			// Holds each character as as an integer as it is manipulated
			int chInt = '\0';
			
			// The offset we use on a character, which depends on if a character 
			// is upper case or lower case. 
			int offset = 0;
			
			// Loop through each character in the string, running the cipher if it 
			// is a letter of the alphabet and ignoring it if it isn't. 
			for (int i=0; i<string.length(); i++) {
				chInt = (int) string.charAt(i);
				
				// If the character is not alphabetic, just append it
				// without running the cipher
				if (!Character.isAlphabetic(chInt)) {
					sBuilder.append((char) chInt);
					continue;
				}
				
				// Get the offset to use based on if this is an upper case or 
				// lower case character. 
				if (Character.isUpperCase(chInt)) {
					offset = UPPER_CASE_OFFSET;
				} 
				else {
					offset = LOWER_CASE_OFFSET;
				}
				
				// Offset the character so that the alphabet is now zero based, 
				// i.e. 'a' and 'A' are 0 and 'z' and 'Z' are 25. 
				chInt -= offset;
				
				// Now add the rotational key
				chInt += this.key;
				
				// Now use modulo to account for going past the end of the alphabet
				chInt %= LETTERS_IN_ALPHA;
				// Also account for negative rotations
				if (chInt < 0) {
					chInt += LETTERS_IN_ALPHA;
				}
				
				// Undo the offset moving the character back to the alphabetic 
				// ascii codes. 
				chInt += offset;
				
				// Finally, cast back to a char, (safe as the number is between 
				// 65 & 122 inclusive) and append the char to the encoded string
				sBuilder.append((char) chInt);
			}
			
			return sBuilder.toString();
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
		int primes = 0;
		
		// Set currentNumber to 1 so that it starts testing primes at 2. s
		int currentNumber = 0;
		
		// If there is not a valid ith prime, IE, i <= 0, throw an exception
		if (i <= 0) {
			throw new IllegalArgumentException("i Must be positive: " + i);
		}
		
		while (primes < i) {
			// Increment the current number
			currentNumber++;
			// TODO: Optimize, as can be expensive. 
			// TODO: Note: All composite numbers are made of primes, so only prime numbers
			// TODO: need to be checked when checking for a prime. OPTIMIZATION!!!!!
			// If the currentNumber is prime, increment primes
			if (isPrime(currentNumber)) {
				primes++;
			}
		}
		
		return (int) currentNumber;
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
		
		/** The number of letters in the alphabet. */
		private static final int LETTERS_IN_ALPHA = 26;
		
		/** The offset in the ASCII table that the lower case letters end at. */
		private static final int LOWER_CASE_END_OFFSET = ((int) 'z') + 1;
		
		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			return runCipher(string, true);
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			return runCipher(string, false);
		}
		
		
		/**
		 * A private helper method to facilitate the encoding and decoding of 
		 * atbash cipher. 
		 * @param str - The string to encode or decode
		 * @param encode - True if we are encoding, false if decoding. 
		 * @return - The resulting string from running the cipher. 
		 */
		private static String runCipher(String string, boolean encode) {
			// A string builder to hold the newly encoded string
			StringBuilder sBuilder = new StringBuilder();
			
			// Holds each character as we manipulate it
			char ch = '\0';
			
			// Current character counter
			int characterCounter = 0;
			
			// Encode each character of the string
			for (int i=0; i<string.length(); i++) {
				// First, convert the character to lower case 
				ch = Character.toLowerCase(string.charAt(i));
				
			
				
				// If the character isn't a digit or a letter, ignore it entirely
				if (!Character.isDigit(ch) && !Character.isAlphabetic(ch)) {
					continue;
				}
				
				// If the character is a letter, flip it according to the cipher. 
				// If the character is not an alphabetic character, don't shift it. 
				// Just append it.
				if (Character.isAlphabetic(ch)) {
					// Shift the value of the character directly after where the lower 
					// case alphabet ends. 'a' is now 1 after where 'z' was, 'b' is 2
					// after, and so on. This allows us to shift the characters back 
					// based on their distance from z. 
					ch += LETTERS_IN_ALPHA;
					
					// Now shift back the character based on its distance from the OFFSET CHAR
					ch -= 1 + 2 * ((int) ch - LOWER_CASE_END_OFFSET);
				}
				
				// If we are encoding, every five characters that we append, we want 
				// to add a space. The only time we don't is when we have reached 
				// the end of the input string. 
				// We do mod 6 as a special case for the first character since we add after 
				// adding the sixth character to avoid adding spaces at the end of the string
				characterCounter++;
				if (encode && characterCounter % 6 == 0) {
					sBuilder.append(" ");
					characterCounter = 1;
				}
				
				// Finally, add the shifted character to or newly constructed string
				sBuilder.append(ch);
			}
			
			return sBuilder.toString();
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
		// Holds the running result of the ISBN number validity formula
		int result = 0;
		
		// Keeps track of if the check has failed for some reason
		boolean failedCheck = false;
		
		// The number of digits we expect to encounter. This is also used in the 
		// formula to test validity. It is decremented for each digit. 
		// Therefore, if this is not 0 by the end, the number is not valid. 
		int expectedDigits = 10;
		
		// Holds each character as we manipulate it
		char ch = '\0';
		
		// Holds the number that we parse from the character 
		int number = 0;
		
		// The defined value of an 'x' character
		final int X_VALUE = 10;
		
		
		// For each character in the string, run the math formula
		// stop if there is some reason besides math that causes this string to be 
		// invalid ISBN.
		for (int i=0; i<string.length() && !failedCheck; i++) {
			ch = Character.toLowerCase(string.charAt(i));
			
			// If the character is a digit or an 'x', which is counts as a digit, 
			// run it through the formula
			if (Character.isDigit(ch) || ch == 'x') {
				// Special case: an X represents a 10 digit. 
				if (ch == 'x') {
					number = X_VALUE;
				}
				// Otherwise, convert our character number to an integer in base 10.
				else {
					
					number = Character.digit(ch, 10);
				}
				
				// The formula to validate a ISBN-10 number
				result += number * expectedDigits;
				expectedDigits--;
				
				// If we have encountered more than the expected digits, this is not 
				// a valid ISBN-10 number
				if (expectedDigits < 0) {
					failedCheck = true;
				}
			}
			// Otherwise, if the character is a hyphen, ignore it. If it is another 
			// character besides that, the number is invalid.
			else if (ch != '-') {
				failedCheck = true;
			}
		}
		
		// Return true if the resulting number mod 11 == 0. THis is part of the 
		// validation of the ISBN-10. Only return ture if the number hasn't been 
		// invalidated for another reason. 
		return !failedCheck && expectedDigits == 0 && result % 11 == 0;
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
		HashSet<Character> letters = new HashSet<>();
		
		// Add any encountered letters to the hash set
		for (int i=0; i<string.length(); i++) {
			// Only consider letters that have been converted to lower case
			// This prevents more than 26 characters from being considered. 
			if (Character.isAlphabetic(Character.toLowerCase(string.charAt(i)))) {
				letters.add(string.charAt(i));
			}
		}
		
		// If we have 26 letters in out hash set, we have a pangram
		return letters.size() == 26;
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
		// A gigasecond, (one billion seconds)
		final long gigaSecond = 1000000000L;
		
		
		LocalDateTime newTime = null;
		
		//Because there are two classes of time measurement on the back end, 
		// I need to accommodate the possibility that the Temporal doesn't 
		// support seconds. Because everything after the Date class was GENIUS!
		if (given.isSupported(ChronoUnit.SECONDS)) {
			newTime = LocalDateTime.of(
					given.get(ChronoField.YEAR), 
					given.get(ChronoField.MONTH_OF_YEAR), 
					given.get(ChronoField.DAY_OF_MONTH), 
					given.get(ChronoField.HOUR_OF_DAY), 
					given.get(ChronoField.MINUTE_OF_HOUR), 
					given.get(ChronoField.SECOND_OF_MINUTE));
			
		}
		// Otherwise, just use days...
		else {
			newTime = LocalDateTime.of(
					given.get(ChronoField.YEAR), 
					given.get(ChronoField.MONTH_OF_YEAR), 
					given.get(ChronoField.DAY_OF_MONTH),
					0, 0, 0);
		}
		
		newTime = newTime.plusSeconds(gigaSecond);
		
		return newTime;
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
		int sum = 0;
		
		// For natural numbers from 1 to i...
		for (int j=1; j<i; j++) {
			// Check each number in the provided set to see if 'j''
			// is a multiple of each number in the set. 
			for (int k=0; k<set.length; k++) {
				if (j % set[k] == 0) {
					sum += j;
					// If we have found a multiple, don't possibly repeat it
					break;
				}
			}
		}
		
		return sum;
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
		// Holds the running result of the ISBN number validity formula
		int result = 0;
		
		// THe current number we are manipulating. 
		int currentNumber = 0;
		
		// Keeps track of if the check failed for some reason
		boolean failedCheck = false;
		
		// Holds each character as we manipulate it
		char ch = '\0';
		
		// Holds each number as we manipulate it
		int number = 0;
		
		
		
		// Starting from the right perform the Luhn formula, doubling every 
		// second number, subtracting 9 if the double results in >= 10
		// Break when we are done checking, or if the number has been invalidated
		for (int i = string.length() - 1; i>= 0; i--) {
			ch = string.charAt(i);
			
			// If the character is a space, ignore it. 
			if (ch == ' ') {
				continue;
			}
			// Otherwise, if the character is not a digit it automatically fails
			else if (!Character.isDigit(ch)) {
				failedCheck = true;
				break;
			}
			
			// Convert our character number to an integer in base 10.
			number = Character.digit(ch, 10);
			
			// Double every second number, subtracting 99 if it goes to 10 or over.
			currentNumber++;
			if (currentNumber % 2 == 0) {
				number *= 2;
				
				// If the result is ten or greater, subtract 9 according to 
				// the Luhn formula
				if (number >= 10) { 
					number -= 9;
				}
			}
			
			// Add the number to the running sum
			result += number;
		}
		
		// Return true if the resulting number is divisible by ten, and the check
		// didn't fail for some other reason
		return !failedCheck && result % 10 == 0;
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
		// Note: this implementation assumes correct input of two numbers with 
		// an  operator between them. This will return incorrect results if the 
		// string is not properly formated, or if more than two operators are 
		// provided. 
		
		// Used to parse the different parts parts of the string based on whitespace
		Scanner scanner = new Scanner(string);
		
		// Holds a token from the string parser.
		String token = null;
		
		// Holds the numbers in the equation
		ArrayList<Integer> numbers = new ArrayList<>();
		
		// For use with lambda expressions to define the operation dased on the word
		Operation operation = null;
		
		
		// While there is more input, parse the string
		while (scanner.hasNext()) {
			token = scanner.next();
			
			// If the token contains a number, even with non-number characters, 
			// extract the number with regex and put it in the list of numbers. 
			// Then continue with the loop. 
			if (token.matches(".*[0-9]+.*")) {
				token = token.replaceAll("[^0-9\\-]", "");
				numbers.add(Integer.parseInt(token));
				continue;
			}
			
			
			// If it's not a number, switch to see if we should perform an 
			// operation, and which operation to perform. Only do this if operation 
			// hasn't been previously defined. 
			if (operation == null) {
				switch(token) {
				case "plus": 
					operation = (int i1, int i2) -> {return i1 + i2;};
					break;
				case "minus": 
					operation = (int i1, int i2) -> {return i1 - i2;};
					break;
				case "multiplied": 
					operation = (int i1, int i2) -> {return i1 * i2;};
					break;
				case "divided": 
					operation = (int i1, int i2) -> {return i1 / i2;};
					break;
				// No default case. Just ignore other strings
				}
			}
		}
		
		// Close that scanner. No resources left behind. 
		scanner.close();
		
		// If we don't have an operation, throw an exception. 
		if (operation == null) {
			throw new IllegalArgumentException(
						"No operation found within input: " + string);
		}
		
		// If we don't have at least two arguments throw an exception
		if (numbers.size() < 2) {
			throw new IllegalArgumentException(
						"Need at least two numbers to operate on: " + string);
		}
		
		
		// Take the first two numbers from the list and perform the given 
		// operation on it. Return the result
		return operation.execute(numbers.get(0), numbers.get(1));
	}
	
	/** 
	 * A private interface for use in lambda expressions in the solveWordProblem
	 * method.  
	 */
	private interface Operation {
		int execute(int i1, int i2);
	}

}
