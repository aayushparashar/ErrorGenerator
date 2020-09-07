package errorGenerator;

import java.util.Random;

public class ErrorGenerator implements ErrorGeneratingTemplate {

	Random rand;

	public ErrorGenerator() {
		// TODO Auto-generated constructor stub
		rand = new Random();
	}

	@Override
	public String generateTypingErrorInLine(String line) {
		// Splitting the line into the words
		String[] words = line.split(" ");

		// Boolean array that checks if the error is already introduced in the current
		// word or not
		boolean[] generatedError = new boolean[words.length];
		int cnt = 0;

		// A Random number that decides how many words to introduce the error in, with
		// the range [1 - words.length/2]
		int numberOfErrors = rand.nextInt((words.length + 1) / 2) + 1;

		while (cnt < numberOfErrors) {
			// Getting a index for the word to introduce error in
			int wordIndex = rand.nextInt(words.length);
			if (generatedError[wordIndex]) // Error already introduced in the word
				continue;
			boolean b = false;
			if (words[wordIndex].length() > 1) {
				// No error to be introduced in case of single character
				b = generateErrorInWord(words, wordIndex);
			}
			if (b) {
				// Only if introducing of error into the word was successful
				cnt++;
				generatedError[wordIndex] = true;
			}
		}
		return generateLine(words);
	}

	@Override
//Function for generating a string from a list of words
	public String generateLine(String[] words) {
		StringBuilder str = new StringBuilder();
		for (String word : words)
			str.append(word).append(" ");
		return str.substring(0, str.length() - 1).toString();
	}

	@Override
	public boolean generateErrorInWord(String[] words, int index) {
		// Random number to decide which type of error to generate in the word
		int characterIndexToChange = rand.nextInt(words[index].length());
		StringBuilder str = new StringBuilder(words[index]);
		int errorType = rand.nextInt(4);
		boolean ans = false;
		switch (errorType) {
		case 0: // Generate error by repeating character at index
			ans = ans || generateErrorByRepeating(str, characterIndexToChange);
		case 1: // Generate error by swapping index and index+1 character
			ans = ans || generateErrorBySwapping(str, characterIndexToChange);
		case 2: // Generate error by replacing the character with similar sounding characters
			ans = ans || generateErrorByCharacter(str, characterIndexToChange);
		case 4: // Generate error by switching the case of the first index
			ans = ans || generateErrorByCapitalization(str, characterIndexToChange);
		}
		words[index] = str.toString();
		return ans;
	}

	@Override
	public boolean generateErrorByCapitalization(StringBuilder str, int index) {
		if (!Character.isAlphabetic(str.charAt(index))) {
			// Character is not an alphabet
			return false;
		}
		if (Character.isLowerCase(str.charAt(index)))
			str.setCharAt(index, Character.toUpperCase(str.charAt(index)));
		else
			str.setCharAt(index, Character.toLowerCase(str.charAt(index)));
		return true;
	}

	@Override
	public boolean generateErrorByRepeating(StringBuilder str, int index) {
		if (Character.isAlphabetic(str.charAt(index))) {
			str.insert(index + 1, Character.toLowerCase(str.charAt(index)));
			return true;
		} else
			return false;
	}

	@Override
	public boolean generateErrorBySwapping(StringBuilder str, int index) {
		if (index == 0) {
			// Not swapping the first character
			return false;
		}
		try {
			// Swapping the characters at index and index+1
			char ch = str.charAt(index);
			str.setCharAt(index, str.charAt(index + 1));
			str.setCharAt(index + 1, ch);
		} catch (Exception e) {
			// Swapping the last character of the string
			return false;
		}
		return true;
	}

	@Override
	public boolean generateErrorByCharacter(StringBuilder str, int index) {
		// Array which stores the similar sounding words
		String[][] similarWords = new String[][] { { "ar", "o", "au", "ai" }, {}, { "k", "ch", "ck" }, {},
				{ "ee", "ei", "i" }, { "ef" }, { "j" }, {}, {}, {}, { "ch", "ck" }, { "el" }, { "em" }, { "en" },
				{ "au", "ow" }, {}, { "qu" }, { "ar", "er" }, { "es", "as" }, {}, {}, {}, {}, { "ex" }, {}, { "j" }, };

		if (Character.isAlphabetic(str.charAt(index)))
			return addSimilarWords(str, index, similarWords[Character.toLowerCase(str.charAt(index)) - 'a']);
		else
			return false;

	}

	@Override
	public boolean addSimilarWords(StringBuilder str, int index, String[] words) {
		if (words.length == 0) {
			return false;
		}
		str.deleteCharAt(index);
		int val = rand.nextInt(words.length);
		str.insert(index, words[val].toCharArray());
		return true;
	}

}
