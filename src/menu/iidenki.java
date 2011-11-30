package menu;
import javax.swing.UIManager;

/**
 * main application class
 */
public class iidenki {
	
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