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
import wordeditor.WordEditor;
import wordtester.DynamicTest;
import wordtester.SimpleTest;
import wordtester.Tester;
import wordtester.WordTester;

public class WordEditorItem extends SubMenuEntry{
	
	public WordEditorItem(){
		super("Vocabulary editor",new File("vocab_editor_menu.gif"));
	}

	public void run(){
		new WordEditor(new ArrayList<Word>());
	}
}
