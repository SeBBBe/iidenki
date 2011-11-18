package menu;
import java.io.File;
import java.net.URL;
import javax.swing.JRadioButton;


public class SubMenuEntry{
	
	private String pic;
	private String text;
	public JRadioButton rb;

	public SubMenuEntry(String text, String pic){
		this.text = text;
		this.pic = pic;
		rb = new JRadioButton(text);
	}
	
	public String getText(){
		return text;
	}
	
	public File getPic(){
		return new File(pic);
	}
	
	public URL getJARPic(){
		return this.getClass().getResource("/" + pic);
	}
	
	public void run(){
		System.out.println("Run command issued for: " + getText());
	}
}
