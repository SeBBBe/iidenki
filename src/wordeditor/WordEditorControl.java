package wordeditor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import vocab.Word;

public class WordEditorControl {
	
	public static void saveToFile(File file, ArrayList<Word> wlist) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(wlist);
	}
	
	public static ArrayList<Word> loadFromFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
		ArrayList<Word> newlist =(ArrayList<Word>)in.readObject();
		return newlist;
	}
}
