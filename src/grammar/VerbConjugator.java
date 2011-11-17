package grammar;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * Asks the user for a verb and shows the result in a ConjugatorGrid
 */
public class VerbConjugator {
	
	/**
	 * Instantiates a new verb conjugator.
	 */
	public VerbConjugator(){
		int confirm = 1;
		String verb = JOptionPane.showInputDialog(null, "Please input a verb in romaji to conjugate (dictionary form)");
		Verb v = null;
		try {
			v = new Verb(verb);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The input value does not conform to Japanese grammar standards for verbs.");
			e.printStackTrace();
		}
		
		if (v.ending().equals("ru") && !v.isIrregular()){
			String last3 = (verb.substring(verb.length()-3, verb.length()));
			String guess = last3.equals("iru") || last3.equals("eru") ? "I think it is" : "I don't think it is";
			confirm = JOptionPane.showConfirmDialog(null, "Is this a ru-verb? (" + guess + ")", "Verb type", JOptionPane.YES_NO_OPTION);
		}
		if (confirm == 0){
			v.setRuverb(true);
		}else{
			v.setRuverb(false);
		}
		
		new ConjugatorGrid(v);
	}
}
