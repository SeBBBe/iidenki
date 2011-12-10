package menu;

import wordeditor.WordEditor;

public class WordEditorItem extends SubMenuEntry{
	
	public WordEditorItem(){
		super("Vocabulary editor","vocab_editor_menu.gif");
	}

	public void run(){
		new WordEditor();
	}
}
