package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import kanjitester.KanjiTester;
import vocab.Kanji;
import vocab.Word;
import wordtester.DynamicTest;
import wordtester.SimpleTest;
import wordtester.Tester;


/**
 * The kanji test button
 */
public class KanjiTestButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 4725080651066764126L;

	/**
	 * Instantiates a new kanji test button.
	 */
	public KanjiTestButton(){
		super("Kanji test");
		addActionListener(this);
	}

	/**
	 * Asks the test type and initializes the test
	 */
	public void actionPerformed(ActionEvent arg0) {
		String[] possibilities = {"Test all kanji", "Test the most difficult kanji"};
		Object[] inc = new Object[2];
		inc[0] = "What kind of test do you want to conduct?";
		JCheckBox reset = new JCheckBox("Reset scores");
		inc[1] = reset;
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
			Tester test = new SimpleTest(newlist);
			if(s == possibilities[0]){
				test = new SimpleTest(newlist);
			}else if(s == possibilities[1]){
				test = new DynamicTest(newlist);
			}
			new KanjiTester(newlist, test, file);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error loading file!");
			e.printStackTrace();
		}
	}

	private void resetList(ArrayList newlist) {
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the scores in this list?\nThis information is used to determine which kanji are the most difficult.", "Confirm reset", JOptionPane.YES_NO_OPTION);
		if (confirm == 0){
			for (Object w : newlist.toArray()){
				((Word)w).right = 0;
				((Word)w).wrong = 0;
			}
		}
	}
}
