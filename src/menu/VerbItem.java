package menu;

import grammar.VerbConjugator;

public class VerbItem extends SubMenuEntry{
	
	public VerbItem(){
		super("Verb conjugator","verb_menu.gif");
	}

	public void run(){
		new VerbConjugator();
	}
}
