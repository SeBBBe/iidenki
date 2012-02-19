package kanjitester;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vocab.Kanji;
import wordtester.Tester;


/**
 * The Class TestHandler.
 */
public class TestHandler implements ActionListener {
	
	private JLabel wt;
	private JLabel stats;
	private int currentindex;
	private int correct;
	private int total;
	private List<Kanji> testlist;
	private Kanji currentword;
	private KanjiTester kanjiTester;
	private Tester<Kanji> tester;
	private List<Kanji> wlist;
	private JButton[] buttons;
	private boolean eng;
	
	/**
	 * Conducts the test apart from what the Tester does
	 *
	 * @param wlist the WordList to test
	 * @param wt the WordText field (to show the next word)
	 * @param stats the stats field to show user statistics
	 * @param kanjiTester the graphical kanji test object
	 * @param tester the tester to use for this test
	 * @param buttons 
	 */
	public TestHandler(List<Kanji> wlist, JLabel wt, JLabel stats, KanjiTester kanjiTester, Tester<Kanji> tester, JButton[] buttons, boolean eng){
		this.wt = wt;
		this.stats = stats;
		this.kanjiTester = kanjiTester;
		this.wlist = wlist;
		this.tester = tester;
		this.buttons = buttons;
		this.eng = eng;
		currentindex = -1;
		correct = 0;
		total = 0;
		testlist = new ArrayList<Kanji>();
		testlist.addAll(wlist);
		makeNext();
		for (JButton jb : buttons){
			jb.addActionListener(this);
		}
	}
	
	/**
	 * Generates and prints the next question for the user, using the selected Tester
	 */
	public void makeNext(){
		currentword = (Kanji)tester.getNext();
		if (currentword == null){
			JOptionPane.showMessageDialog(null, "End of test!\n" + correct + " correct out of " + total);
			kanjiTester.dispose();
			try{
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(kanjiTester.file));
				out.writeObject(wlist);
			}catch(Exception f){
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occured while saving! List not saved.");
			}
			return;
		}
		wt.setText(currentword.toString());
		Random generator = new Random();
		for (int i = 0; i < 5; i++){
			int index = generator.nextInt(testlist.size());
			if (eng){buttons[i].setText(testlist.get(index).translation);}
			else{buttons[i].setText(testlist.get(index).reading);}
		}
		int correctbutton = generator.nextInt(5);
		buttons[correctbutton].setText(currentword.reading);
		if (eng){buttons[correctbutton].setText(currentword.translation);}
		else{buttons[correctbutton].setText(currentword.reading);}
		total++;
	}

	/**
	 * Checks the validity of the answer
	 *
	 * @param text the attempted guess
	 */
	public void check(String text) {
		if (currentword.check(text)){
			correct++;
			currentword.right++;
			tester.success();
		}else{
			currentword.wrong++;
			tester.fail();
			JOptionPane.showMessageDialog(null, "Wrong! The correct answer is\n"+currentword.reading+"\nwhich means\n"+currentword.translation);
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

	public void actionPerformed(ActionEvent arg0) {
		check(arg0.getActionCommand());
	}
}
