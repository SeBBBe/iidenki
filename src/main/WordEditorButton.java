package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import vocab.Word;
import wordeditor.WordEditor;


/**
 * The word editor button.
 */
public class WordEditorButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 3830053478257260819L;

	/**
	 * Instantiates a new word editor button.
	 */
	public WordEditorButton(){
		super("Vocabulary editor");
		addActionListener(this);
	}

	/**
	 * Creates a new word editor window
	 */
	public void actionPerformed(ActionEvent arg0) {
		new WordEditor(new ArrayList<Word>());
	}
}
