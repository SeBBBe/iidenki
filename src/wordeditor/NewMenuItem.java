package wordeditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 * The 'new' menu item.
 */
public class NewMenuItem extends JMenuItem implements ActionListener {

	private WordEditor editor;
	
	/**
	 * Instantiates a new new menu item.
	 *
	 * @param wordEditor the word editor
	 */
	public NewMenuItem(WordEditor wordEditor){
		super("New");
		this.editor = wordEditor;
		addActionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		int confirm = JOptionPane.showConfirmDialog(null, "Clear the vocabulary list and create a new one?", "Create new list", JOptionPane.YES_NO_OPTION);
		if (confirm == 0){
			ArrayList newlist = new ArrayList();
			editor.load(newlist);
		}
	}
}
