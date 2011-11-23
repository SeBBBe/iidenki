package wordeditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import vocab.Word;


/**
 * The open menu item.
 */
public class OpenMenuItem extends JMenuItem implements ActionListener {

	private WordEditor editor;
	
	/**
	 * Instantiates a new open menu item.
	 *
	 * @param wordEditor the word editor
	 */
	public OpenMenuItem(WordEditor wordEditor){
		super("Open");
		this.editor = wordEditor;
		addActionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Open vocabulary list");
	    chooser.showOpenDialog(null);
	    File file = chooser.getSelectedFile();
	    ArrayList<Word> newlist;
	    if (file == null){return;}
		try{
			newlist = WordEditorControl.loadFromFile(file);
			editor.load(newlist);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error loading file!");
		}
	}
}
