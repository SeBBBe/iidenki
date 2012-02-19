package sys;
import java.awt.Font;

public class FontManager {
	public static Font getKanjiFont(Font f){
		Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+16);
		if (System.getProperty("os.name").toLowerCase().equals("linux")){ //linux default font doesn't work for kanji in ubuntu, change to java default
			f2 = new Font("Dialog", f.getStyle(), f.getSize()+16);
		}
		if (System.getProperty("os.name").toLowerCase().equals("windows xp")){ //XP default doesn't like showing kana and kanji
			f2 = new Font("MS UI Gothic", f.getStyle(), f.getSize()+16);
		}
		return f2;
	}
	
	public static Font getHangmanFont(Font f){
		Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+8);
		if (System.getProperty("os.name").toLowerCase().equals("linux")){ //linux default font doesn't work for kanji in ubuntu, change to java default
			f2 = new Font("Dialog", f.getStyle(), f.getSize()+8);
		}
		if (System.getProperty("os.name").toLowerCase().equals("windows xp")){ //XP default doesn't like showing kana and kanji
			f2 = new Font("MS UI Gothic", f.getStyle(), f.getSize()+8);
		}
		return f2;
	}
}
