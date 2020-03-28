package Panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.Writer;

public class NewMapPanel extends JPanel{
	
	private JPanel newMapPanel;
	
	//Writer/Reader variables
	private String configFile;
	private String path;
	private Writer writer;

	public NewMapPanel(JFrame frame) {		

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
				configFile = textFieldNewMapName.getText();
				path = System.getProperty("user.dir")+File.separator+"maps"+File.separator+configFile;

				//Validates that the name is not an empty string
				if(!(configFile.isEmpty())) {
					writingHeader(path);
					newMapPanel.setVisible(false);
					
					CreatedMapPanel createdMapPanel = new CreatedMapPanel(frame, writer);					
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
		//writer.close();
	}
}
