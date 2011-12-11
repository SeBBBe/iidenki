package menu;
import hangman.Hangman;

public class HangmanItem extends SubMenuEntry{
	
	public HangmanItem(){
		super("Hangman (kana)","hangman_menu.gif");
	}

	public void run(){
		new Hangman();
	}
}
