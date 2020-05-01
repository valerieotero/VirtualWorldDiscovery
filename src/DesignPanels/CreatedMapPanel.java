package DesignPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Classes.Line;
import Classes.Tree;
import Classes.Writer;
import Classes.fileNames;


public class CreatedMapPanel extends JPanel {

	private JPanel newCreatedMapPanel;

	//New Created Map Variables
	JButton btnUploadBackgroundImage;
	JLabel lblBackGroundLabel;	
	JButton btnDrawLines;
	JTextField textFieldFromX;
	JTextField textFieldFromY;
	JTextField textFieldToX;
	JTextField textFieldToY;
	JTextField textFieldMousePosX;
	JTextField textFieldMousePosY;
	private JButton btnNewBuilding;
	private JButton btnDone;
	private boolean entered = false;

	//Coordinate variables
	private JTextField inputX;
	private JTextField inputY;
	private JTextField inputZ;
	private JTextField inputW;	

	//Class-Objects needed.
	Writer writer;
	fileNames locations = new fileNames();
	NewBuildingFrame newBuildingFrame;

	//John - for testing purposes
	private static ArrayList<Line> linesList = new ArrayList<Line>();
	private static ArrayList<Line> linesListFinal = new ArrayList<Line>();
	private JComboBox<String> comboBoxCoordinates = new JComboBox<String>();
	private String Coordinates = "Cartesian";

	//Line variables 
	private Line line;
	private Line finishedLine;
	private int mousePosX;
	private int mousePosY;

	private int xbegin = 0; 
	private int ybegin = 0; 
	private int xend = 0; 
	private int yend = 0; 
	private int walls = 0;
	private int height = 0;
	private int wight = 10;
	private int wallsNeeded = 4;
	//John - for testing purposes
	private int count = 0;


	//Tree variables
	ImageIcon[] images;
	private int selectedTree;
	String[] treeStrings = {"tree1icon", "tree2icon", "tree3icon"};
	JComboBox<?> treeComboBox;
	private Tree tree;
	

