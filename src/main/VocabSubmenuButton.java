package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import kanjieditor.KanjiEditor;


/**
 * The vocabulary submenu button.
 */
public class VocabSubmenuButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 3830053478257260819L;

	/**
	 * Instantiates a new vocabulary submenu button.
	 */
	public VocabSubmenuButton(){
		super("Vocabulary");
		addActionListener(this);
	}

	/**
	 * Creates a new vocabulary submenu window
	 */
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<SubMenuEntry> entries = new ArrayList<SubMenuEntry>();
		entries.add(new VocabTestItem());
		entries.add(new WordEditorItem());
		new SubMenu(entries);
	}
}
