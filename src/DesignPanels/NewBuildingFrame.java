package DesignPanels;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Classes.Writer;
import Classes.fileNames;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/*Author: Valerie Otero | Date: March 31 2020
 * This class defines a new frame when the designer selects the "New Building" button.
 * Here the designer can enter information that will be associated to a building. */
public class NewBuildingFrame {

	private JFrame NewBuildingFrame;

	private JLabel lblBuildingImage;
	private static JLabel lblAmountOfWalls;

	private JTextField textFieldBuildingName;
	private JTextField textFieldAmountOfWalls;
	private JTextField textFieldWallHeight;
	private JTextField textFieldWalWidth;
	private JTextField textFieldBuildingImage;
	private JTextField textFieldWallImage;

	private JButton btnUploadBuildingImage;
	private JButton btnUploadWallImage;
	private JButton btnSave;	
	private JButton btnSaveImage;
	private JButton btnTestCreator;

	ImageIcon wallIcon;
	ImageIcon buildingIcon;

	ImageIcon wallImageIcon;
	ImageIcon buildingImageIcon;	

	private static String buildingName;

	private int count = 0;	
	private int amountOfWalls; 
	private int buildingWidth;
	private int buildingHeight;		

	public HashMap<Integer, String> mapQuestions = new HashMap<Integer, String>();
	Writer writer;	
	File wallFile;
	File buildingFile;
	fileNames locations = new fileNames();



	//CONSTRUCTOR
	public NewBuildingFrame() {
		newBuildingInfoFrame();
	}


	//GETTERS AND SETTERS
	public static String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getAmountOfWalls() {
		return amountOfWalls;
	}

	public void setAmountOfWalls(int amountOfWalls) {
		this.amountOfWalls = amountOfWalls;
	}

	public int getBuildingWidth() {
		return buildingWidth;
	}

	public void setBuildingWidth(int buildingWidth) {
		this.buildingWidth = buildingWidth;
	}

	public int getBuildingHeight() {
		return buildingHeight;
	}

	public void setBuildingHeight(int buildingHeight) {
		this.buildingHeight = buildingHeight;
	}

	public File getWallFile() {
		return wallFile;
	}


	public void setWallFile(File wallFile) {
		this.wallFile = wallFile;
	}


	public File getBuildingFile() {
		return buildingFile;
	}


	public void setBuildingFile(File buildingFile) {
		this.buildingFile = buildingFile;
	}


