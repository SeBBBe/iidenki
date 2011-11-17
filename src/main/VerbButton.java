package main;
import grammar.VerbConjugator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


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
