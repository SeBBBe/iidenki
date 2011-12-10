package kanjieditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import vocab.Kanji;


/**
 * The 'new' menu item.
 */
public class NewMenuItem extends JMenuItem implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1903575247360960316L;
	
	/** The editor. */
	private KanjiEditor editor;
	
	/**
	 * Instantiates a new new menu item.
	 *
	 * @param wordEditor the word editor
	 */
	public NewMenuItem(KanjiEditor wordEditor){
		super("New");
		this.editor = wordEditor;
		addActionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		int confirm = JOptionPane.showConfirmDialog(null, "Clear the kanji list and create a new one?", "Create new list", JOptionPane.YES_NO_OPTION);
		if (confirm == 0){
			ArrayList<Kanji> newlist = new ArrayList<Kanji>();
			editor.load(newlist);
		}
	}
}
