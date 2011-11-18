package main;
import java.io.File;

import javax.swing.JRadioButton;

//EXTEND THIS CLASS
public class SubMenuEntry{
	
	private File pic;
	private String text;
	public JRadioButton rb;

	public SubMenuEntry(String text, File pic){
		this.text = text;
		this.pic = pic;
		rb = new JRadioButton(text);
	}
	
	public String getText(){
		return text;
	}
	
	public File getPic(){
		return pic;
	}
	
	public void run(){
		System.out.println("Run command issued for: " + getText());
	}
}
