package kanjieditor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import vocab.Kanji;

public class KanjiEditorControl {
	
	public static void saveToFile(File file, List<Kanji> klist) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(klist);
	}
	
	public static List<Kanji> loadFromFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
		List<Kanji> newlist = (List<Kanji>)in.readObject();
		return newlist;
	}
}
