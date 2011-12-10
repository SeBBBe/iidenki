package menu;

import sheets.KanjiSheets;

public class KanjiSheetItem extends SubMenuEntry{
	
	public KanjiSheetItem(){
		super("Make kanji practice sheets","kanji_sheet_menu.gif");
	}

	public void run(){
		new KanjiSheets();
	}
}
