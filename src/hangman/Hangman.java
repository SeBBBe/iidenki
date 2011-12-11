package hangman;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import sys.FontManager;
import vocab.Word;

import menu.SubMenuEntry;
import menu.iidenki;

public class Hangman {
	
	private ImageIcon icon;
	private JLabel picLabel;
	private ArrayList<Word> newlist;
	public int level;
	private JFrame frame;
	private InputField ifi;
	private GameHandler gh;
	
	public Hangman(){
		level = 1;
		JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Open vocabulary list");
	    chooser.showOpenDialog(null);
    	File file = chooser.getSelectedFile();
	    if (file == null){return;}
		try{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			newlist = (ArrayList<Word>)in.readObject();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error loading file!");
			e.printStackTrace();
		}
		
		frame = new JFrame("Hangman");
		frame.setResizable(false);
		JPanel panel = new JPanel();
		frame.add(panel);
		
		BufferedImage myPicture = null;
		try {
			if (!iidenki.IS_JAR) {myPicture = ImageIO.read(new File("hangman1.gif"));}
			if (iidenki.IS_JAR) {myPicture = ImageIO.read(this.getClass().getResource("/" + "hangman1.gif"));}
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(myPicture);
		picLabel = new JLabel(icon);
		panel.add(picLabel);
		
		JPanel sidepanel = new JPanel();
		sidepanel.setLayout(new GridLayout(4,1));
		panel.add(sidepanel);
		
		gh = new GameHandler(newlist);
		JLabel wrongfield = new JLabel();
		JLabel partial = new JLabel();
		partial.setFont(FontManager.getKanjiFont(partial.getFont()));
		
		ifi = new InputField(gh, wrongfield, partial, this);
		sidepanel.add(new JLabel("Please input one character for your guess"));
		sidepanel.add(partial);
		sidepanel.add(ifi);
		sidepanel.add(wrongfield);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void nextLevel(){
		level++;
		if (level >= 10){
			level = 10;
			ifi.setEnabled(false);
		}
		
		Runnable changePic = new Runnable() {
			public void run(){
				String pic = "hangman" + level + ".gif";
				BufferedImage myPicture = null;
				try {
					if (!iidenki.IS_JAR) {myPicture = ImageIO.read(new File(pic));}
					if (iidenki.IS_JAR) {myPicture = ImageIO.read(this.getClass().getResource("/" + pic));}
				} catch (IOException e) {
					e.printStackTrace();
				}
				icon.setImage(myPicture);
				picLabel = new JLabel(icon);
				frame.add(picLabel);
				frame.setVisible(true);
				if (level == 10){
					JOptionPane.showMessageDialog(frame, gh.loseText());
					level = 0;
					nextLevel();
					ifi.generateNew();
				}
			}
		 };

		 SwingUtilities.invokeLater(changePic);
	}
}
