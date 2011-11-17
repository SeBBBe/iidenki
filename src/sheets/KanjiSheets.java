package sheets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import vocab.Kanji;
import wordtester.DynamicTest;
import wordtester.SimpleTest;

public class KanjiSheets {
	public KanjiSheets(){
		String[] possibilities = {"Sheet including all kanji", "Sheet including the latest kanji", "Sheet with randomly selected kanji", "Random sheet (later = more likely)"};
		Object[] inc = new Object[3];
		inc[0] = "Specify what kind of sheet you want to generate.";
		JCheckBox eng = new JCheckBox("Include English translation");
		JCheckBox read = new JCheckBox("Include reading");
		read.setSelected(true);
		inc[1] = eng;
		inc[2] = read;
		String s = (String)JOptionPane.showInputDialog(
		                    null,
		                    inc,
		                    "Practice sheet generator",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "Sheet including the latest kanji");
		if (s == null){
			return;
		}
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Open kanji list");
	    chooser.showOpenDialog(null);
	    File file = chooser.getSelectedFile();
	    ArrayList<Kanji> newlist;
	    if (file == null){return;}
	   
		try{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			newlist =(ArrayList<Kanji>)in.readObject();
			KanjiSheetHTMLPrinter print = new KanjiSheetHTMLPrinter(new File("kanjisheet.htm"));
			ArrayList<Kanji> sheetlist = new ArrayList<Kanji>();
			
			if(s == possibilities[0]){
				sheetlist = newlist;
				print.print("Sheet type: all kanji");
			}else if(s == possibilities[1]){
				String num = JOptionPane.showInputDialog(null, "How many of the latest kanji do you want to include?");
				int n = 20;
				try{
					n = Integer.parseInt(num);
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, "Invalid value. Will include 20 kanji.");
					n = 20;
				}
				if (n > newlist.size()){
					JOptionPane.showMessageDialog(null, "The number is larger than the size of the list! All kanji will be included.");
					sheetlist = newlist;
				}
				int total = newlist.size();
				int startvalue = total - n;
				for (int i = 0; i < n; i++){
					sheetlist.add(newlist.get(i + startvalue));
				}
				print.print("Sheet type: latest kanji");
			}else if(s == possibilities[2]){
				String num = JOptionPane.showInputDialog(null, "How many randomly selected kanji do you want to include?");
				int n = 20;
				try{
					n = Integer.parseInt(num);
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, "Invalid value. Will include 20 kanji.");
					n = 20;
				}
				for (int i = 0; i < n; i++){
					Random generator = new Random();
					sheetlist.add(newlist.get(generator.nextInt(newlist.size())));
				}
				print.print("Sheet type: randomized sheet");
			}else if(s == possibilities[3]){
				String num = JOptionPane.showInputDialog(null, "How many randomly selected kanji do you want to include?");
				int n = 20;
				try{
					n = Integer.parseInt(num);
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, "Invalid value. Will include 20 kanji.");
					n = 20;
				}
				int total = newlist.size();
				int i = 0;
				while (i < n){
					Random generator = new Random();
					int selection = generator.nextInt(newlist.size());
					if (generator.nextDouble() < ((double)selection/total)){
						sheetlist.add(newlist.get(selection));
						i++;
					}
				}
				print.print("Sheet type: weighted randomized sheet");
			}
			print.print("number of kanji: " + sheetlist.size());
			print.generateEntries(sheetlist, eng.isSelected(), read.isSelected());
			print.close();
			JOptionPane.showMessageDialog(null, "Done! The file can be found as 'kanjisheet' in the same place as the\niidenki program file.");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error loading file!");
			e.printStackTrace();
		}
	}
}
