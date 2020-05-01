package DesignPanels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.Writer;
import Classes.fileNames;


/*Author: Valerie Otero | Date: March 28 2020
 * Class initializes the New Map Panel when the user clicks such option in previous panel (Design Panel).
 * When the user clicks on the Save button, the constructor calls another 
 * method that initializes the New Created Map Panel (where designer can start designing the map).
 * Also has a wirting method to begin writing to a file */	
public class NewMapPanel extends JPanel{

	private JPanel newMapPanel;

	//Writer/Reader variables
	private String configFile;
	private String path;
	private String pathQuestions;
	private String pathTrees;
	//private String pathVRML;
	private Writer writer;
	private JTextField textFieldBuildingAmount;
	//public static ArrayList<String> fileNames = new ArrayList<String>();	
	fileNames locations = new fileNames();
	private int buildingAmount;

	public int getBuildingAmount() {
		return buildingAmount;
	}

	public void setBuildingAmount(int buildingAmount) {
		this.buildingAmount = buildingAmount;
	}
	
	public NewMapPanel(JFrame frame) {		

		//NEW MAP PANEL
		newMapPanel = new JPanel();
		newMapPanel.setBounds(0, 0, 1220, 681);
		frame.getContentPane().add(newMapPanel);
		newMapPanel.setLayout(null);	

		//LABEL ENTER MAP NAME
		JLabel lblEnterMapName = new JLabel("1. Enter new map name:");
		lblEnterMapName.setBounds(367, 147, 139, 23);
		newMapPanel.add(lblEnterMapName);

		//INPUT - NEW MAP NAME
		JTextField textFieldNewMapName;textFieldNewMapName = new JTextField();
		textFieldNewMapName.setBounds(515, 147, 89, 23);
		newMapPanel.add(textFieldNewMapName);
		textFieldNewMapName.setColumns(10);

		//LABEL - HOW MANY BUILDINGS
		JLabel lblHowManyBuildings = new JLabel("2. How many buildings will be created?");
		lblHowManyBuildings.setBounds(367, 199, 231, 23);
		newMapPanel.add(lblHowManyBuildings);

		//BUILDING AMOUNT INPUT
		textFieldBuildingAmount = new JTextField();
		textFieldBuildingAmount.setBounds(597, 200, 86, 20);
		newMapPanel.add(textFieldBuildingAmount);
		textFieldBuildingAmount.setColumns(10);

		//SAVE BUTTON
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				//Gets the name entered by the user				
				configFile = textFieldNewMapName.getText();
				path = System.getProperty("user.dir")+File.separator+"maps"+File.separator+configFile;
				pathQuestions = System.getProperty("user.dir")+File.separator+"questions"+File.separator+configFile;
				pathTrees = System.getProperty("user.dir")+File.separator+"trees"+File.separator+configFile;
				//pathVRML = System.getProperty("user.dir")+File.separator+"vrmlmap"+File.separator+configFile;
				
				//Validates that the name is not an empty string or empty value 
				if(!(configFile.isEmpty() && buildingAmount==0 ) )  {

					try {
						buildingAmount = Integer.parseInt(textFieldBuildingAmount.getText());

						newMapPanel.setVisible(false);

						CreatedMapPanel createdMapPanel = new CreatedMapPanel(frame, writer);	
						createFileNames(path, pathQuestions, pathTrees);
					}

					catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(null, "Must enter a number for 'How many buildings will be created?'", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Must enter a name and number to save ", "Warning", JOptionPane.INFORMATION_MESSAGE);	
				}
			}});
		btnSave.setBounds(367, 249, 89, 23);
		newMapPanel.add(btnSave);
		
		this.setBuildingAmount(buildingAmount);
	}
	
	/*Author: Yamil Gonzalez | Last edited: March 28 2020
	 * Helper method to create necessary files into the array
	 */
	private void createFileNames(String s, String q, String t) {
		locations.save(s);
		locations.save(q);
		locations.save(t);
		writingHeader(locations.load(0));
		treeQuestionFiles(locations.load(1), locations.load(2));
	}
	
	
	/*Author: Yamil Gonzalez | Last edited: March 28 2020 
	//Helper method to write the name of the map, question, tree and header of each file
	//header -> Map name */
	private void writingHeader(String s) {
		Writer.open(s);
		Writer.write("Map name = ");
		Writer.writeSpace(s);
		Writer.writeSpace("Amount = "+Integer.toString(this.getBuildingAmount()));
		Writer.close();
	}
	private void treeQuestionFiles(String s, String q) {
		Writer.open(s);
		Writer.write("Questions File = ");
		Writer.writeSpace(s);
		Writer.newLine();
		Writer.newLine();
		Writer.close();
		
		Writer.open(q);
		Writer.write("Trees File = ");
		Writer.writeSpace(s);
		Writer.newLine();
		Writer.close();
	}
}