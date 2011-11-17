package grammar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A graphical grid showing conjugations of a verb
 */
//TODO: Auto-generated Javadoc
public class ConjugatorGrid{

	/**
	 * Instantiates a new conjugator grid.
	 *
	 * @param verb the verb
	 */
	public ConjugatorGrid(Verb verb){
		JFrame frame = new JFrame(verb.toString());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9,2));
		frame.add(panel);
		panel.add(new JLabel("Dictionary form: "));
		panel.add(new JLabel(verb.toString()));
		panel.add(new JLabel("Verb class: "));
		panel.add(new JLabel(verb.verbclass()));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel("Negative: "));
		panel.add(new JLabel(verb.conjugated(Verb.NEGATIVE)));
		panel.add(new JLabel("Past: "));
		panel.add(new JLabel(verb.conjugated(Verb.PAST)));
		panel.add(new JLabel("Past negative: "));
		panel.add(new JLabel(verb.conjugated(Verb.PAST_NEGATIVE)));
		panel.add(new JLabel("Gerund: "));
		panel.add(new JLabel(verb.conjugated(Verb.GERUND)));
		panel.add(new JLabel("Hortative: "));
		panel.add(new JLabel(verb.conjugated(Verb.HORTATIVE)));
		panel.add(new JLabel("Imperative: "));
		panel.add(new JLabel(verb.conjugated(Verb.IMPERATIVE)));
		
		frame.pack();
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    int w = frame.getSize().width;
	    int h = frame.getSize().height;
	    int x = (dim.width-w)/2;
	    int y = (dim.height-h)/2;
	    frame.setLocation(x, y);
		frame.setVisible(true);
	}
}
