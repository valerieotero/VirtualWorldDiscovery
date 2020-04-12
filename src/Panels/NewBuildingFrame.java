package Panels;
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

public class NewBuildingFrame {

	private JFrame NewBuildingFrame;
	
	private JLabel lblSelectImage;
	private static JLabel lblAmountOfWalls;
		
	private JTextField textFieldBuildingName;
	private JTextField textFieldAmountOfWalls;
	private JTextField textFieldWallHeight;
	private JTextField textFieldWalWidth;
	private JTextField textFieldImage;
	
	private JButton btnUploadImage;
	private JButton btnSave;	
	private JButton btnSaveImage;
	private JButton btnTestCreator;
	
	ImageIcon icon;
	ImageIcon imageIcon;	
	private int count = 0;
	private static String buildingName;
	private int amountOfWalls; 
	private int buildingWidth;
	private int buildingHeight;		
	
	public HashMap<Integer, String> mapQuestions = new HashMap<Integer, String>();
	Writer writer;
	File file;
	TestCreatorFrame TCF = new TestCreatorFrame();
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
	
	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}
	
	
	private void newBuildingInfoFrame() {
		NewBuildingFrame = new JFrame();
		NewBuildingFrame.setBounds(100, 100, 527, 415);
		NewBuildingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		NewBuildingFrame.getContentPane().setLayout(null);

		//LABEL - BUILDING NAME:
		JLabel lblBuildingName = new JLabel("Building name:");
		lblBuildingName.setBounds(22, 21, 86, 14);
		NewBuildingFrame.getContentPane().add(lblBuildingName);

		//BUILDING NAME INPUT FIELD
		textFieldBuildingName = new JTextField();
		textFieldBuildingName.setBounds(128, 18, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldBuildingName);
		textFieldBuildingName.setColumns(10);

		//LABEL - AMOUNT OF WALLS"
		lblAmountOfWalls = new JLabel("Amount of walls: ");
		lblAmountOfWalls.setBounds(22, 57, 100, 14);
		NewBuildingFrame.getContentPane().add(lblAmountOfWalls);

		//INPUT FIELD AMOUNT OF WALLS
		textFieldAmountOfWalls = new JTextField();
		textFieldAmountOfWalls.setBounds(128, 54, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldAmountOfWalls);
		textFieldAmountOfWalls.setColumns(10);

		//BUTTON UPLOAD IMAGE
		btnUploadImage = new JButton("Upload image");		
		btnUploadImage.setBounds(174, 235, 114, 23);
		btnUploadImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnUploadBackgroundImageMouseEvent(e);	

			}
		});
		NewBuildingFrame.getContentPane().add(btnUploadImage);

		//LABEL - SELECT IMAGE
		lblSelectImage = new JLabel("Select image for building:");
		lblSelectImage.setBounds(22, 239, 154, 14);
		NewBuildingFrame.getContentPane().add(lblSelectImage);

		//SAVE BUTTON
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				buildingName = textFieldBuildingName.getText();
				setBuildingName(buildingName);
				try {
					amountOfWalls = Integer.parseInt(textFieldAmountOfWalls.getText());
					buildingHeight = Integer.parseInt(textFieldWallHeight.getText());
					buildingWidth = Integer.parseInt(textFieldWalWidth.getText());		
				}

				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Must enter a number for 'Amount of Walls', 'Height of walls' and 'Width of walls'", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
				count++;
				//For debug
				System.out.println("Building Name: "+ buildingName);		
				System.out.println("Amount Of Walls: " + amountOfWalls);
				System.out.println("Building Height: " + buildingHeight);
				System.out.println("Building Width: " + buildingWidth);
			}
		});
		//Setting variables for getters
		this.setBuildingHeight(buildingHeight);
		this.setBuildingWidth(buildingWidth);
		this.setBuildingName(buildingName);
		this.setAmountOfWalls(amountOfWalls);
		
		btnSave.setBounds(73, 171, 89, 23);
		NewBuildingFrame.getContentPane().add(btnSave);

		//NAME OF IMAGE UPLOADED TEXT FIELD
		textFieldImage = new JTextField();
		textFieldImage.setBounds(22, 270, 114, 20);
		NewBuildingFrame.getContentPane().add(textFieldImage);
		textFieldImage.setColumns(10);

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
		
		//SAVE IMAGE BUTTON
		btnSaveImage = new JButton("Save Image");
		btnSaveImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				imageIcon = icon;
				System.out.println("ImageIcon: " + imageIcon);

			}
		});
		this.setImageIcon(imageIcon);
		//Writer.write(this.getImageIcon());
		btnSaveImage.setBounds(174, 269, 114, 23);
		NewBuildingFrame.getContentPane().add(btnSaveImage);	

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
		btnTestCreator.setBounds(20, 325, 230, 23);
		NewBuildingFrame.getContentPane().add(btnTestCreator);
		
		NewBuildingFrame.setVisible(true);
	}


	public void btnUploadBackgroundImageMouseEvent(MouseEvent e) {

		JFileChooser chooser = new JFileChooser();
		BufferedImage img;	   				    				  

		if (e.getSource()==btnUploadImage) {
			chooser.showOpenDialog(null);
			file = chooser.getSelectedFile();

			try {
				img=ImageIO.read(file);
				icon=new ImageIcon(img);										
			}

			catch(IOException e1) {
				System.out.println("Must select an image");
			}

			textFieldImage.setText(file.getName());			
		}
	}

	public boolean areTheFieldsCompleted() {
		if(imageIcon!=null && amountOfWalls!=0 && buildingName!=null && buildingWidth!=0 && buildingHeight!=0) {
			buildingInfo();
			//mapQuestions.put(count, TCF.getComboQAndA());
			return true;
		}
		else {
			return false;
		}
	}
	
	public void buildingInfo() {
		Writer.open(locations.load(0));
		Writer.buildingInfo(this.getBuildingName(), count, file);
		Writer.newLine();
		Writer.close();
	}
}