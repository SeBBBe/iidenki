package menu;

import kanjieditor.KanjiEditor;

public class KanjiEditorItem extends SubMenuEntry{
	
	public KanjiEditorItem(){
		super("Kanji editor","kanji_editor_menu.gif");
	}

	public void run(){
		new KanjiEditor();
	}
}
