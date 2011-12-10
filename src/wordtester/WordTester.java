package wordtester;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import vocab.Word;


/**
 * The main word tester graphical application
 */
public class WordTester extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5461112199107395509L;
	
	/** The WordList used in the current test */
	public ArrayList<Word> wlist;
	
	/** The file containing the current list of words */
	public File file;
	
	/** The text string of the currently selected radio buton */
	public String buttonselection;

	/**
	 * Instantiates a new word tester.
	 *
	 * @param wlist the WordList to use
	 * @param tester the selected test mode
	 * @param file the file containing the word list
	 */
	public WordTester(ArrayList wlist, Tester tester, File file, boolean testtype){
		super("Vocabulary test");
		this.file = file;
		this.wlist = wlist;
		buttonselection = "";
		setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(6,1));
		
		panel.add(new JLabel("Translate the following into Japanese:"));
		JLabel wt = new JLabel();
		panel.add(wt);

		JLabel stats = new JLabel("はじめましょう！");
		TestHandler th = new TestHandler(wlist, wt, stats, this, tester, testtype);
		InputField ifi = new InputField(th);
		panel.add(ifi);
		if (testtype){
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(3,2));
			JRadioButton noun = new JRadioButton("noun");
			JRadioButton ruv = new JRadioButton("ru-verb");
			JRadioButton uv = new JRadioButton("u-verb");
			JRadioButton iv = new JRadioButton("irregular verb");
			JRadioButton naa = new JRadioButton("na-adjective");
			JRadioButton ia = new JRadioButton("i-adjective");
			ButtonGroup gb = new ButtonGroup();
			gb.add(noun);gb.add(ruv);gb.add(uv);gb.add(iv);gb.add(naa);gb.add(ia);
			noun.addActionListener(this);
			ruv.addActionListener(this);
			uv.addActionListener(this);
			iv.addActionListener(this);
			naa.addActionListener(this);
			ia.addActionListener(this);
			panel2.add(noun);
			panel2.add(ruv);
			panel2.add(uv);
			panel2.add(iv);
			panel2.add(naa);
			panel2.add(ia);
			panel.add(panel2);
		}
		JButton okButton = new JButton("OK");
		okButton.addActionListener(ifi);
		panel.add(okButton);
		panel.add(stats);
		
		
		pack();
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    int w = getSize().width;
	    int h = getSize().height;
	    int x = (dim.width-w)/2;
	    int y = (dim.height-h)/2;
	    setLocation(x, y);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		buttonselection = arg0.getActionCommand();
	}
}
