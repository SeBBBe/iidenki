package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;


/**
 * The vocabulary submenu button.
 */
public class GrammarSubmenuButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 3830053478257260819L;

	/**
	 * Instantiates a new vocabulary submenu button.
	 */
	public GrammarSubmenuButton(){
		super("Grammar");
		addActionListener(this);
	}

	/**
	 * Creates a new vocabulary submenu window
	 */
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<SubMenuEntry> entries = new ArrayList<SubMenuEntry>();
		entries.add(new VerbItem());
		new SubMenu(entries);
	}
}
