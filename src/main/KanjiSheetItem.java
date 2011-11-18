package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import kanjieditor.KanjiEditor;
import kanjitester.KanjiTester;
import sheets.KanjiSheets;
import vocab.Kanji;
import vocab.Word;
import wordtester.DynamicTest;
import wordtester.SimpleTest;
import wordtester.Tester;

public class KanjiSheetItem extends SubMenuEntry{
	
	public KanjiSheetItem(){
		super("Make kanji practice sheets",new File("kanji_sheet_menu.gif"));
	}

	public void run(){
		new KanjiSheets();
	}
}
