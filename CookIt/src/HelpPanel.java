

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class HelpPanel extends JPanel {

	Font titleFont = new Font("Castellar", Font.PLAIN, 50);
	Font normalFont = new Font("Times New Roman",Font.PLAIN, 50); 
	
	public void switchPanels(JPanel panel) {
		MainFrame.layeredPane_1.removeAll();
		MainFrame.layeredPane_1.add(panel);
		MainFrame.layeredPane_1.repaint();
		MainFrame.layeredPane_1.revalidate();
	}
	
	public HelpPanel() {
		//Set up
		setPreferredSize(new Dimension(1600, 900));
		setBackground(Color.BLACK);
		setLayout(null);
		
		//Title
		JLabel Title = new JLabel("Help");
		Title.setForeground(Color.ORANGE);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(598, 118, 400,87);
		add(Title);
		Title.setFont(titleFont);
		
		//Help info
		JTextArea optionArea = new JTextArea("How to Play - Guess the recipe! Click on item then click on the pot. "
				+ "Once you reach the required number of ingredients you either win or lose depending on whether you selected"
				+ " the correct order");
		optionArea.setLineWrap(true);
		optionArea.setBounds(100,300,1400,380);
		optionArea.setBackground(Color.black);
		optionArea.setForeground(Color.white);
		optionArea.setFont(normalFont);
		optionArea.setWrapStyleWord(true);
		optionArea.setLineWrap(true);
		add(optionArea);
		
		//Back button
		JButton Back = new JButton("Back");
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					switchPanels(MainFrame.MainMenuPanel);
				}
			}
		});
		Back.setBackground(Color.BLACK);
		Back.setForeground(Color.white); 
		Back.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		Back.setBorder(null);
		Back.setBounds(650, 700, 271, 82);
		add(Back);
	}
}
