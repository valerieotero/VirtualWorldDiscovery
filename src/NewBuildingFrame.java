import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewBuildingFrame {

	private JFrame NewBuildingFrame;
	private JTextField textFieldBuildingName;
	private static JLabel lblAmountOfWalls;
	private JTextField textFieldAmountOfWalls;
	private JButton btnUploadImage;
	private JLabel lblSelectImage;
	ImageIcon icon;

	ImageIcon imageIcon; 
	int amountOfWalls; 
	String buildingName;
	private JButton btnSave;
	private JTextField textFieldImage;
	private JButton btnSaveImage;

	
	public NewBuildingFrame() {
		newBuildingInfoFrame();
	}


	private void newBuildingInfoFrame() {

		NewBuildingFrame = new JFrame();
		NewBuildingFrame.setBounds(100, 100, 450, 300);
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
		numberConvertion();
		
		//INPUT FIELD AMOUNT OF WALLS
		textFieldAmountOfWalls = new JTextField();
		textFieldAmountOfWalls.setBounds(128, 54, 86, 20);
		NewBuildingFrame.getContentPane().add(textFieldAmountOfWalls);
		textFieldAmountOfWalls.setColumns(10);

		//BUTTON UPLOAD IMAGE
		btnUploadImage = new JButton("Upload image");		
		btnUploadImage.setBounds(174, 171, 114, 23);
		btnUploadImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnUploadBackgroundImageMouseEvent(e);	

			}
		});
		NewBuildingFrame.getContentPane().add(btnUploadImage);

		//LABEL - SELECT IMAGE
		lblSelectImage = new JLabel("Select image for building:");
		lblSelectImage.setBounds(22, 175, 154, 14);
		NewBuildingFrame.getContentPane().add(lblSelectImage);

		//SAVE BUTTON
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				buildingName = textFieldBuildingName.getText();

				try {
					amountOfWalls = Integer.parseInt(textFieldAmountOfWalls.getText());
				}

				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Must enter a number for 'Amount of Walls'", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}

				System.out.println("Building Name: "+ buildingName);		
				System.out.println("Amount Of Walls: " + amountOfWalls);
			}
		});

		btnSave.setBounds(71, 95, 89, 23);
		NewBuildingFrame.getContentPane().add(btnSave);

		//NAME OF IMAGE UPLOADED TEXT FIELD
		textFieldImage = new JTextField();
		textFieldImage.setBounds(22, 200, 114, 20);
		NewBuildingFrame.getContentPane().add(textFieldImage);
		textFieldImage.setColumns(10);

		//SAVE IMAGE BUTTON
		btnSaveImage = new JButton("Save Image");
		btnSaveImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				imageIcon = icon;
				System.out.println("ImageIcon: " + icon);

			}
		});
		btnSaveImage.setBounds(174, 200, 114, 23);
		NewBuildingFrame.getContentPane().add(btnSaveImage);

		NewBuildingFrame.addWindowListener(new WindowAdapter() {			

			public void windowClosing(WindowEvent e) {
				if(areTheFieldsCompleted()) {
					NewBuildingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});
		
		NewBuildingFrame.setVisible(true);
	}


	public void btnUploadBackgroundImageMouseEvent(MouseEvent e) {

		JFileChooser chooser = new JFileChooser();
		BufferedImage img;	   				   
		File file ; 				  

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

		if(imageIcon!=null && amountOfWalls!=0 && buildingName!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//takes the input number of walls and save it.
	public static int numberConvertion() {
		int wallNumber = 0;
		try {
			wallNumber = Integer.parseInt(lblAmountOfWalls.getText());
		}catch(NumberFormatException ex) {
			System.out.println("Not a number, try again");
		}
		return wallNumber;
	}
}
