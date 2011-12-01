package menu;
import wordtester.WordQuizMenu;

public class VocabTestItem extends SubMenuEntry{
	
	public VocabTestItem(){
		super("Vocabulary quiz","vocab_test_menu.gif");
	}

	public void run(){
		new WordQuizMenu();
	}
}
