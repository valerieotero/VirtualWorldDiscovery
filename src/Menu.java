import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Menu {

	private JFrame frame;
	private JPanel designPanel;
	private JPanel gameModePanel;
	private JPanel newMapPanel;
	private JTextField textFieldNewMapName;
	
	//Writer/Reader variables
	private String configFile;
	private String buildingName;
	private Writer writer;
	private Reader reader;
	
	//Coordinate variables
	private JTextField inputX;
	private JTextField inputY;
	private JTextField inputZ;
	private JTextField inputW;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initializeFrame();
	}

	public void initializeFrame() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		GameModePanel();


	}
	public void GameModePanel() {

		//NEW GAME MODE PANEL
		gameModePanel = new JPanel();
		gameModePanel.setBounds(0, 0, 1008, 681);
		frame.getContentPane().add(gameModePanel);
		gameModePanel.setLayout(null);

		//LABEL-CHOOSE GAME MODE
		JLabel lblChooseGameMode = new JLabel("Choose game mode:");
		lblChooseGameMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseGameMode.setBounds(408, 127, 139, 23);
		gameModePanel.add(lblChooseGameMode);

		//DESIGN BUTTON
		JButton btnDesign = new JButton("Design");
		btnDesign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				initializeDesignPanel();

			}
		});
		btnDesign.setBounds(426, 161, 89, 23);
		gameModePanel.add(btnDesign);

		//PLAY BUTTON
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPlay.setBounds(426, 195, 89, 23);
		gameModePanel.add(btnPlay);	

	}

	public void initializeDesignPanel() {
		gameModePanel.setVisible(false);

		//NEW DESIGN PANEL
		designPanel = new JPanel();
		designPanel.setBounds(0, 0, 1008, 681);
		frame.getContentPane().add(designPanel);
		designPanel.setLayout(null);				

		//LABEL CHOOSE MAP
		JLabel lblChooseMap = new JLabel("Choose Map:");
		lblChooseMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseMap.setBounds(426, 127, 139, 23);
		designPanel.add(lblChooseMap);

		//NEW MAP BUTTON
		JButton btnNewMap = new JButton("New Map");
		btnNewMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	

				initializeNewMapPanel();

			}
		});
		btnNewMap.setBounds(426, 161, 89, 23);
		designPanel.add(btnNewMap);

		//LOAD MAP BUTTON
		JButton btnLoadMap = new JButton("Load Map");
		btnLoadMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {							

			}
		});
		btnLoadMap.setBounds(426, 195, 89, 23);
		designPanel.add(btnLoadMap);


	}
	
	
	public void initializeNewMapPanel() {

		designPanel.setVisible(false);
				
		//NEW MAP PANEL
		newMapPanel = new JPanel();
		newMapPanel.setBounds(0, 0, 1008, 681);
		frame.getContentPane().add(newMapPanel);
		newMapPanel.setLayout(null);	

		//GO BACK BUTTON
		JButton btngoBack = new JButton("Back");			
		btngoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {							
				
				designPanel.setVisible(true);
				btngoBack.setVisible(false);
			}
		});				
		btngoBack.setBounds(10, 11, 65, 23);
		btngoBack.setVisible(true);
		newMapPanel.add(btngoBack);
		
		
		//LABEL ENTER MAP NAME
		JLabel lblEnterMapName = new JLabel("Enter new map name:");
		lblEnterMapName.setBounds(408, 127, 139, 23);
		newMapPanel.add(lblEnterMapName);
		
		//INPUT - NEW MAP NAME
		textFieldNewMapName = new JTextField();
		textFieldNewMapName.setBounds(426, 161, 89, 23);
		newMapPanel.add(textFieldNewMapName);
		textFieldNewMapName.setColumns(10);

		//SAVE BUTTON
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Gets the name entered by the user
				configFile = textFieldNewMapName.getText();
				
				//Validates that the name is not an empty string
				if(!(configFile.isEmpty())) {
					writingHeader(configFile);
				}							
				JOptionPane.showMessageDialog(null, "Must enter a name to save", "Warning", JOptionPane.INFORMATION_MESSAGE);				}
		});
		btnSave.setBounds(550, 161, 89, 23);
		newMapPanel.add(btnSave);
	}
	
	//Helper method to write the name of the map and header of the file
	//header -> Map name
	private void writingHeader(String s) {
		writer.open(s);
		writer.write("Map name = ");
		writer.write(s);
		writer.newLine();
		writer.close();
	}
	
	private void writingCoordinates() {
		
	}
	
}