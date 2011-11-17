package wordtester;
import vocab.Word;


/**
 * The interface for different test selections 
 */
public interface Tester<E> {
	
	/**
	 * Gets the next word to test
	 *
	 * @return the next word
	 */
	public E getNext();
	
	/**
	 * Is called by the program when the user enters the wrong answer
	 */
	public void fail();
	
	/**
	 * Is called by the program when the user enters the correct answer
	 */
	public void success();
}
