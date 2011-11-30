package kanjitester;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import vocab.Kanji;
import vocab.Word;
import wordtester.DynamicTest;
import wordtester.LatestTest;
import wordtester.SimpleTest;
import wordtester.Tester;

/**
 * Control object for launching kanji quiz
 */
public class KanjiTestControl {
	
	/**
	 * Do test.
	 */
	public static void doTest(){
		String[] possibilities = {"Test all kanji", "Test the most difficult kanji", "Test the latest kanji"};
		Object[] inc = new Object[3];
		inc[0] = "What kind of test do you want to conduct?";
		JCheckBox reset = new JCheckBox("Reset scores");
		JCheckBox eng = new JCheckBox("Use English translation instead of reading");
		inc[1] = reset;
		inc[2] = eng;
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
	    ArrayList<Kanji> newlist;
	    if (file == null){return;}
		try{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			newlist =(ArrayList<Kanji>)in.readObject();
			if (reset.isSelected()){
				resetList(newlist);
			}
			Tester test = new SimpleTest<Kanji>(newlist);
			if(s == possibilities[0]){
				test = new SimpleTest<Kanji>(newlist);
			}else if(s == possibilities[1]){
				test = new DynamicTest<Kanji>(newlist);
			}else if(s == possibilities[2]){
				test = new LatestTest<Kanji>(newlist);
			}
			new KanjiTester(newlist, test, file, eng.isSelected());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error loading file!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Reset score
	 *
	 * @param newlist the list to reset
	 */
	private static void resetList(ArrayList<Kanji> newlist) {
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the scores in this list?\nThis information is used to determine which kanji are the most difficult.", "Confirm reset", JOptionPane.YES_NO_OPTION);
		if (confirm == 0){
			for (Object w : newlist.toArray()){
				((Word)w).right = 0;
				((Word)w).wrong = 0;
			}
		}
	}
}
