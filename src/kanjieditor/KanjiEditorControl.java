package kanjieditor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import vocab.Kanji;

public class KanjiEditorControl {
	
	public static void saveToFile(File file, ArrayList<Kanji> klist) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(klist);
	}
	
	public static ArrayList<Kanji> loadFromFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
		ArrayList<Kanji> newlist =(ArrayList<Kanji>)in.readObject();
		return newlist;
	}
}
