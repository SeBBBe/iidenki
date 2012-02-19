package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vocab.Word;

public class GameHandler {

	private List<Word> list;
	private List<String> correct;
	private List<String> wrong;
	private Word currentword;
	private String partialtext;
	
	public GameHandler(List<Word> list){
		this.list = list;
		correct = new ArrayList<String>();
		wrong = new ArrayList<String>();
		generateNew();
	}
	
	public void generateNew(){
		Random r = new Random();
		int choice = r.nextInt(list.size());
		currentword = list.get(choice);
		partialtext = "";
		for (int i = 0; i < currentword.getKana().length(); i++){
			partialtext = partialtext + "_ ";
		}
		correct = new ArrayList<String>();
		wrong = new ArrayList<String>();
	}
	
	public String partialText(){
		return partialtext;
	}
	
	public String wrongText(){
		if (wrong.size() == 0){
			return "";
		}
		String w = "wrong: ";
		for (String a : wrong){
			w = w + a + " ";
		}
		return w;
	}
	
	public int guess(String guess){
		if (guess.length() != 1){
			return 3;
		}
		if (wrong.contains(guess) || correct.contains(guess)){
			return 2;
		}
		boolean right = currentword.getKana().contains(guess);
		if (right){
			correct.add(guess);
			constructPartial();
		}else{
			wrong.add(guess);
		}
		return right == true ? 1 : 0;
	}

	private void constructPartial() {
		partialtext = "";
		String word = currentword.getKana();
		for (int i = 0; i < word.length(); i++){
			if (correct.contains(word.charAt(i) + "")){
				partialtext = partialtext + word.charAt(i) + " ";
			}else{
				partialtext = partialtext + "_ ";
			}
		}
	}

	public Object winText() {
		return "Congratulations!\nThe word was\n" + currentword.getKana() + "\nwhich means\n" + currentword;
	}
	
	public Object loseText() {
		return "Sorry! You didn't make it.\nThe word was\n" + currentword.getKana() + "\nwhich means\n" + currentword + "\nBetter luck next time.";
	}
}
