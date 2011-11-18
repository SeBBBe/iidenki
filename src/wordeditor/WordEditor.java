package wordeditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


/**
 * The main Word Editor application
 */
public class WordEditor{
	
	/** The WordList */
	ArrayList wlist;
	
	/** The GraphicalList */
	GraphicalList glist;
	
	/**
	 * Instantiates a new word editor.
	 *
	 * @param wlist the WordList to edit
	 */
	public WordEditor(ArrayList wlist){
		this.wlist = wlist;
		JFrame frame = new JFrame("Vocabulary editor");
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//adds the menu bar
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new NewMenuItem(this));
		fileMenu.add(new OpenMenuItem(this));
		fileMenu.add(new SaveMenuItem(wlist));
		menu.add(fileMenu);
		
		//adds the editable properties
		JPanel proppanel = new JPanel();
		frame.add(proppanel, BorderLayout.EAST);
		JTextField romaji = new JTextField(15);
		JTextField kana = new JTextField();
		JTextField kanji = new JTextField();
		JRadioButton noun = new JRadioButton("noun");
		noun.setSelected(true);
		JRadioButton ruv = new JRadioButton("ru-verb");
		JRadioButton uv = new JRadioButton("u-verb");
		JRadioButton iv = new JRadioButton("irregular verb");
		JRadioButton naa = new JRadioButton("na-adjective");
		JRadioButton ia = new JRadioButton("i-adjective");
		JRadioButton[] buttonlist = new JRadioButton[6];
		buttonlist[0] = noun;
		buttonlist[1] = ruv;
		buttonlist[2] = uv;
		buttonlist[3] = iv;
		buttonlist[4] = naa;
		buttonlist[5] = ia;

		//adds the scrolling vocabulary list
		JPanel listpanel = new JPanel();
		frame.add(listpanel, BorderLayout.WEST);
		glist = new GraphicalList(wlist, romaji, kana, kanji, buttonlist);
		JScrollPane scrollPane = new JScrollPane(glist);
		scrollPane.setPreferredSize(new Dimension(200,400));
		listpanel.add(scrollPane);
		
		Font f = kanji.getFont();
		Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+16);
		kanji.setFont(f2);
		proppanel.setLayout(new BoxLayout(proppanel, BoxLayout.PAGE_AXIS));
		proppanel.add(new JLabel("Romaji writing"));
		proppanel.add(romaji);
		proppanel.add(new JLabel("Kana writing"));
		proppanel.add(kana);
		proppanel.add(new JLabel("Kanji writing"));
		proppanel.add(kanji);
		ButtonGroup gb = new ButtonGroup();
		gb.add(noun);gb.add(ruv);gb.add(uv);gb.add(iv);gb.add(naa);gb.add(ia);
		proppanel.add(noun);
		proppanel.add(ruv);
		proppanel.add(uv);
		proppanel.add(iv);
		proppanel.add(naa);
		proppanel.add(ia);
		Dimension fbox = new Dimension(5,100);
		proppanel.add(new Box.Filler(fbox, fbox, fbox));
		JPanel butpanel = new JPanel();
		JButton neww = new NewButton(glist);
		butpanel.add(neww);
		JButton delete = new DeleteButton(glist);
		butpanel.add(delete);
		proppanel.add(butpanel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Loads a new WordList into the editor
	 *
	 * @param newlist the new list
	 */
	public void load(ArrayList newlist) {
		wlist.clear();
		wlist.addAll(newlist);
		glist.updateList();
		glist.clearFields();
	}	
}