	//CONSTRUCTOR
	public CreatedMapPanel(JFrame frame, Writer writer){

		this.writer = writer;	

		//NEW CREATED MAP PANEL
		newCreatedMapPanel = new JPanel();
		newCreatedMapPanel.setBounds(0, 0, 1220, 681);
		frame.getContentPane().add(newCreatedMapPanel);
		newCreatedMapPanel.setLayout(null);	

		//LABEL-WHERE BACKGROUND IMAGE IS SET
		lblBackGroundLabel= new JLabel("");	
		lblBackGroundLabel.setBounds(0, 46, 1220, 678);
		newCreatedMapPanel.add(lblBackGroundLabel);


		//NEW BUILDING BUTTON
		btnNewBuilding = new JButton("New Building");	
		btnNewBuilding.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				

				btnNewBuilding.setVisible(false);
				btnDrawLines.setVisible(true);
				btnDone.setVisible(true);

				newBuildingFrame = new NewBuildingFrame();				

			}
		});
		btnNewBuilding.setBounds(207, 11, 145, 24);
		newCreatedMapPanel.add(btnNewBuilding);

		initializeManualCoordinatesLabelsAndTextFields();
		//initializeTakeTest();
		initializeDrawLines();
		initializeSaveBuilding();

		btnUploadBackgroundImage = new JButton("Upload background image");
		btnUploadBackgroundImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				UploadBackgroundImageMouseEvent(e);	

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
		btnDone.setBounds(1099, 64, 89, 23);
		btnDone.setVisible(false);
		newCreatedMapPanel.add(btnDone);
		newCreatedMapPanel.add(lblBackGroundLabel);			
	}


	/*Author: Valerie Otero | Date: March 9 2020
	 * Method initializes a chooser box when the map designer selects to "Upload background image" 
	 * that lets the map designer select an image from their computer
	 */	
	public void UploadBackgroundImageMouseEvent(MouseEvent e) {

		JFileChooser chooser = new JFileChooser();
		BufferedImage img;	   				   
		File file ; 				  

		if (e.getSource()==btnUploadBackgroundImage) {
			chooser.showOpenDialog(null);
			file = chooser.getSelectedFile();

			try {
				backgorundWriter(file);
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

	public void backgorundWriter(File file) {
		Writer.open(locations.load(0));
		Writer.writeSpace(file.getPath());
		Writer.close();
	}
	

	/* Author: Valerie Otero | Date: March 9 2020
	 * Method initializes several labels and text fields associated with the panel
	 * where the map designer starts designing */	 
	public void initializeManualCoordinatesLabelsAndTextFields() {

		//LABEL - FROM:
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(618, 1, 37, 14);
		newCreatedMapPanel.add(lblFrom);

		//LABEL (FROM) - x=
		JLabel lblFromX = new JLabel("x=");
		lblFromX.setBounds(580, 18, 14, 14);
		newCreatedMapPanel.add(lblFromX);

		//(FROM) X INPUT
		textFieldFromX = new JTextField();
		textFieldFromX.setBounds(596, 15, 30, 20);
		newCreatedMapPanel.add(textFieldFromX);
		textFieldFromX.setColumns(10);

		//LABEL (FROM) - y=
		JLabel lblFromY = new JLabel("y=");
		lblFromY.setBounds(636, 18, 14, 14);
		newCreatedMapPanel.add(lblFromY);

		//(FROM) Y INPUT
		textFieldFromY = new JTextField();
		textFieldFromY.setBounds(653, 15, 30, 20);
		newCreatedMapPanel.add(textFieldFromY);
		textFieldFromY.setColumns(10);

		//LABEL - TO:
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(748, 1, 22, 14);
		newCreatedMapPanel.add(lblTo);	

		//LABEL (TO) - x=
		JLabel labelToX = new JLabel("x=");
		labelToX.setBounds(703, 18, 14, 14);
		newCreatedMapPanel.add(labelToX);

		//(TO) X INPUT
		textFieldToX = new JTextField();
		textFieldToX.setColumns(10);
		textFieldToX.setBounds(720, 15, 30, 20);
		newCreatedMapPanel.add(textFieldToX);

		//LABEL (TO) - y=
		JLabel labelToY = new JLabel("y=");
		labelToY.setBounds(760, 18, 14, 14);
		newCreatedMapPanel.add(labelToY);

		//(TO) Y INPUT
		textFieldToY = new JTextField();
		textFieldToY.setColumns(10);
		textFieldToY.setBounds(774, 15, 30, 20);
		newCreatedMapPanel.add(textFieldToY);

		//LABEL Mouse Position
		JLabel labelMousePos = new JLabel("Mouse Position=");
		labelMousePos.setBounds(823, 18, 100, 14);
		newCreatedMapPanel.add(labelMousePos);

		//MousePosX Input
		textFieldMousePosX = new JTextField();
		textFieldMousePosX.setColumns(10);
		textFieldMousePosX.setBounds(922, 15, 37, 20);
		newCreatedMapPanel.add(textFieldMousePosX);

		//MousePosY Input
		textFieldMousePosY = new JTextField();
		textFieldMousePosY.setColumns(10);
		textFieldMousePosY.setBounds(961, 15, 37, 20);
		newCreatedMapPanel.add(textFieldMousePosY);

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
				entered = true;
				drawLines();
			}
		});		
		btnDrawLines.setBounds(207, 11, 145, 24);
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
		JButton btnDrawLinesManually = new JButton("Draw Building Wall");
		btnDrawLinesManually.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				entered = true;
				drawLines();
			}
		});		
		btnDrawLinesManually.setBounds(362, 11, 200, 24);
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
		linesList.add(line);
		line.setForeground(Color.BLACK);
		line.setBounds(0, 0, 1008, 681);
		line.setOpaque(false);
		newCreatedMapPanel.add(line);	
		newCreatedMapPanel.add(lblBackGroundLabel);

		xbegin = Integer.parseInt(textFieldFromX.getText());   //receive input 'from x' text field
		ybegin = Integer.parseInt(textFieldFromY.getText());   //receive input 'from y' text field
		xend = Integer.parseInt(textFieldToX.getText());   //receive input 'to x' text field
		yend = Integer.parseInt(textFieldToY.getText());   //receive input 'to y' text field

		line.coordinateList.set(0, xbegin);
		line.coordinateList.set(1, ybegin);
		line.coordinateList.set(2, xend);
		line.coordinateList.set(3, yend);
		line.repaint();
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

			String strXBegin = Integer.toString(xbegin); 
			String strYBegin = Integer.toString(ybegin);

			textFieldFromX.setText(strXBegin);
			textFieldFromY.setText(strYBegin);
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

			String strXEnd = Integer.toString(xend); 
			String strYEnd = Integer.toString(yend);

			textFieldToX.setText(strXEnd);
			textFieldToY.setText(strYEnd);
			buildingInformation();
			linesList.add(finishedLine);
			line.repaint();
		}
	};

	//Author: Yamil Gonzalez 
	//Last edited: 4/13/2020
	//This method opens and closes the file to prevent other writer methods 
	//To write on the correct file, here also is controlled the amount of times
	//it enters to this method, to prevent the repaint method for damaging the
	//file
	private void buildingInformation() {
		//calls writing functions
		if(entered) {
			entered = false;
			if(walls == newBuildingFrame.getAmountOfWalls()-1) {
				writer.open(locations.load(0));
				writingCoordinates(walls, "wall.png");
				writer.newLine();
				closing();
				walls = 0;
				System.out.println("number of walls completed "+walls);
				return;
			}
			else {
				System.out.println("number of on-going walls "+walls);
				writer.open(locations.load(0));
				writingCoordinates(walls, "wall.png");
				closing();
				walls++;
			}
		}
	}
	
	//Author: Yamil Gonzalez 
	//Last edited: 4/13/2020
	//Writes the wall, wall number and wall coordinates
	private void writingCoordinates(int w,String p) {
		writer.writeSpace("Wall "+w+" = ("+xbegin+","+ybegin+")("+xend+","+yend+")("+newBuildingFrame.getBuildingWidth()+","+newBuildingFrame.getBuildingHeight()+")"+p);
	}
	//Author: Yamil Gonzalez 
	//Last edited: 4/13/2020
	//Closes the file
	private void closing() {
		writer.close();
	}

	public MouseMotionListener mouseMotionHandler = new MouseMotionAdapter() {
		@Override
		public void mouseDragged(MouseEvent e) {
			xend = e.getX();
			yend = e.getY();
			line.coordinateList.set(2, xend);
			line.coordinateList.set(3, yend);

			String strXEnd = Integer.toString(xend); 
			String strYEnd = Integer.toString(yend);

			textFieldToX.setText(strXEnd);
			textFieldToY.setText(strYEnd);
			textFieldMousePosX.setText(strXEnd);
			textFieldMousePosY.setText(strYEnd);

			line.repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			mousePosX = e.getX();
			mousePosY = e.getY();
			String strX = Integer.toString(mousePosX); 
			String strY = Integer.toString(mousePosY);

			textFieldMousePosX.setText(strX);
			textFieldMousePosY.setText(strY);
			repaint();
		}
	};

	/*Author: Valerie Otero | Date: March 22 2020
	 * This method initializes the drop down where the 3 tree options appear with icons
	 * so the designer can choose from those option to place on the map.*/
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
		treeComboBox.setBounds(1025, 14, 163, 23);
		newCreatedMapPanel.add(treeComboBox);
		treeComboBox.setRenderer(renderer);

		treeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				

				insertTrees();

			}
		});
	}


	/*Author: Valerie Otero | Date: March 22 2020
	 * Helper method for initializeTreeDropDown(). This method receives a path to an image,
	 * and turns into a URL type. With this, the image can be set as an image icon for the tree drop down options. 
	 * It also verifies that the image in the path exists.*/
	protected ImageIcon createImageIcon(String path) {

		URL imgURL = getClass().getClassLoader().getResource(path);

		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}


	/*Author: Valerie Otero | Date: March 22 2020
	 * This method saves the selected option from the tree drop down. After that, it awaits for a click anywhere inside
	 * the panel to draw the tree where the designer selected. Calls the Tree class to draw the tree. */
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
					treeWriter((selectedTree+1), e.getX(), e.getY() ,60, 87);				
					
					newCreatedMapPanel.add(tree);
					tree.revalidate();
					tree.repaint();
					newCreatedMapPanel.add(lblBackGroundLabel);	
				}
			}
		});
	}

	public void treeWriter(int image, int X, int Y, int W, int H) {
		Writer.open(locations.load(2));
		Writer.writeSpace("TreeImage"+Integer.toString(image)+" = "+"("+X+","+Y+")("+W+","+H+")");
		Writer.close();
	}

	/*Author: Valerie Otero | Date: March 22 2020
	 * This nested class is a default class of Java Swing for rendering the tree option with the associated image. */
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