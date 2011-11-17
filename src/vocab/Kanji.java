package vocab;
import java.io.Serializable;


/**
 * Represents a kanji or kanji compound
 */
public class Kanji extends RightWrong implements Serializable {

	private static final long serialVersionUID = 2216391087212502892L;
	
	/** The kanji. */
	public String kanji;
	
	/** The reading. */
	public String reading;
	
	/** The translation. */
	public String translation;
	
	/** The number of correct guesses */
	public int right;
	
	/** The number of wrong guesses */
	public int wrong;
	
	/**
	 * Instantiates a new kanji.
	 *
	 * @param kanji the kanji
	 */
	public Kanji(String kanji){
		this.kanji = kanji;
		reading = "";
		translation = "";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return kanji;
	}

	public boolean check(String text) {
		return text.equals(reading);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0) {
	    Kanji otherword = (Kanji)arg0;
		return (right - wrong) - (otherword.right - otherword.wrong);
	}
}
