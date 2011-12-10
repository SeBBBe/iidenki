package hangman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.iidenki;

public class Hangman {
	
	private ImageIcon icon;
	private JLabel picLabel;
	
	public Hangman(){
		JFrame frame = new JFrame("Hangman");
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
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
