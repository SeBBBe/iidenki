package kanjieditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import vocab.Kanji;


/**
 * The delete button.
 */
public class DeleteButton extends JButton implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5269401400605635128L;
	
	/** The glist. */
	private GraphicalList glist;

	/**
	 * Instantiates a new delete button.
	 *
	 * @param glist the GraphicalList to delete from
	 */
	public DeleteButton(GraphicalList glist){
		super("Delete");
		addActionListener(this);
		this.glist = glist;
	}

	/**
	 * Deletes the selected word from the GraphicalList view.
	 *
	 * @param arg0 the arg0
	 */
	public void actionPerformed(ActionEvent arg0) {
		int index = glist.getSelectedIndex();
		if (index != -1){
			Kanji kanji = glist.klist.get(index);
			int confirm = JOptionPane.showConfirmDialog(null, "Delete the kanji '" + kanji + "'?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
				if (confirm == 0){
					glist.deleteWord(index);
					glist.clearFields();
				}
		}
	}
	
	
}
