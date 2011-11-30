package kanjieditor;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vocab.Kanji;


/**
 * The Class GraphicalList.
 */
public class GraphicalList extends JList implements ListSelectionListener, DocumentListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -597910668731583303L;
	
	/** The reading. */
	private JTextField reading;
	
	/** The translation. */
	private JTextField translation;
	
	/** The klist. */
	public ArrayList<Kanji> klist;
	
	/** The locked. */
	private boolean locked;
	
	/**
	 * Instantiates a new graphical list.
	 *
	 * @param klist the klist
	 * @param reading the reading
	 * @param translation the translation
	 */
	public GraphicalList(ArrayList<Kanji> klist, JTextField reading, JTextField translation){
		super();
		Font f = getFont();
		Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+16);
		if (System.getProperty("os.name").toLowerCase().equals("linux")){ //linux default font doesn't work for kanji in ubuntu, change to java default
			f2 = new Font("Dialog", f.getStyle(), f.getSize()+16);
		}
		setFont(f2);
		this.klist = klist;
		this.reading = reading;
		this.translation = translation;
		updateList();
		addListSelectionListener(this);
		reading.getDocument().addDocumentListener(this);
		translation.getDocument().addDocumentListener(this);
		locked = false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	public void valueChanged(ListSelectionEvent arg0){
		locked = true;
		int index = getSelectedIndex();
		if (index != -1){
			reading.setText(klist.get(reverseIndex(index)).reading);
			translation.setText(klist.get(reverseIndex(index)).translation);
		}
		locked = false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	public void changedUpdate(DocumentEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	public void insertUpdate(DocumentEvent arg0) {
		if (!locked){
			int index = getSelectedIndex();
			Kanji changed = klist.get(reverseIndex(index));
			changed.reading = reading.getText();
			changed.translation = translation.getText();
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	public void removeUpdate(DocumentEvent arg0) {
		insertUpdate(null);
	}

	/**
	 * Adds the kanji to the graphical view.
	 *
	 * @param newkanji the new kanji
	 */
	public void addWord(Kanji newkanji) {
		locked = true;
		klist.add(newkanji);
		updateList();
		setSelectedIndex(0);
		locked = false;
	}

	/**
	 * Delete kanji.
	 *
	 * @param index the index of the word
	 */
	public void deleteWord(int index) {
		klist.remove(reverseIndex(index));
		updateList();
	}
	
	/**
	 * Clear fields.
	 */
	public void clearFields(){
		locked = true;
		reading.setText("");
		translation.setText("");
		locked = false;
	}

	/**
	 * Update list with the kanji list.
	 */
	public void updateList() {
		ArrayList list2 = new ArrayList();
		list2.addAll(klist);
		Collections.reverse(list2);
		setListData(list2.toArray());
	}
	
	public int reverseIndex(int index){
		return klist.size() - index - 1;
	}
}