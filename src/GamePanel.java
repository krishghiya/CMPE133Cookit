
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
	
	String item = "None";
	Font normalFont = new Font("Times New Roman",Font.PLAIN, 40); 
	
	public void switchPanels(JPanel panel) {
		MainFrame.layeredPane_1.removeAll();
		MainFrame.layeredPane_1.add(panel);
		MainFrame.layeredPane_1.repaint();
		MainFrame.layeredPane_1.revalidate();
	}

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public GamePanel(String recipeName, String difficulty) throws IOException {
		
		//Set yp Panel
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(1600, 900));
		setLayout(null);
		
		//Display recipe name top left
		JLabel title_label = new JLabel(recipeName.split(".txt")[0]);
		title_label.setForeground(Color.WHITE);
		title_label.setFont(normalFont);
		title_label.setBounds(100, 10, 315, 50);
		add(title_label);
		
		//Display completed dish image on top left
		JLabel final_dish = new JLabel();
		BufferedImage temp = null;
		final_dish.setBounds(50, 40, 315, 225);
		try {
		    temp = ImageIO.read(new File("./src/images/final dish_full/"+recipeName.replace(".txt", ".png")));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image temp_dimg = temp.getScaledInstance(final_dish.getWidth(), final_dish.getHeight(),
		        Image.SCALE_SMOOTH);
		final_dish.setIcon(new ImageIcon(temp_dimg));
		add(final_dish);
		
		
		//Get correct recipe order from file storage
		DefaultListModel<String> actual_order = new DefaultListModel<>();
		BufferedReader br = new BufferedReader(new FileReader("./src/images/"+difficulty+"_recipes/"+recipeName));  
		String line = null;  
		while ((line = br.readLine()) != null)  
		{  
			if(line.equals("Game Order:")) {
				String tempLine = null;
				while ((tempLine = br.readLine()) != null) {
					if(tempLine.trim().length() > 0)
						actual_order.addElement((tempLine+".png"));
				}
				break;
			}
		} 
		
		DefaultListModel<String> selectedOrder = new DefaultListModel<>();
		
		//Pane for selected ingredients on left side
		JList instructions = new JList(selectedOrder);
		instructions.setLayoutOrientation(JList.VERTICAL);
		instructions.setCellRenderer(new FoodListRenderer());
		JScrollPane ins_scroll = new JScrollPane(instructions);
		ins_scroll.setBounds(50,  300, 325, 500);
		add(ins_scroll);
		
		//Pane to display various ingredients and tools at top
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(375, 35, 1100, 250);

		for (String title : new String[] { "raw", "rice_wheat", "sauce", "vegetables", "kitchen tools" }) {
			File folder = new File("./src/images/" + title);
			JList ingredients = new JList(folder.list());
			ingredients.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			ingredients.setVisibleRowCount(4);
			ingredients.setCellRenderer(new FoodListRenderer());
			ingredients.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					item = (String) ingredients.getSelectedValue();
				}
			});
			JScrollPane ing_scroll = new JScrollPane(ingredients);
			tabbedPane.add(title, ing_scroll);
		}
		
		add(tabbedPane);

		//Center placeholder image for start of game
		//Also includes game end decision popup
		JLabel Pot = new JLabel("Pot");
		Pot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!item.equals("None")) {
					selectedOrder.addElement(item);
					item = "None";
					
					boolean win = true;
					if(actual_order.size() == selectedOrder.size()) {
						
						for(int i=0;i<actual_order.size();i++) {
							if(!selectedOrder.get(i).equals(actual_order.get(i))) {
								win = false;
								break;
							}
						}
						
						if(win) {
							dialogBox("You Win!", "Game finished");
							switchPanels(MainFrame.optionPanel);
						}
						else {
							int choice = JOptionPane.showOptionDialog(MainFrame.GamePanel, "You Lose!", "Required amount of ingredients reached!", 
									JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(), new String[] {"Try Again", "Quit"}, 0);
							
							if(choice == 0) {
								try {
									MainFrame.GamePanel = new GamePanel(recipeName, difficulty);
									switchPanels(MainFrame.GamePanel);
								} catch (IOException e1) {
								}
							}
							else switchPanels(MainFrame.optionPanel);
						}
					}
				}
			}
		});
		Pot.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Pot.setForeground(Color.WHITE);
		Pot.setHorizontalAlignment(SwingConstants.CENTER);
		Pot.setBackground(Color.WHITE);
		Pot.setBounds(735, 476, 336, 259);
		BufferedImage img = ImageIO.read(new File("./src/images/kitchen tools/cooking pot.png"));
		Image dimg = img.getScaledInstance(Pot.getWidth(), Pot.getHeight(), Image.SCALE_SMOOTH);
		Pot.setIcon(new ImageIcon(dimg));
		add(Pot);
		
		//Back button
		JButton Back = new JButton("Quit");
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
		Back.setBounds(1300, 700, 271, 82);
		add(Back);
		
		//Hint button
		JButton Hint = new JButton("Hint");
		Hint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					dialogBox(actual_order.elementAt(selectedOrder.getSize()).split(".png")[0], "Here's a hint");
				}
			}
		});
		Hint.setBackground(Color.black);
		Hint.setForeground(Color.white); 
		Hint.setFont(normalFont);
		Hint.setBorder(null);
		Hint.setBounds(1100, 700, 271, 82);
		add(Hint);
	}
	
	private void dialogBox(String message, String title) {
		JOptionPane.showOptionDialog(this, 
				message, title, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(), new String[] {"Ok"}, null);
	}
}
