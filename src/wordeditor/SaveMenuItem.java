package wordeditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 * The save menu item.
 */
public class SaveMenuItem extends JMenuItem implements ActionListener {
	
	private ArrayList wlist;
	
	/**
	 * Instantiates a new save menu item.
	 *
	 * @param wlist the WordList
	 */
	public SaveMenuItem(ArrayList wlist){
		super("Save as");
		this.wlist = wlist;
		addActionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Save vocabulary list");
		chooser.showSaveDialog(null);
		File file = chooser.getSelectedFile();
		if (file == null){return;}
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(wlist);
		}catch(Exception f){
			f.printStackTrace();
			JOptionPane.showMessageDialog(null, "An error occured while saving! List not saved.");
		}
	}
}
