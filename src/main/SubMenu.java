package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class SubMenu implements ActionListener{
	
	JLabel picLabel;
	ArrayList<SubMenuEntry> entries;
	ImageIcon icon;
	JFrame frame;
	JPanel panel;
	String source;
	ButtonGroup bg;

	public SubMenu(ArrayList<SubMenuEntry> entries){
		this.entries = entries;
		init();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(entries.get(0).getPic());
			//myPicture = ImageIO.read(this.getClass().getResource("/iidenki.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(myPicture);
		picLabel = new JLabel(icon);
		panel.add(picLabel);
		
		addAndShow(entries, panel);
	}

	private void init() {
		frame = new JFrame("iidenki");
		frame.setResizable(false);
		panel = new JPanel();
		frame.add(panel);
	}

	private void addAndShow(ArrayList<SubMenuEntry> entries, JPanel panel) {
		JPanel butpanel = new JPanel();
		butpanel.setLayout(new BoxLayout(butpanel, BoxLayout.PAGE_AXIS));
		panel.add(butpanel);
		
		bg = new ButtonGroup();
		for (SubMenuEntry e : entries){
			butpanel.add(e.rb);
			bg.add(e.rb);
			e.rb.addActionListener(this);
			e.rb.getModel().setActionCommand(e.rb.getText());
		}
		
		JRadioButton first = entries.get(0).rb;
		first.setSelected(true);
		bg.setSelected(first.getModel(), true);
		
		JButton ok = new JButton("OK");
		panel.add(ok);
		ok.addActionListener(this);
		
		frame.pack();
	    frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton){
			executeSelection();
			return;
		}
		JRadioButton sourceb = (JRadioButton)arg0.getSource();
		source = sourceb.getText();
		
		Runnable changePic = new Runnable() {
			public void run(){
				SubMenuEntry entry = null;
				for (SubMenuEntry e : entries){
					if (e.getText().equals(source)){
						entry = e;
						break;
					}
				}
				BufferedImage myPicture = null;
				try {
					//myPicture = ImageIO.read(new File("test.gif"));
					myPicture = ImageIO.read(entry.getPic());
					//myPicture = ImageIO.read(this.getClass().getResource("/iidenki.gif"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				icon.setImage(myPicture);
				picLabel = new JLabel(icon);
				frame.add(picLabel);
				frame.setVisible(true);
			}
		 };

		 SwingUtilities.invokeLater(changePic);
	}

	private void executeSelection() {
		SubMenuEntry entry = null;
		String selected = bg.getSelection().getActionCommand();
		for (SubMenuEntry e : entries){
			if (selected.equals(e.rb.getText())){
				e.run();
				break;
			}
		}
	}
}
