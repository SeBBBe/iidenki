package wordtester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import vocab.RightWrong;

/**
 * Conducts a test of all words in the vocabulary, in random order
 */
public class SimpleTest<E extends RightWrong> implements Tester{

	private ArrayList<E> testlist;
	private int currentindex;
	private E currentword;
	
	/**
	 * Instantiates a new simple test.
	 *
	 * @param wlist the WordList
	 */
	public SimpleTest(ArrayList<E> wlist){
		testlist = new ArrayList<E>();
		testlist.addAll((Collection<? extends E>) wlist);
		currentindex = -1;
		currentword = null;
	}
	
	/* (non-Javadoc)
	 * @see wordtester.Tester#getNext()
	 */
	public E getNext(){
		if (testlist.size() == 0){
			return null;
		}
		Random generator = new Random();
		currentindex = generator.nextInt(testlist.size());
		currentword = testlist.get(currentindex);
		return currentword;
	}
	
	/* (non-Javadoc)
	 * @see wordtester.Tester#fail()
	 */
	public void fail(){
		currentword.wrong++;
	}
	
	/* (non-Javadoc)
	 * @see wordtester.Tester#success()
	 */
	public void success(){
		currentword.right++;
		testlist.remove(currentindex);
	}
}
