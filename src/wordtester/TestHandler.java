package wordtester;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vocab.Word;


/**
 * The Class TestHandler.
 */
public class TestHandler {
	
	private JLabel wt;
	private JLabel stats;
	private int currentindex;
	private int correct;
	private int total;
	private ArrayList<Word> testlist;
	private Word currentword;
	private WordTester wordTester;
	private Tester<Word> tester;
	private List<Word> wlist;
	private boolean testtype;
	
	/**
	 * Conducts the test apart from what the Tester does
	 *
	 * @param wlist2 the WordList to test
	 * @param wt the WordText field (to show the next word)
	 * @param stats the stats field to show user statistics
	 * @param wordTester the graphical word tester object
	 * @param tester the tester to use for this test
	 * @param testtype selects whether to test the user on the grammatical word class
	 */
	public TestHandler(List<Word> wlist2, JLabel wt, JLabel stats, WordTester wordTester, Tester<Word> tester, boolean testtype){
		this.wt = wt;
		this.stats = stats;
		this.wordTester = wordTester;
		this.wlist = wlist2;
		this.tester = tester;
		this.testtype = testtype;
		currentindex = -1;
		correct = 0;
		total = 0;
		testlist = new ArrayList<Word>();
		testlist.addAll(wlist2);
		makeNext();
	}
	
	/**
	 * Generates and prints the next question for the user, using the selected Tester
	 */
	public void makeNext(){
		currentword = tester.getNext();
		if (currentword == null){
			JOptionPane.showMessageDialog(null, "End of test!\n" + correct + " correct out of " + total);
			wordTester.dispose();
			try{
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(wordTester.file));
				out.writeObject(wlist);
			}catch(Exception f){
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured while saving! List not saved.");
			}
			return;
		}
		wt.setText(currentword.toString());
		total++;
	}

	/**
	 * Checks the validity of the answer
	 *
	 * @param text the attempted guess
	 */
	public void check(String text) {
		if (currentword.check(text, wordTester.buttonselection, testtype)){
			correct++;
			currentword.right++;
			tester.success();
		}else{
			currentword.wrong++;
			tester.fail();
			JOptionPane.showMessageDialog(null, "Wrong! The correct answer is\n"+currentword.getRomaji()+"\n"+currentword.getKana()+"\n"+currentword.getKanji()+"\nThe class of this word is "+currentword.type);
		}
		updateStats();
		makeNext();
	}
	
	/**
	 * Update the stats field
	 */
	public void updateStats(){
		stats.setText(correct + " correct out of " + total);
	}
}
