import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

	//Panel variables
	private JFrame frame;
	private JPanel designPanel;
	private JPanel gameModePanel;
	private JPanel newMapPanel;
	private JPanel newCreatedMapPanel;

	//Design Panel Variables
	String filesPath;
	JComboBox<String> comboBoxLoadMap;		

	//New Created Map Variables
	JButton btnUploadBackgroundImage;
	JLabel lblBackGroundLabel;	
	JButton btnDrawLines;
	JTextField textFieldFromX;
	JTextField textFieldFromY;
	JTextField textFieldToX;
	JTextField textFieldToY;
	ImageIcon[] images;
	String[] treeStrings = {"tree1icon", "tree2icon", "tree3icon"};
	JComboBox<?> treeComboBox;
	private Tree tree;
	private int selectedTree;
	private JButton btnNewBuilding;
	private JButton btnDone;
	
	//Writer/Reader variables
	private String configFile;
	private String path;
	private String buildingName;
	private Writer writer;
	private Reader reader;

	//Coordinate variables
	private JTextField inputX;
	private JTextField inputY;
	private JTextField inputZ;
	private JTextField inputW;	

	
	//John - for testing purposes
	private static ArrayList<Line> linesList = new ArrayList<Line>();
	
	private Line line;
	private Line finishedLine;
	
	private int xbegin = 0; 
	private int ybegin = 0; 
	private int xend = 0; 
	private int yend = 0; 
	private int walls = 0;
	private int height = 0;
	private int wight = 10;
	private int wallsNeeded = 4;
	//John - for testing purposes

	
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
		frame.setBounds(10, 10, 1024, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		GameModePanel();
	}
	
	/* Author: Valerie Otero | Date: March 8 2020	
	 * Method initializes the main panel where the user can choose if it wants
	 * to Design or Play. If the user clicks Design button, this methods calls another method
	 * that initializes the Design Panel. (Play button is not enabled for now)
	 */
	public void GameModePanel() {

		//NEW GAME MODE PANEL
		gameModePanel = new JPanel();
		gameModePanel.setBounds(0, 0, 1008, 681);
		frame.getContentPane().add(gameModePanel);
		gameModePanel.setLayout(null);

		//LABEL-CHOOSE GAME MODE
		JLabel lblChooseGameMode = new JLabel("Choose game mode:");
		lblChooseGameMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseGameMode.setBounds(447, 127, 139, 23);
		gameModePanel.add(lblChooseGameMode);

		//DESIGN BUTTON
		JButton btnDesign = new JButton("Design");
		btnDesign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				initializeDesignPanel();

			}
		});
		btnDesign.setBounds(457, 161, 89, 23);
		gameModePanel.add(btnDesign);

		//PLAY BUTTON
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPlay.setBounds(457, 195, 89, 23);
		gameModePanel.add(btnPlay);	
		
	}


	/*Author: Valerie Otero | Date: March 8 2020
	 * Method initializes the design panel when the user clicks such option.
	 * If the user clicks on the Create New Map option, this method calls another 
	 * method that initializes that panel.
	*/
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
		lblChooseMap.setBounds(447, 127, 139, 23);
		designPanel.add(lblChooseMap);

		//NEW MAP BUTTON
		JButton btnNewMap = new JButton("New Map");
		btnNewMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	

				initializeNewMapPanel();

			}
		});
		btnNewMap.setBounds(447, 152, 89, 23);
		designPanel.add(btnNewMap);

		//LABEL - LOAD MAP
		JLabel lblLoadMap = new JLabel("Load Map:");
		lblLoadMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoadMap.setBounds(447, 210, 89, 23);
		designPanel.add(lblLoadMap);

		//LOAD MAP DROPDOWN
		comboBoxLoadMap = new JComboBox<String>();	

		filesPath = System.getProperty("user.dir");		

		File directory = new File(filesPath+"\\Maps");

		String[] fileList = directory.list();

		for(String name:fileList){
			comboBoxLoadMap.addItem(name);		
		} 		
		comboBoxLoadMap.setBounds(447, 235, 107, 20);
		designPanel.add(comboBoxLoadMap);
	}
	
	/*Author: Juan Davila | Date: March 21 2020
	 * Method creates an array of questions.
	*/
	public Question[] createQuestions() {
		
		String q1 = "Que clases se dan en este edificio?\n" +
					"(a) INGE/INEL/ICOM\n(b) INQU\n(c) ADMI\n";
		String q2 = "Este edificio conecta a que otro edificio?" +
					"(a) Chardon\n(b) Fisica\n(c) ININ\n";
		Question[] questions = {
				new Question(q1, "a"),
				new Question(q2, "c")
		};
		
		return questions;
	}
	
	public static void takeTest(Question[] questions) {
		int score = 0;
		//SCANNER EN NUESTRO CASO ENTRADA DE UN TEXTBOX
		Scanner keyboardInput = new Scanner(System.in);
		for(int i = 0; i < questions.length; i++) {
			System.out.println(questions[i].prompt);
			String answer = keyboardInput.nextLine();
			if(answer.equals(questions[i].answer)) {
				score++;
			}
		}
		System.out.println("You have unlocked the building, with a score of " + score + "!");
	}
	
	
	public void initializeTakeTest() {

		Font obj = new Font("Arial", Font.BOLD, 18);
		JLabel question1= new JLabel("How many seats in this building?");
		question1.setFont(obj);
		question1.setBounds(10, 525, 300, 20);
		newCreatedMapPanel.add(question1);
		
		
		JLabel answer1= new JLabel("24 no more no less");
		answer1.setFont(obj);
		answer1.setBounds(10, 558, 300, 20);
		newCreatedMapPanel.add(answer1);
		
		JLabel answer2= new JLabel("About 9");
		answer2.setFont(obj);
		answer2.setBounds(10, 600, 300, 20);
		newCreatedMapPanel.add(answer2);
		
		JLabel answer3= new JLabel("A lot");
		answer3.setFont(obj);
		answer3.setBounds(10, 644, 300, 20);
		newCreatedMapPanel.add(answer3);
		
		
		JButton option1 = new JButton("Select");
		option1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		option1.setBounds(908, 550, 100, 44);
		newCreatedMapPanel.add(option1);	
		
		
		JButton option2 = new JButton("Select");
		option2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		option2.setBounds(908, 594, 100, 44);
		newCreatedMapPanel.add(option2);	
		
		
		JButton option3 = new JButton("Select");
		option3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		option3.setBounds(908, 638, 100, 44);
		newCreatedMapPanel.add(option3);	
		
		JPanel questionPanel = new JPanel();
		questionPanel.setBorder(new LineBorder(Color.BLACK));
		questionPanel.setBackground(Color.WHITE);
		questionPanel.setBounds(0, 638, 1024, 44); //1024 x 720
		newCreatedMapPanel.add(questionPanel);
		
		JPanel questionPanel2 = new JPanel();
		questionPanel2.setBorder(new LineBorder(Color.BLACK));
		questionPanel2.setBackground(Color.WHITE);
		questionPanel2.setBounds(0, 594, 1024, 44); //1024 x 720
		newCreatedMapPanel.add(questionPanel2);
		
		JPanel questionPanel3 = new JPanel();
		questionPanel3.setBorder(new LineBorder(Color.BLACK));
		questionPanel3.setBackground(Color.WHITE);
		questionPanel3.setBounds(0, 550, 1024, 44); //1024 x 720
		newCreatedMapPanel.add(questionPanel3);
		
		JPanel answerPanel = new JPanel();
		answerPanel.setBorder(new LineBorder(Color.BLACK));
		answerPanel.setBackground(Color.GRAY);
		answerPanel.setBounds(0, 520, 1024, 200); //1024 x 720
		newCreatedMapPanel.add(answerPanel);
	}

	
	/*Author: Valerie Otero | Date: March 8 2020
	  Method initializes the Create New Map panel when the user clicks such option.
	 * If the user clicks on the Save option, this method calls another 
	 * method that initializes the panel where the map designer will begin creatin the map.
	 */	
	public void initializeNewMapPanel() {

		designPanel.setVisible(false);

		//NEW MAP PANEL
		newMapPanel = new JPanel();
		newMapPanel.setBounds(0, 0, 1008, 681);
		frame.getContentPane().add(newMapPanel);
		newMapPanel.setLayout(null);	

		//LABEL ENTER MAP NAME
		JLabel lblEnterMapName = new JLabel("Enter new map name:");
		lblEnterMapName.setBounds(408, 127, 139, 23);
		newMapPanel.add(lblEnterMapName);

		//INPUT - NEW MAP NAME
		JTextField textFieldNewMapName;textFieldNewMapName = new JTextField();
		textFieldNewMapName.setBounds(426, 161, 89, 23);
		newMapPanel.add(textFieldNewMapName);
		textFieldNewMapName.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				//Gets the name entered by the user
				//Gets the name entered by the user
				configFile = textFieldNewMapName.getText();
				path = System.getProperty("user.dir")+File.separator+"maps"+File.separator+configFile;

				//Validates that the name is not an empty string
				if(!(configFile.isEmpty())) {

					writingHeader(path);

					initializeCreateMapPanel();					
				}
				else {
					JOptionPane.showMessageDialog(null, "Must enter a name to save", "Warning", JOptionPane.INFORMATION_MESSAGE);	
				}
			}});
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
		writer.newLine();
		writer.close();
	}

	/*Author: Valerie Otero | Date: March 9 2020
	 * Method initializes the New Created Map panel when the user clicks the Save button.
	 * Here the map designer can begin its design by uploading and image from computer,
	 * as a background and/or draw lines for the building walls.
	*/
	public void initializeCreateMapPanel(){
		newMapPanel.setVisible(false);

		//NEW CREATED MAP PANEL
		newCreatedMapPanel = new JPanel();
		newCreatedMapPanel.setBounds(0, 0, 1008, 681);
		frame.getContentPane().add(newCreatedMapPanel);
		newCreatedMapPanel.setLayout(null);	

		//LABEL-WHERE BACKGROUND IMAGE IS SET
		lblBackGroundLabel= new JLabel("");	
		lblBackGroundLabel.setBounds(0, 43, 1008, 681);
		newCreatedMapPanel.add(lblBackGroundLabel);

		
		//NEW BUILDING BUTTON
		btnNewBuilding = new JButton("New Building");	
		btnNewBuilding.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				

				btnNewBuilding.setVisible(false);
				btnDrawLines.setVisible(true);
				btnDone.setVisible(true);

				NewBuildingFrame newBuildingFrame = new NewBuildingFrame();				

			}
		});
		btnNewBuilding.setBounds(386, 15, 145, 20);
		newCreatedMapPanel.add(btnNewBuilding);

		initializeManualCoordinatesLabelsAndTextFields();
		initializeTakeTest();
		initializeDrawLines();
		initializeSaveBuilding();

		btnUploadBackgroundImage = new JButton("Upload background image");
		btnUploadBackgroundImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				btnUploadBackgroundImageMouseEvent(e);	
				
			}
		});
		btnUploadBackgroundImage.setHorizontalAlignment(SwingConstants.LEFT);		
		btnUploadBackgroundImage.setBounds(10, 11, 187, 23);			
		newCreatedMapPanel.add(btnUploadBackgroundImage);
		
		initializeTreeDropDown();
		
		
		//DONE BUTTON
		btnDone = new JButton("Done");
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnDrawLines.setVisible(false);	
				btnDone.setVisible(false);
				btnNewBuilding.setVisible(true);					
			}
		});
		btnDone.setBounds(909, 50, 89, 23);
		btnDone.setVisible(false);
		newCreatedMapPanel.add(btnDone);
		newCreatedMapPanel.add(lblBackGroundLabel);	
	}

	/*Author: Valerie Otero | Date: March 9 2020
	 * Method initializes a chooser box when the map designer selects to "Upload background image" 
	 * that lets the map designer select an image from their computer
	 */	
	public void btnUploadBackgroundImageMouseEvent(MouseEvent e) {

		JFileChooser chooser = new JFileChooser();
		BufferedImage img;	   				   
		File file ; 				  

		if (e.getSource()==btnUploadBackgroundImage) {
			chooser.showOpenDialog(null);
			file = chooser.getSelectedFile();

			try {
				img=ImageIO.read(file);
				ImageIcon icon=new ImageIcon(img);
			    lblBackGroundLabel.setIcon(icon); 			

				lblBackGroundLabel.revalidate(); 
				lblBackGroundLabel.repaint();			
			}
			catch(IOException e1) {
				System.out.println("Must select an image");
			}
		}
	}

	/* Author: Valerie Otero | Date: March 9 2020
	 * Method initializes several labels and text fields associated with the panel
	 * where the map designer starts designing */	 
	public void initializeManualCoordinatesLabelsAndTextFields() {
		
		//LABEL - FROM:
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(800, 1, 37, 14);
		newCreatedMapPanel.add(lblFrom);

		//LABEL (FROM) - x=
		JLabel lblFromX = new JLabel("x=");
		lblFromX.setBounds(751, 18, 14, 14);
		newCreatedMapPanel.add(lblFromX);

		//(FROM) X INPUT
		textFieldFromX = new JTextField();
		textFieldFromX.setBounds(768, 17, 37, 20);
		newCreatedMapPanel.add(textFieldFromX);
		textFieldFromX.setColumns(10);

		//LABEL (FROM) - y=
		JLabel lblFromY = new JLabel("y=");
		lblFromY.setBounds(818, 18, 14, 14);
		newCreatedMapPanel.add(lblFromY);

		//(FROM) Y INPUT
		textFieldFromY = new JTextField();
		textFieldFromY.setBounds(834, 16, 37, 20);
		newCreatedMapPanel.add(textFieldFromY);
		textFieldFromY.setColumns(10);

		//LABEL - TO:
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(935, 1, 22, 14);
		newCreatedMapPanel.add(lblTo);	

		//LABEL (TO) - x=
		JLabel labelToX = new JLabel("x=");
		labelToX.setBounds(879, 18, 14, 14);
		newCreatedMapPanel.add(labelToX);

		//(TO) X INPUT
		textFieldToX = new JTextField();
		textFieldToX.setColumns(10);
		textFieldToX.setBounds(897, 17, 37, 20);
		newCreatedMapPanel.add(textFieldToX);

		//LABEL (TO) - y=
		JLabel labelToY = new JLabel("y=");
		labelToY.setBounds(944, 18, 14, 14);
		newCreatedMapPanel.add(labelToY);

		//(TO) Y INPUT
		textFieldToY = new JTextField();
		textFieldToY.setColumns(10);
		textFieldToY.setBounds(962, 17, 37, 20);
		newCreatedMapPanel.add(textFieldToY);
	}

	/*
	 * 
	 * Method initializeDrawLines Created by: John A. Parks
	 * Still going
	 * End: March 7/2020
	 * 
	 * Implement button to start drawing lines on background.
	 *
	 */
	public void initializeDrawLines() {
		
		//DRAW LINES BUTTON
		btnDrawLines = new JButton("Draw Building Wall");
		btnDrawLines.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				drawLines();
			}
		});		
		btnDrawLines.setBounds(386, 15, 145, 20);
		newCreatedMapPanel.add(btnDrawLines);
	}
	
	/*
	 * 
	 * Method initializeSaveBuilding Created by: John A. Parks
	 * Still going
	 * TO-DO
	 * 
	 * Implement button to save the lines drawn as the building
	 *
	 */
	public void initializeSaveBuilding() {
		
		//DRAW LINES BUTTON
		JButton btnDrawLinesManually = new JButton("Draw Building Wall Manually");
		btnDrawLinesManually.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				drawLines();
			}
		});		
		btnDrawLinesManually.setBounds(541, 15, 200, 20);
		newCreatedMapPanel.add(btnDrawLinesManually);
	}

	/*
	 * 
	 * Method drawLines Created by: John A. Parks
	 * End:March 8/2020
	 * 
	 * Uses mouse listeners to get coordinates and draw a line.
	 *
	 */
	public void drawLines() {		
		newCreatedMapPanel.addMouseListener(mouseHandler);
		newCreatedMapPanel.addMouseMotionListener(mouseMotionHandler);
		line = new Line();
		line.setForeground(Color.BLACK);
		line.setBounds(0, 0, 1008, 681);
		line.setOpaque(false);
		newCreatedMapPanel.add(line);	
		newCreatedMapPanel.add(lblBackGroundLabel);
	}
	
	/*
	 * 
	 * mouseHandler Created by: John A. Parks
	 * End:March 8/2020
	 * 
	 * Obtains coordinates with respect to mouse movements to draw the line.
	 *
	 */
	public MouseListener mouseHandler = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			xbegin = xend = e.getX();
			ybegin = yend = e.getY();
			line.coordinateList.set(0, xbegin);
			line.coordinateList.set(1, ybegin);
			line.coordinateList.set(2, xend);
			line.coordinateList.set(3, yend);
			line.repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			xend = e.getX();
			yend = e.getY();
			finishedLine = new Line();
			line.coordinateList.set(0, xbegin);
			line.coordinateList.set(1, ybegin);
			line.coordinateList.set(2, xend);
			line.coordinateList.set(3, yend);
			//calls writing functions
			writer.writingCoordinates(walls, xbegin, ybegin, xend, yend, height, wight, "wall.png");
			if(walls == wallsNeeded)writer.close();
			walls++;
			//ends
			linesList.add(finishedLine);
			line.repaint();

		}
	};
	
	public MouseMotionListener mouseMotionHandler = new MouseMotionAdapter() {
		@Override
		public void mouseDragged(MouseEvent e) {
			xend = e.getX();
			yend = e.getY();
			line.coordinateList.set(2, xend);
			line.coordinateList.set(3, yend);
			line.repaint();
		}
	};
	
	public void initializeTreeDropDown() {
		
		TreeDropDownRenderer renderer= new TreeDropDownRenderer();

		images = new ImageIcon[treeStrings.length];

		Integer[] intArray = new Integer[treeStrings.length];

		for (int i = 0; i < treeStrings.length; i++) {
			intArray[i] = new Integer(i);
			images[i] = createImageIcon("Resources/" + treeStrings[i] + ".png");			
		}                    

		//TREE DROPDOWN
		treeComboBox = new JComboBox(intArray);
		treeComboBox.setBounds(207, 11, 163, 23);
		newCreatedMapPanel.add(treeComboBox);
		treeComboBox.setRenderer(renderer);
		
		treeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				
				insertTrees();

			}
		});
		
	}
	
	protected static ImageIcon createImageIcon(String path) {

		URL imgURL = Menu.class.getResource(path);

		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	
	public void insertTrees() {		

		treeComboBox.addActionListener(new ActionListener() {			

			public void actionPerformed(ActionEvent arg0) {	
				selectedTree = (int)treeComboBox.getSelectedItem();				

			}
		});

		newCreatedMapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {					

				if (e.isMetaDown()) { //So trees are only added with right click
					return;

				}
				else {
					tree = new Tree((selectedTree+1), e.getX(), e.getY() ,60, 87);

					newCreatedMapPanel.add(tree);
					tree.revalidate();
					tree.repaint();
					newCreatedMapPanel.add(lblBackGroundLabel);	
				}
			}
		});
	}
	
	//Standard class for rendering a JComboBox 
	class TreeDropDownRenderer extends JLabel implements ListCellRenderer {
		
		public TreeDropDownRenderer() {
			setOpaque(true);        
		}

		/* This method finds the image and text corresponding to the selected value 
		 * and returns the label, set up to display the text and image. */		 
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			
			//Get the selected index. 
			int selectedIndex = ((Integer)value).intValue();

			if (isSelected) {
				setBackground(list.getSelectionBackground());				
			} else {
				setBackground(list.getBackground());				
			}

			//Set the icon and text.  
			ImageIcon icon = images[selectedIndex];		
			setIcon(icon);
			if (icon != null) {
				setText("Tree " + (selectedIndex+1));				
			} 
			return this;
		}
	}
}