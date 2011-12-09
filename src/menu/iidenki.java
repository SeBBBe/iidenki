package menu;
import javax.swing.UIManager;

/**
 * main application class
 */
public class iidenki {
	
	/** Set true if packaging a JAR, will make pictures work */
	public static final boolean IS_JAR = true;
	
	/**
	 * invokes the main menu.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		setStyle();
		new MainMenu();
	}

	private static void setStyle() {
		try {
        UIManager.setLookAndFeel(
        	UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e){
	    	e.printStackTrace();
	    }
	}
}