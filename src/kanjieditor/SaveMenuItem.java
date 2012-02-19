package kanjieditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import vocab.Kanji;


/**
 * The save menu item.
 */
public class SaveMenuItem extends JMenuItem implements ActionListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3364725120224414027L;
	
	/** The klist. */
	private List<Kanji> klist;
	
	/**
	 * Instantiates a new save menu item.
	 *
	 * @param klist2 the kanji list
	 */
	public SaveMenuItem(List<Kanji> klist2){
		super("Save as");
		this.klist = klist2;
		addActionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Save kanji list");
		chooser.showSaveDialog(null);
		File file = chooser.getSelectedFile();
		if (file == null){return;}
		try{
			KanjiEditorControl.saveToFile(file, klist);
		}catch(Exception f){
			f.printStackTrace();
			JOptionPane.showMessageDialog(null, "An error occured while saving! List not saved.");
		}
	}
}
