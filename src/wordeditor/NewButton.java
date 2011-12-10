package wordeditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vocab.Word;


/**
 * The 'new' button.
 */
public class NewButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 3162569211849259948L;
	private GraphicalList glist;

	/**
	 * Instantiates a new new button.
	 *
	 * @param glist the glist
	 */
	public NewButton(GraphicalList glist){
		super("New word");
		addActionListener(this);
		this.glist = glist;
	}

	/**
	 * Creates a new word and adds it to the GraphicalList
	 */
	public void actionPerformed(ActionEvent arg0) {
		String english = JOptionPane.showInputDialog(null, "Please enter the English meaning of the new word");
		if (english != null){
			if (english.length() > 0){
				Word newword = new Word(english, "", "", "");
				glist.addWord(newword);
			}
		}
	}
	
	
}
