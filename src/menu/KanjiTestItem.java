package menu;
import kanjitester.KanjiQuizMenu;

public class KanjiTestItem extends SubMenuEntry{
	
	public KanjiTestItem(){
		super("Kanji quiz","kanji_test_menu.gif");
	}

	public void run(){
		new KanjiQuizMenu();
	}
}
