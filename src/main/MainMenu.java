package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


/**
 * The main menu.
 */
public class MainMenu {

	/**
	 * Instantiates a new main menu.
	 */
	public MainMenu(){
		JFrame frame = new JFrame("iidenki");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.add(panel);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("iidenki.gif"));
			//myPicture = ImageIO.read(this.getClass().getResource("/iidenki.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		panel.add(picLabel);
		
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu menu1 = new JMenu("iidenki");
		menu1.add(new AboutMenuItem());
		menu.add(menu1);
		
		JPanel butpanel = new JPanel();
		butpanel.setLayout(new BoxLayout(butpanel, BoxLayout.PAGE_AXIS));
		panel.add(butpanel);
		butpanel.add(new VocabTestButton());
		butpanel.add(new WordEditorButton());
		butpanel.add(new KanjiTestButton());
		butpanel.add(new KanjiEditorButton());
		butpanel.add(new VerbButton());
		butpanel.add(new KanjiSheetButton());
		//butpanel.add(new FlashButton());
		
		frame.pack();
	    frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
