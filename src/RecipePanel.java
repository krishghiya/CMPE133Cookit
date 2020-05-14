
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;

public class RecipePanel extends JPanel {

	Font titleFont = new Font("Castellar", Font.PLAIN, 50);
	Font normalFont = new Font("Times New Roman",Font.PLAIN, 50); 
	
	public void switchPanels(JPanel panel) {
		MainFrame.layeredPane_1.removeAll();
		MainFrame.layeredPane_1.add(panel);
		MainFrame.layeredPane_1.repaint();
		MainFrame.layeredPane_1.revalidate();
	}
	
	/**
	 * Create the panel.
	 */
	public RecipePanel() {
		
		//Set up
		setPreferredSize(new Dimension(1600, 900));
		setBackground(Color.BLACK);
		setLayout(null);
		String[] recipe_types = new String[] {"easy_recipes", "medium_recipes", "hard_recipes"};
		
		//Display steps
		JTextArea info = new JTextArea();
		info.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		info.setWrapStyleWord(true);
		info.setLineWrap(true);
		JScrollPane info_scroll = new JScrollPane (info);
		info_scroll.setBounds(846, 246, 500, 341);
		add(info_scroll);
		
		//Get list of recipes
		ArrayList<String> recipes = new ArrayList<String>();
		for(String r: recipe_types) {
			File f = new File("./src/images/"+r);
			for(String s: f.list()) recipes.add(s.split(".txt")[0]);
		}
		
		//Display recipe data
		JList recipeList = new JList(recipes.toArray());
		recipeList.setLayoutOrientation(JList.VERTICAL);
		recipeList.setCellRenderer(new FoodListRenderer(new String[] {"final dish"}));
		recipeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					String recipe = (String) recipeList.getSelectedValue();
					for(String r: recipe_types) {
						try {
							BufferedReader br = new BufferedReader(new FileReader("./src/images/"+r+"/"+recipe+".txt"));
							String line = null;  
							while ((line = br.readLine()) != null)  
							{  
								if(line.equals("Procedure:")) {
									String tempLine = null;
									while ((tempLine = br.readLine()).trim().length() > 0)
											info.append(tempLine+"\n");
									break;
								}
							} 
						}
						catch(Exception exc) {
							continue;
						}
					}
				}
			}
		});
		JScrollPane recipeList_scroll = new JScrollPane(recipeList);
		recipeList_scroll.setBounds(432, 246, 291, 341);
		add(recipeList_scroll);
		
		//Title
		JLabel Title = new JLabel("Recipe List");
		Title.setForeground(Color.ORANGE);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(598, 118, 400,87);
		add(Title);
		Title.setFont(titleFont);
		
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
		Back.setBackground(Color.black);
		Back.setForeground(Color.white); 
		Back.setFont(normalFont);
		Back.setBorder(null);
		Back.setBounds(650, 700, 271, 82);
		add(Back);
	}
}

