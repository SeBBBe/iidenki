package main;
import grammar.VerbConjugator;

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
 * The flashcard button
 */
public class VerbButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 4725080651066764126L;

	/**
	 * Instantiates a new flashcard button.
	 */
	public VerbButton(){
		super("Verb conjugation");
		addActionListener(this);
	}

	/**
	 * Launches the flashcard application
	 */
	public void actionPerformed(ActionEvent arg0) {
		new VerbConjugator();
	}
}
