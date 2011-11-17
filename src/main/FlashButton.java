package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 * The flashcard button
 */
public class FlashButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 4725080651066764126L;

	/**
	 * Instantiates a new flashcard button.
	 */
	public FlashButton(){
		super("Flashcards");
		addActionListener(this);
	}

	/**
	 * Launches the flashcard application
	 */
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "Not yet implemented");
	}
}
