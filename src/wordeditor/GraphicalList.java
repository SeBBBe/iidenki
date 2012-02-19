package wordeditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vocab.Word;


/**
 * The list of words in the word editor. Does most of the heavy lifting in the editor.
 */
public class GraphicalList extends JList implements ListSelectionListener, DocumentListener, ActionListener{
	
	private static final long serialVersionUID = 7559457405219613772L;
	private JTextField romaji;
	private JTextField kana;
	private JTextField kanji;
	public List<Word> wlist;
	private boolean locked;
	private JRadioButton[] buttonlist;
	
	/**
	 * Instantiates a new graphical list.
	 *
	 * @param wlist the WordList
	 * @param romaji the romaji field
	 * @param kana the kana field
	 * @param kanji the kanji field
	 * @param buttonlist list of radio buttons to watch
	 */
	public GraphicalList(List<Word> wlist, JTextField romaji, JTextField kana, JTextField kanji, JRadioButton[] buttonlist){
		super();
		this.wlist = wlist;
		this.romaji = romaji;
		this.kana = kana;
		this.kanji = kanji;
		this.buttonlist = buttonlist;
		updateList();
		addListSelectionListener(this);
		romaji.getDocument().addDocumentListener(this);
		kana.getDocument().addDocumentListener(this);
		kanji.getDocument().addDocumentListener(this);
		for (JRadioButton butt : buttonlist){
			butt.addActionListener(this);
		}
		locked = false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	public void valueChanged(ListSelectionEvent arg0){
		locked = true;
		int index = getSelectedIndex();
		if (index != -1){
			romaji.setText(((Word) wlist.get(reverseIndex(index))).getRomaji());
			kana.setText(((Word) wlist.get(reverseIndex(index))).getKana());
			kanji.setText(((Word) wlist.get(reverseIndex(index))).getKanji());
			String type = ((Word)wlist.get(reverseIndex(index))).type;
			if (type.equals("noun")){
				buttonlist[0].setSelected(true);
			}
			if (type.equals("ru-verb")){
				buttonlist[1].setSelected(true);
			}
			if (type.equals("u-verb")){
				buttonlist[2].setSelected(true);
			}
			if (type.equals("irregular verb")){
				buttonlist[3].setSelected(true);
			}
			if (type.equals("na-adjective")){
				buttonlist[4].setSelected(true);
			}
			if (type.equals("i-adjective")){
				buttonlist[5].setSelected(true);
			}
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
			Word changed = (Word) wlist.get(reverseIndex(index));
			changed.setRomaji(romaji.getText());
			changed.setKana(kana.getText());
			changed.setKanji(kanji.getText());
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	public void removeUpdate(DocumentEvent arg0) {
		insertUpdate(null);
	}

	/**
	 * Adds the word to the graphical view.
	 *
	 * @param newword the new word
	 */
	public void addWord(Word newword) {
		locked = true;
		wlist.add(newword);
		updateList();
		setSelectedIndex(0);
		locked = false;
	}

	/**
	 * Delete word.
	 *
	 * @param index the index of the word
	 */
	public void deleteWord(int index) {
		wlist.remove(reverseIndex(index));
		updateList();
	}
	
	/**
	 * Clear fields.
	 */
	public void clearFields(){
		locked = true;
		romaji.setText("");
		kana.setText("");
		kanji.setText("");
		locked = false;
	}
	
	public int reverseIndex(int index){
		return wlist.size() - index - 1;
	}

	/**
	 * Update list with the WordList.
	 */
	public void updateList() {
		List<Word> list2 = new ArrayList<Word>();
		list2.addAll(wlist);
		Collections.reverse(list2);
		setListData(list2.toArray());
	}

	public void actionPerformed(ActionEvent arg0) {
		int index = getSelectedIndex();
		Word changed = (Word) wlist.get(reverseIndex(index));
		changed.type = arg0.getActionCommand();
	}
}