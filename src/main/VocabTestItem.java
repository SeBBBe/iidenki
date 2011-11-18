package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import kanjieditor.KanjiEditor;
import kanjitester.KanjiTester;
import sheets.KanjiSheets;
import vocab.Kanji;
import vocab.Word;
import wordtester.DynamicTest;
import wordtester.SimpleTest;
import wordtester.Tester;
import wordtester.WordTester;

public class VocabTestItem extends SubMenuEntry{
	
	public VocabTestItem(){
		super("Vocabulary test",new File("vocab_test_menu.gif"));
	}

	public void run(){
		String[] possibilities = {"Test all words", "Test the most difficult words"};
		Object[] inc = new Object[3];
		inc[0] = "What kind of test do you want to conduct?";
		JCheckBox reset = new JCheckBox("Reset scores");
		JCheckBox testtype = new JCheckBox("Test grammatical word class");
		testtype.setSelected(true);
		inc[1] = reset;
		inc[2] = testtype;
		String s = (String)JOptionPane.showInputDialog(
		                    null,
		                    inc,
		                    "Vocabulary test type",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "Test all words");
		if (s == null){
			return;
		}
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Open vocabulary list");
	    chooser.showOpenDialog(null);
	    File file = chooser.getSelectedFile();
	    ArrayList<Word> newlist;
	    if (file == null){return;}
		try{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			newlist =(ArrayList<Word>)in.readObject();
			if (reset.isSelected()){
				resetList(newlist);
			}
			Tester test = new SimpleTest(newlist);
			if(s == possibilities[0]){
				test = new SimpleTest(newlist);
			}else if(s == possibilities[1]){
				test = new DynamicTest(newlist);
			}
			new WordTester(newlist, test, file, testtype.isSelected());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error loading file!");
			e.printStackTrace();
		}
	}

	private void resetList(ArrayList newlist) {
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the scores in this list?\nThis information is used to determine which words are the most difficult.", "Confirm reset", JOptionPane.YES_NO_OPTION);
		if (confirm == 0){
			for (Object w : newlist.toArray()){
				((Word)w).right = 0;
				((Word)w).wrong = 0;
			}
		}
	}
}
