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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/*Author: Valerie Otero | Date: March 8 2020
 * Class initializes the Design panel when the user clicks such option in previous panel (GameModePanel).
 * If the user clicks on the Create New Map button, the constructor calls another 
 * method that initializes the New Map Panel. */	
public class DesignPanel extends JPanel{
	
	//Design Panel Variables
	String filesPath;
	JComboBox<String> comboBoxLoadMap;	
	private JPanel designPanel;
	
	
	
	public DesignPanel(JFrame frame) {		

		//DESIGN PANEL
		designPanel = new JPanel();
		designPanel.setBounds(0, 0, 1220, 681);
		frame.getContentPane().add(designPanel);
		designPanel.setLayout(null);				

		//LABEL CHOOSE MAP
		JLabel lblChooseMap = new JLabel("Create Map:");
		lblChooseMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseMap.setBounds(552, 124, 139, 23);
		designPanel.add(lblChooseMap);

		//NEW MAP BUTTON
		JButton btnNewMap = new JButton("New Map");
		btnNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	

				designPanel.setVisible(false);
				
				NewMapPanel newMapPanel = new NewMapPanel(frame);

			}
		});
		btnNewMap.setBounds(552, 151, 89, 23);
		designPanel.add(btnNewMap);

		//LABEL - LOAD MAP
		JLabel lblLoadMap = new JLabel("Load Map:");
		lblLoadMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoadMap.setBounds(563, 206, 89, 23);
		designPanel.add(lblLoadMap);

		//LOAD MAP DROPDOWN
		comboBoxLoadMap = new JComboBox<String>();	

		filesPath = System.getProperty("user.dir");		

		File directory = new File(filesPath+"\\Maps");

		String[] fileList = directory.list();

		for(String name:fileList){
			comboBoxLoadMap.addItem(name);		
		} 		
		comboBoxLoadMap.setBounds(558, 240, 107, 20);
		designPanel.add(comboBoxLoadMap);
	}

}
