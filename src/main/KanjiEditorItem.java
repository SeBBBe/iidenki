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
import vocab.Kanji;
import vocab.Word;
import wordtester.DynamicTest;
import wordtester.SimpleTest;
import wordtester.Tester;

public class KanjiEditorItem extends SubMenuEntry{
	
	public KanjiEditorItem(){
		super("Kanji editor",new File("kanji_editor_menu.gif"));
	}

	public void run(){
		new KanjiEditor();
	}
}
