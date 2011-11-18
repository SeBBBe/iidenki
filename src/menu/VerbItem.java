package menu;

import grammar.VerbConjugator;

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

public class VerbItem extends SubMenuEntry{
	
	public VerbItem(){
		super("Verb conjugator","verb_menu.gif");
	}

	public void run(){
		new VerbConjugator();
	}
}
