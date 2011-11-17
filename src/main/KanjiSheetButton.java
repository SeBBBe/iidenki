package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import sheets.KanjiSheets;


/**
 * The kanji sheet button
 */
public class KanjiSheetButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 466992367436781314L;

	/**
	 * Instantiates a new kanji sheet button.
	 */
	public KanjiSheetButton(){
		super("Generate kanji practice sheets");
		addActionListener(this);
	}

	/**
	 * Launches the kanji sheet application
	 */
	public void actionPerformed(ActionEvent arg0) {
		new KanjiSheets();
	}
}
