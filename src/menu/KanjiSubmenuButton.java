package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;


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
