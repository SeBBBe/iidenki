package hangman;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sys.FontManager;

public class InputField extends JTextField implements ActionListener{
	
	GameHandler gh;
	JLabel wrongfield;
	JLabel partial;
	Hangman hangman;
	
	public InputField(GameHandler gh, JLabel wrongfield, JLabel partial, Hangman hangman){
		this.gh = gh;
		this.wrongfield = wrongfield;
		this.partial = partial;
		this.hangman = hangman;
		setFont(FontManager.getKanjiFont(getFont()));
		partial.setText(gh.partialText());
		wrongfield.setText(gh.wrongText());
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		int i = gh.guess(getText());
		if (i == 0){
			hangman.nextLevel();
		}
		if (i == 3){
			JOptionPane.showMessageDialog(hangman.frame, "Please enter one character only!");
		}
		partial.setText(gh.partialText());
		wrongfield.setText(gh.wrongText());
		setText("");
		checkWin();
	}

	private void checkWin() {
		String p = gh.partialText();
		if (!p.contains("_")){
			JOptionPane.showMessageDialog(hangman.frame, gh.winText());
			setEnabled(false);
			hangman.level = 0;
			hangman.nextLevel();
			generateNew();
		}
	}

	public void generateNew() {
		gh.generateNew();
		partial.setText(gh.partialText());
		wrongfield.setText(gh.wrongText());
		setText("");
		setEnabled(true);
	}
}
