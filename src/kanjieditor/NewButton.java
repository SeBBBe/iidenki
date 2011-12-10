package kanjieditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vocab.Kanji;


/**
 * The 'new' button.
 */
public class NewButton extends JButton implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3162569211849259948L;
	
	/** The glist. */
	private GraphicalList glist;

	/**
	 * Instantiates a new new button.
	 *
	 * @param glist the glist
	 */
	public NewButton(GraphicalList glist){
		super("New kanji");
		addActionListener(this);
		this.glist = glist;
	}

	/**
	 * Creates a new word and adds it to the GraphicalList.
	 *
	 * @param arg0 the arg0
	 */
	public void actionPerformed(ActionEvent arg0) {
		String kanji = JOptionPane.showInputDialog(null, "Please enter the new kanji/kanji compound");
		if (kanji != null){
			if (kanji.length() > 0){
				Kanji newkanji = new Kanji(kanji);
				glist.addWord(newkanji);
			}
		}
	}
	
	
}
