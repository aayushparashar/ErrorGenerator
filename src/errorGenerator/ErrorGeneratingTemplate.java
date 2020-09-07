package errorGenerator;

public interface ErrorGeneratingTemplate {
	String generateTypingErrorInLine(String line);

	String generateLine(String[] words);

	boolean generateErrorInWord(String[] words, int index);

	boolean generateErrorByCapitalization(StringBuilder str, int index);

	boolean generateErrorByRepeating(StringBuilder str, int index);

	boolean generateErrorBySwapping(StringBuilder str, int index);

	boolean generateErrorByCharacter(StringBuilder str, int index);

	boolean addSimilarWords(StringBuilder str, int index, String[] words);

}
