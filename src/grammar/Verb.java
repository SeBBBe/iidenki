package grammar;

/**
 * represents a Japanese verb
 */
public class Verb {

	private String verb;
	private boolean ruverb;
	private static String[] allowedEndings = {"ku", "ru", "u", "gu", "nu", "mu", "bu", "tsu", "su"};
	private static String[] PASTEndings = {"ita", "tta", "tta", "ida", "nda", "nda", "nda", "tta", "shita"};
	
	/** static selector for past form */
	public static int PAST = 0;
	
	/** static selector for negative form  */
	public static int NEGATIVE = 1;
	
	/** static selector for gerund form  */
	public static int GERUND = 2;
	
	/** static selector for hortative form  */
	public static int HORTATIVE = 3;
	
	/** static selector for imperative form . */
	public static int IMPERATIVE = 4;
	
	/** static selector for past negative form  */
	public static int PAST_NEGATIVE = 5;
	
	/**
	 * Instantiates a new verb.
	 *
	 * @param verb the verb
	 * @throws Exception is thrown if the string is not found to be a Japanese verb
	 */
	public Verb(String verb) throws Exception{
		this.verb = verb;
		ruverb = false;
		performCheck();
	}
	
	private void performCheck() throws Exception {
		boolean pass = false;
		for (String a : allowedEndings){
			if (ending().equals(a)) pass=true;
		}
		if (!pass) throw new Exception();
	}

	/**
	 * returns the ending of the verb
	 *
	 * @return the ending
	 */
	public String ending(){
		String ending = "dummy value";
		if (verb.length() >= 3){
			ending = verb.substring(verb.length() - 3, verb.length());
		}
		if (!ending.equals("tsu")){
			ending = verb.substring(verb.length() - 2, verb.length());
		}
		if (ending.equals("au") || ending.equals("iu") || ending.equals("ou") || ending.equals("uu") || ending.equals("eu")){
			ending = "u";
		}
		return ending;
	}
	
	/**
	 * returns the verb stem
	 *
	 * @return the verb stem
	 */
	public String stem(){
		if (isIrregular()){
			return "<NO STEM>";
		}
		int endlength = ending().length();
		String stem = verb.substring(0, verb.length()-endlength);
		return stem;
	}
	
	private String lastConsonant(){
		if (ending().equals("u")) {return "";}
		return ending().charAt(0) + "";
	}
	
	/**
	 * returns a string representation of the verb class
	 *
	 * @return the verb class
	 */
	public String verbclass(){
		if (isIrregular()) {return "irregular verb";}
		return (ruverb ? "ru-verb" : "u-verb with " + ending() + " ending");
	}
	
	/**
	 * Checks if is a ru-verb.
	 *
	 * @return true, if is a ru-verb
	 */
	public boolean isRuverb(){
		return ruverb;
	}
	
	/**
	 * Sets ru-verb status
	 *
	 * @param state true to set as ru-verb
	 */
	public void setRuverb(boolean state){
		ruverb = state;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return verb;
	}
	
	/**
	 * Returns the selected conjugated form of the verb
	 *
	 * @param form the type of conjugation to perform, select using the static selectors in this class
	 * @return the conjugated form
	 */
	public String conjugated(int form){
		int n = 0;
		for (String a : allowedEndings){
			if (ending().equals(a)) break;
			n++;
		}
		
		if (form == PAST){
			if (last4().equals("kuru")) {return before4() + "kita";}
			if (last4().equals("suru")) {return before4() + "shita";}
			if (verb.equals("iku")) {return "itta";}
			if (ruverb) {return (stem() + "ta");}
			return stem() + PASTEndings[n];
		}
		
		if (form == NEGATIVE){
			if (verb.equals("aru")){return "nai";}
			if (last4().equals("kuru")) {return before4() + "konai";}
			if (last4().equals("suru")) {return before4() + "shinai";}
			if (ruverb) {return (stem() + "nai");}
			if (ending().equals("u")){return stem() + "wanai";}
			return stem() + lastConsonant() + "anai";
		}
		
		if (form == GERUND){
			String past = conjugated(PAST);
			return past.substring(0, past.length()-1) + "e";
		}
		
		if (form == HORTATIVE){
			if (last4().equals("kuru")) {return before4() + "koyou";}
			if (last4().equals("suru")) {return before4() + "shiyou";}
			if (ruverb) {return (stem() + "you");}
			return stem() + lastConsonant() + "oo";
		}
		
		if (form == IMPERATIVE){
			if (last4().equals("kuru")) {return before4() + "koi";}
			if (last4().equals("suru")) {return before4() + "shiro";}
			if (ruverb) {return (stem() + "ro");}
			return stem() + lastConsonant() + "e";
		}
		
		if (form == PAST_NEGATIVE){
			String neg = conjugated(NEGATIVE);
			return neg.substring(0, neg.length()-1) + "katta";
		}
		
		return "<PARAMETER ERROR>";
	}
	
	/**
	 * Checks if it is an irregular verb
	 *
	 * @return true, if it is irregular
	 */
	public boolean isIrregular(){
		if (verb.length() < 4){return false;}
		if (last4().equals("kuru")) {return true;}
		if (last4().equals("suru")) {return true;}
		return false;
	}
	
	private String last4(){
		if (verb.length() < 4){return "dummy value";}
		return verb.substring(verb.length()-4, verb.length());
	}
	
	private String before4(){
		if (verb.length() < 4){return "dummy value";}
		return verb.substring(0, verb.length()-4);
	}
}
