/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QuizMenu.java
 *
 * Created on Dec 1, 2011, 4:32:43 PM
 */

package wordtester;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import vocab.Word;

public class WordQuizMenu extends javax.swing.JFrame {

	private ArrayList<Word> newlist;
	private File file;
	private String[] possibilities = {"Test all words", "Test the most difficult words", "Test the latest words"};

	
    /** Creates new form QuizMenu */
    public WordQuizMenu() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(possibilities));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Test grammatical word class");
        jCheckBox1.setSelected(true);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("no file chosen");

        jButton3.setText("Choose vocabulary file");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Reset scores");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jCheckBox2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(15, 15, 15))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        //Reset scores
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        //Browse button
    	JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Open vocabulary list");
	    chooser.showOpenDialog(null);
    	file = chooser.getSelectedFile();
	    if (file == null){return;}
		try{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			newlist =(ArrayList<Word>)in.readObject();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error loading file!");
			e.printStackTrace();
		}
		
		jLabel1.setText(file.toString());
		pack();
		setLocationRelativeTo(null);
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // OK button
    	if (file == null){JOptionPane.showMessageDialog(null, "No file selected!"); return;}
    	if (jCheckBox2.isSelected()){
    		resetList(newlist);
    	}
    	dispose();
    	Tester<Word> test = new SimpleTest<Word>(newlist);
		if(jComboBox1.getSelectedItem() == possibilities[0]){
			test = new SimpleTest<Word>(newlist);
		}else if(jComboBox1.getSelectedItem() == possibilities[1]){
			test = new DynamicTest<Word>(newlist);
		}else if(jComboBox1.getSelectedItem() == possibilities[2]){
			test = new LatestTest<Word>(newlist);
		}
    	new WordTester(newlist, test, file, jCheckBox1.isSelected());
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // English reading
    }
    
	/**
	 * Reset score
	 *
	 * @param newlist the list to reset
	 */
	private static void resetList(ArrayList<Word> newlist) {
		if (newlist == null) {JOptionPane.showMessageDialog(null, "Can't reset scores; invalid list selected"); return;}
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the scores in this list?\nThis information is used to determine which words are the most difficult.", "Confirm reset", JOptionPane.YES_NO_OPTION);
		if (confirm == 0){
			for (Object w : newlist.toArray()){
				((Word)w).right = 0;
				((Word)w).wrong = 0;
			}
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:varinew String[] {"Test all kanji", "Test the most difficult kanji", "Test the latest kanji"}ables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
