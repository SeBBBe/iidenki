package wordtester;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import vocab.RightWrong;

/**
 * Conducts a test of the latest items
 */
public class LatestTest<E extends RightWrong> implements Tester{

	private ArrayList<E> testlist;
	private int currentindex;
	private E currentword;
	
	/**
	 * Instantiates a new latest test.
	 *
	 * @param wlist the WordList
	 */
	public LatestTest(ArrayList<E> wlist){
		String num = JOptionPane.showInputDialog(null, "This test will present you with the latest words. How many words would you like to type?");
		int n = 10;
		try{
			n = Integer.parseInt(num);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Invalid value. Will test 10 words.");
			n = 10;
		}
		if (n > wlist.size()){
			JOptionPane.showMessageDialog(null, "The number is larger than the size of the list! All words will be tested.");
			n = wlist.size();
		}
		if (n < 1){
			JOptionPane.showMessageDialog(null, "Invalid value. Will test 10 words.");
			n = 10;
		}
		int size = wlist.size();
		int start = size -n;
		testlist = new ArrayList<E>();
		for (int i = 0; i < n; i++){
			testlist.add(wlist.get(i+start));
		}
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
