package kanjitester;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vocab.Kanji;
import wordtester.Tester;


/**
 * The main kanji tester graphical application
 */
public class KanjiTester extends JFrame{
	
	public List<Kanji> klist;
	public File file;

	public KanjiTester(List<Kanji> klist, Tester<Kanji> tester, File file, boolean eng){
		super("Kanji test");
		this.file = file;
		this.klist = klist;
		setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(8,1));
		
		panel.add(new JLabel("What is the reading of this kanji (compound)?"));
		JLabel wt = new JLabel();
		wt.setFont(sys.FontManager.getKanjiFont(wt.getFont()));
		panel.add(wt);
		JButton[] buttons = new JButton[5];
		for (int i = 0; i < 5; i++){
				buttons[i] = new JButton(i + "");
				panel.add(buttons[i]);
		}

		JLabel stats = new JLabel("はじめましょう！");
		TestHandler th = new TestHandler(klist, wt, stats, this, tester, buttons, eng);
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
}