	/*Author: Valerie Otero | Date: March 31 2020
	 * This method initializes the frame with all the components for the new building information. */
	private void newBuildingInfoFrame() {

		NewBuildingFrame = new JFrame();
		NewBuildingFrame.setBounds(100, 100, 527, 497);
		NewBuildingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		NewBuildingFrame.getContentPane().setLayout(null);
		NewBuildingFrame.setVisible(true);

		//LABEL - BUILDING NAME:
		JLabel lblBuildingName = new JLabel("Building name:");
		lblBuildingName.setBounds(22, 21, 86, 14);
		NewBuildingFrame.getContentPane().add(lblBuildingName);

		//BUILDING NAME INPUT FIELD
		textFieldBuildingName = new JTextField();
		textFieldBuildingName.setBounds(128, 18, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldBuildingName);
		textFieldBuildingName.setColumns(10);

		//LABEL - AMOUNT OF WALLS
		lblAmountOfWalls = new JLabel("Amount of walls: ");
		lblAmountOfWalls.setBounds(22, 57, 100, 14);
		NewBuildingFrame.getContentPane().add(lblAmountOfWalls);

		//INPUT FIELD AMOUNT OF WALLS
		textFieldAmountOfWalls = new JTextField();
		textFieldAmountOfWalls.setBounds(128, 54, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldAmountOfWalls);
		textFieldAmountOfWalls.setColumns(10);

		//LABEL - SELECT BUILDING IMAGE
		lblBuildingImage = new JLabel("Select image for building:");
		lblBuildingImage.setBounds(22, 239, 154, 14);
		NewBuildingFrame.getContentPane().add(lblBuildingImage);

		//LABEL - SELECT IMAGE FOR WALLS
		JLabel lblSelectImageFor = new JLabel("Select image for walls:");
		lblSelectImageFor.setBounds(22, 284, 140, 14);
		NewBuildingFrame.getContentPane().add(lblSelectImageFor);

		//NAME OF WALL IMAGE UPLOADED TEXT FIELD
		textFieldWallImage = new JTextField();
		textFieldWallImage.setBounds(353, 281, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldWallImage);
		textFieldWallImage.setColumns(10);

		//NAME OF IMAGE UPLOADED TEXT FIELD
		textFieldBuildingImage = new JTextField();
		textFieldBuildingImage.setBounds(353, 236, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldBuildingImage);
		textFieldBuildingImage.setColumns(10);

		//LABEL - WALL HEIGHT
		JLabel lblHeightOfWalls = new JLabel("Height of walls:");
		lblHeightOfWalls.setBounds(22, 92, 86, 14);
		NewBuildingFrame.getContentPane().add(lblHeightOfWalls);

		//LABEL - WALL WIDTH
		JLabel lblWidthOfWalls = new JLabel("Width of walls:");
		lblWidthOfWalls.setBounds(22, 126, 86, 14);
		NewBuildingFrame.getContentPane().add(lblWidthOfWalls);

		//WALL HEIGHT INPUT
		textFieldWallHeight = new JTextField();
		textFieldWallHeight.setBounds(128, 89, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldWallHeight);
		textFieldWallHeight.setColumns(10);

		//WALL WIDTH INPUT
		textFieldWalWidth = new JTextField();
		textFieldWalWidth.setBounds(128, 123, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldWalWidth);
		textFieldWalWidth.setColumns(10);

		//SAVE BUTTON
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setBuildingName(textFieldBuildingName.getText());
				
				try {
					saveAmountOfWalls(Integer.parseInt(textFieldAmountOfWalls.getText()));
					
					//Setting variables for getters					
					setBuildingHeight(Integer.parseInt(textFieldWallHeight.getText()));
					setBuildingWidth(Integer.parseInt(textFieldWalWidth.getText()));											
				}

				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Must enter a number for 'Amount of Walls', 'Height of walls' and 'Width of walls'", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
				//For debug
				System.out.println("Building Name: "+ getBuildingName());		
				System.out.println("Amount Of Walls: " + getAmountOfWalls());
				System.out.println("Building Height: " + getBuildingHeight());
				System.out.println("Building Width: " + getBuildingWidth());
			}
		});
		btnSave.setBounds(72, 164, 89, 23);
		NewBuildingFrame.getContentPane().add(btnSave);

		
		//BUTTON BUILDING IMAGE
		btnUploadBuildingImage = new JButton("Upload building image");		
		btnUploadBuildingImage.setHorizontalAlignment(SwingConstants.LEFT);
		btnUploadBuildingImage.setBounds(172, 235, 160, 23);
		btnUploadBuildingImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				UploadBuildingImage(e);	

			}
		});
		NewBuildingFrame.getContentPane().add(btnUploadBuildingImage);

		// BUTTON WALL IMAGE
		btnUploadWallImage = new JButton("Upload wall image");
		btnUploadWallImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				UploadWallImage(arg0);
			}
		});
		btnUploadWallImage.setBounds(172, 280, 154, 23);
		NewBuildingFrame.getContentPane().add(btnUploadWallImage);

		//SAVE IMAGES BUTTON
		btnSaveImage = new JButton("Save Images");
		btnSaveImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				buildingImageIcon = buildingIcon;
				wallImageIcon = wallIcon;

				//debug
				System.out.println("BuildingImageIcon: " + buildingImageIcon);
				System.out.println("WallImageIcon: " + wallImageIcon);

			}
		});
		btnSaveImage.setBounds(100, 331, 114, 23);
		NewBuildingFrame.getContentPane().add(btnSaveImage);	
		
		this.setBuildingFile(buildingFile);
		this.setWallFile(wallFile);

		NewBuildingFrame.addWindowListener(new WindowAdapter() {			

			public void windowClosing(WindowEvent e) {
				if(areTheFieldsCompleted()) {
					NewBuildingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});

		//MAKE TEST BUTTON
		btnTestCreator = new JButton("Add Questions with Answers");
		btnTestCreator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TestCreatorFrame testFrame = new TestCreatorFrame();
			}
		});
		btnTestCreator.setBounds(22, 401, 230, 23);
		NewBuildingFrame.getContentPane().add(btnTestCreator);

	}
	
	public void saveAmountOfWalls(int walls) {
		this.setAmountOfWalls(walls);
	}

	/*Author: Valerie Otero | Date: March 31 2020
	 * Method is called in the newBuildingInfoFrame() when the map designer selects to "Upload Building Image". 
	 * It initializes a chooser box that lets the designer select an image from their computer. */		
	public void UploadBuildingImage(MouseEvent e) {

		JFileChooser chooser = new JFileChooser();
		BufferedImage img;	   				    				  

		if (e.getSource()==btnUploadBuildingImage) {
			chooser.showOpenDialog(null);
			buildingFile = chooser.getSelectedFile();

			try {
				img=ImageIO.read(buildingFile);
				buildingIcon=new ImageIcon(img);										
			}

			catch(IOException e1) {
				System.out.println("Must select an image");
			}

			textFieldBuildingImage.setText(buildingFile.getName());			
		}
	}

	/*Author: Valerie Otero | Date: March 31 2020
	 * Method is called in the newBuildingInfoFrame() when the map designer selects to "Upload Wall Image". 
	 * It initializes a chooser box that lets the designer select an image from their computer. */		
	public void UploadWallImage(MouseEvent e) {

		JFileChooser chooser = new JFileChooser();
		BufferedImage img;	   				    				  

		if (e.getSource()==btnUploadWallImage) {
			chooser.showOpenDialog(null);
			wallFile = chooser.getSelectedFile();

			try {
				img=ImageIO.read(wallFile);
				wallIcon=new ImageIcon(img);										
			}

			catch(IOException e1) {
				System.out.println("Must select an image");
			}

			textFieldWallImage.setText(wallFile.getName());			
		}
	}

	/*Author: Valerie Otero | Date: March 31 2020
	 * Method is called in the newBuildingInfoFrame() when the map designer wants to close. 
	 * It verifies that all fields are saved, if so then the deigner can close the frame. */		
	public boolean areTheFieldsCompleted() {

		if(buildingImageIcon!=null && wallImageIcon!=null&& amountOfWalls!=0 && buildingName!=null && buildingWidth!=0 && buildingHeight!=0) {
			buildingInfo();
			count++;
			return true;
		}
		else {
			return false;
		}
	}

	//Author: Yamil Gonzalez 
	//Last Edited: 4/15/2020
	//Writes opens, write the building information and closes the file
	public void buildingInfo() {
		Writer.open(locations.load(0));
		Writer.buildingInfo(this.getBuildingName(), count, buildingFile);
		Writer.newLine();
		Writer.close();
	}
}