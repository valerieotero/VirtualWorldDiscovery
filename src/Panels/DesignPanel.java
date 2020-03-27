package Panels;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DesignPanel extends JPanel{
	
	//Design Panel Variables
	String filesPath;
	JComboBox<String> comboBoxLoadMap;	
	private JPanel designPanel;
	
	
	/*Author: Valerie Otero | Date: March 8 2020
	 * Method initializes the design panel when the user clicks such option.
	 * If the user clicks on the Create New Map option, this method calls another 
	 * method that initializes that panel.
	*/	
	public DesignPanel(JFrame frame) {		

		//DESIGN PANEL
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

				designPanel.setVisible(false);
				
				NewMapPanel newMapPanel = new NewMapPanel(frame);

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

}
