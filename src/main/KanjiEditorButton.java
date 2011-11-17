package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import kanjieditor.KanjiEditor;


/**
 * The kanji editor button.
 */
public class KanjiEditorButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 3830053478257260819L;

	/**
	 * Instantiates a new kanji editor button.
	 */
	public KanjiEditorButton(){
		super("Kanji editor");
		addActionListener(this);
	}

	/**
	 * Creates a new kanji editor window
	 */
	public void actionPerformed(ActionEvent arg0) {
		new KanjiEditor();
	}
}
