import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;

public class MainFrame extends JFrame {

	public static JPanel contentPane, MainMenuPanel, gameMenuPan, RecipePanel, GamePanel,HelpPanel, optionPanel;
	private JPanel titleNamePanel, pot, plate, grill, namePanel;
	private JLabel titleNameLabel, nameLabel, potLabel, plateLabel, grillLabel;

	private JPanel buttonMenuPanel;
	// private JTextArea optionArea;

	Font titleFont = new Font("Castellar", Font.PLAIN, 120);
	Font titleFont2 = new Font("Castellar", Font.PLAIN, 80);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 50);
	Font nameFont = new Font("Times New Roman", Font.PLAIN, 20);

	TitleScreenHandler tsHandler = new TitleScreenHandler();
	BackHandler backHandler = new BackHandler();
	public static JLayeredPane layeredPane_1;
	private JButton btnRecipeInformation, btnHelp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setTitle("Cook It!");
					frame.setPreferredSize(new Dimension(1600, 900));
					frame.setLocationRelativeTo(null);
					frame.pack();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void switchPanels(JPanel panel) {
		layeredPane_1.removeAll();
		layeredPane_1.add(panel);
		layeredPane_1.repaint();
		layeredPane_1.revalidate();
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainFrame() throws IOException {

		MainMenu();
		optionGameScreen();
		switchPanels(MainMenuPanel);
	}

	private void MainMenu() {
		// Sets the Icon at the top left
		setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/images/resources/cook.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		// CONTENTPANE
		contentPane = new JPanel();
		contentPane.setBackground(Color.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// MAINMENUPAN
		MainMenuPanel = new JPanel();
		//layeredPane.add(mainMenuPan);
		MainMenuPanel.setLayout(null);
		MainMenuPanel.setBackground(Color.black);

		// GamePan
		gameMenuPan = new JPanel();
		contentPane.add(gameMenuPan);
		gameMenuPan.setLayout(null);
		gameMenuPan.setBackground(Color.black);

		// TITLE CREATED HERE "COOK IT"
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(499, 122, 600, 150);
		titleNamePanel.setBackground(Color.black);

		titleNameLabel = new JLabel("COOK IT!");
		titleNameLabel.setForeground(Color.white);
		titleNamePanel.add(titleNameLabel);
		MainMenuPanel.add(titleNamePanel);
		titleNameLabel.setFont(titleFont);

		// POT IMAGE
		pot = new JPanel();
		pot.setBounds(490, 480, 100, 150);
		pot.setBackground(Color.orange);
		MainMenuPanel.add(pot);

		potLabel = new JLabel("");
		potLabel.setIcon(new ImageIcon("./src/images/resources/pot.png"));
		potLabel.setBounds(200, 500, 600, 150);
		pot.add(potLabel);

		// PLATE IMAGE
		plate = new JPanel();
		plate.setBounds(1015, 480, 100, 150);
		plate.setBackground(Color.orange);
		MainMenuPanel.add(plate);

		plateLabel = new JLabel("");
		plateLabel.setIcon(new ImageIcon("./src/images/resources/plate.png"));
		plateLabel.setBounds(200, 500, 600, 150);
		plate.add(plateLabel);


		// CREATORS
		namePanel = new JPanel();
		namePanel.setBounds(499, 298, 600, 103);
		namePanel.setBackground(Color.black);
		namePanel.setLayout(null);

		nameLabel = new JLabel("By Richard Arcangel, Krish Ghiya, Veida Hernandez, Huan Tran");
		nameLabel.setBounds(37, 5, 526, 24);
		nameLabel.setForeground(Color.orange);
		namePanel.add(nameLabel);
		MainMenuPanel.add(namePanel);
		nameLabel.setFont(nameFont);

		// PLAY BUTTON

		JButton btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.black);
		btnPlay.setForeground(Color.white);
		btnPlay.setFont(normalFont);

		MainMenuPanel.add(btnPlay);
		btnPlay.setBorder(null);
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlay.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPlay.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnPlay.addActionListener(tsHandler);
			}
		});
		btnPlay.setIcon(null);
		btnPlay.setBounds(667, 411, 271, 82);
		
		RecipePanel = new RecipePanel();
		btnRecipeInformation = new JButton("Recipes");
		btnRecipeInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getClickCount() == 1) {
					switchPanels(RecipePanel);
				}
			}
		});
		btnRecipeInformation.setForeground(Color.WHITE);
		btnRecipeInformation.setFont(normalFont);
		btnRecipeInformation.setBorder(null);
		btnRecipeInformation.setBackground(Color.BLACK);
		btnRecipeInformation.setBounds(677, 595, 271, 82);
		MainMenuPanel.add(btnRecipeInformation);

		// HELP BUTTON
		HelpPanel = new HelpPanel();
		btnHelp = new JButton("Help");
		btnHelp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if(e.getClickCount() == 1) {
						switchPanels(HelpPanel);
					}
				}
			});
		btnHelp.setBackground(Color.black);
		btnHelp.setForeground(Color.white);
		btnHelp.setFont(normalFont);

		btnHelp.setBorder(null);
		btnHelp.setBounds(667, 503, 271, 82);
		MainMenuPanel.add(btnHelp);

		// EXIT BUTTON
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.black);
		btnExit.setForeground(Color.white);
		btnExit.setFont(normalFont);

		btnExit.setBorder(null);
		btnExit.setBounds(667, 687, 271, 82);
		MainMenuPanel.add(btnExit);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
	}

	public void optionGameScreen() throws IOException {

		//Set up
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(0, 0, 1582, 853);
		contentPane.add(layeredPane_1);
		layeredPane_1.setLayout(new CardLayout(0, 0));

		optionPanel = new JPanel();
		// optionPanel.setBounds(600,200,300,500);
		optionPanel.setLayout(null);
		optionPanel.setBackground(Color.black);
		layeredPane_1.add(optionPanel);

		buttonMenuPanel = new JPanel();
		buttonMenuPanel.setBounds(261, 98, 900, 100);
		buttonMenuPanel.setBackground(Color.black);

		titleNameLabel = new JLabel("Pick a level");
		titleNameLabel.setForeground(Color.orange);
		buttonMenuPanel.add(titleNameLabel);
		optionPanel.add(buttonMenuPanel);
		titleNameLabel.setFont(titleFont2);
		
		//Easy difficulty button
		JButton easy = new JButton("Easy");
		easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					File f = new File("./src/images/easy_recipes");
					Random r = new Random();
					String[] fileList = f.list();
					int index = r.nextInt(fileList.length);
					try {
						switchPanels(new GamePanel(fileList[index], "easy"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		easy.setBackground(Color.black);
		easy.setForeground(new Color(153, 255, 153));
		easy.setFont(normalFont);
		
		easy.setBorder(null);
		easy.setBounds(578, 220, 271, 82);
		optionPanel.add(easy);

		//Medium difficulty button
		JButton medium = new JButton("Medium");
		medium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					File f = new File("./src/images/medium_recipes");
					Random r = new Random();
					String[] fileList = f.list();
					int index = r.nextInt(fileList.length);
					try {
						switchPanels(new GamePanel(fileList[index], "medium"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		medium.setBackground(Color.black);
		medium.setForeground(new Color(255, 255, 153));
		medium.setFont(normalFont);

		medium.setBorder(null);
		medium.setBounds(588, 308, 271, 82);
		optionPanel.add(medium);

		//Hard difficulty button
		JButton hard = new JButton("Hard");
		hard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					File f = new File("./src/images/hard_recipes");
					Random r = new Random();
					String[] fileList = f.list();
					int index = r.nextInt(fileList.length);
					try {
						switchPanels(new GamePanel(fileList[index], "hard"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		hard.setBackground(Color.black);
		hard.setForeground(new Color(255, 153, 153));
		hard.setFont(normalFont);

		hard.setBorder(null);
		hard.setBounds(578, 400, 271, 82);
		optionPanel.add(hard);

		//Back button
		JButton back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setFont(normalFont);
		back.addActionListener(backHandler);
		back.setBorder(null);
		back.setBounds(578, 694, 271, 82);
		optionPanel.add(back);

	}

	public class TitleScreenHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			switchPanels(optionPanel);
		}

	}

	public class BackHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			switchPanels(MainMenuPanel);
		}
	}
}
