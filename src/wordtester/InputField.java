package wordtester;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * The field where the user inputs his guess
 */
public class InputField extends JTextField implements ActionListener{

	private static final long serialVersionUID = 4267111445454944703L;
	private TestHandler th;

	/**
	 * Instantiates a new input field.
	 *
	 * @param th the TestHandler
	 */
	public InputField(TestHandler th) {
		this.th = th;
		addActionListener(this);
		Font f = getFont();
		Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+16);
		setFont(f2);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		th.check(getText());
		setText("");
	}
}