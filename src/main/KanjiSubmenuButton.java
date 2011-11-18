package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import kanjieditor.KanjiEditor;


/**
 * The kanji submenu button.
 */
public class KanjiSubmenuButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 3830053478257260819L;

	/**
	 * Instantiates a new kanji submenu button.
	 */
	public KanjiSubmenuButton(){
		super("Kanji");
		addActionListener(this);
	}

	/**
	 * Creates a new kanji submenu window
	 */
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<SubMenuEntry> entries = new ArrayList<SubMenuEntry>();
		entries.add(new KanjiTestItem());
		entries.add(new KanjiEditorItem());
		entries.add(new KanjiSheetItem());
		new SubMenu(entries);
	}
}
