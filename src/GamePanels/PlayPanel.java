package GamePanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.Component;


public class PlayPanel {
	
	private JPanel playPanel;
		
	//AVATAR DROPDOWN VARIABLES	
	JComboBox<?> comboBoxAvatar;
	String[] avatarStrings = {"megaManIcon", "marioIcon"};	
	ImageIcon[] images;
	int chosenAvatar;
	
	JComboBox<String> comboBoxLoadMap;	
	String filesPath;
	String mapName;	
	
	
	public PlayPanel(JFrame frame) {
		
		//PLAY PANEL
		playPanel = new JPanel();
		playPanel.setBounds(0, 0, 1220, 681);
		frame.getContentPane().add(playPanel);
		playPanel.setLayout(null);	
				
		
		JLabel lblChooseAvatar = new JLabel("Choose avatar:");
		lblChooseAvatar.setForeground(Color.DARK_GRAY);
		lblChooseAvatar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseAvatar.setBounds(530, 155, 120, 14);
		playPanel.add(lblChooseAvatar);
		
		//AVATAR DROPDOWN
		initializeTreeDropDown();			
		
		//LABEL - LOAD MAP
		JLabel lblLoadMap = new JLabel("Choose map to play:");
		lblLoadMap.setForeground(Color.DARK_GRAY);
		lblLoadMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoadMap.setBounds(530, 236, 138, 23);
		playPanel.add(lblLoadMap);
		
		//LOAD MAP DROPDOWN
		comboBoxLoadMap = new JComboBox<String>();	
		filesPath = System.getProperty("user.dir");		
		File directory = new File(filesPath+"\\Maps");
		String[] fileList = directory.list();

		for(String name:fileList){
			comboBoxLoadMap.addItem(name);		
		} 		
		comboBoxLoadMap.setBounds(543, 270, 125, 32);
		playPanel.add(comboBoxLoadMap);
		
		//LOAD BUTTON
		JButton btnLoad = new JButton("Load");	
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				mapName = (String) comboBoxLoadMap.getSelectedItem();
								
				PlayingFrame gameFrame = new PlayingFrame(mapName, chosenAvatar); 
				gameFrame.setTitle(mapName);			

			}
		});
		btnLoad.setBounds(687, 275, 107, 23);
		playPanel.add(btnLoad);	
	}
	
	public void initializeTreeDropDown() {

		images = new ImageIcon[avatarStrings.length];

		Integer[] intArray = new Integer[avatarStrings.length];

		for (int i = 0; i < avatarStrings.length; i++) {
			intArray[i] = new Integer(i);
			images[i] = createImageIcon("Resources/" + avatarStrings[i] + ".png");			
		}                    

		//TREE DROPDOWN
		comboBoxAvatar = new JComboBox(intArray);
		comboBoxAvatar.setBounds(543, 180, 125, 31);
		playPanel.add(comboBoxAvatar);
		comboBoxAvatar.setRenderer(new TreeDropDownRenderer());

		comboBoxAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				
				chosenAvatar = comboBoxAvatar.getSelectedIndex();
				//System.out.println(chosenAvatar);
			}
		});
	}
	
	protected ImageIcon createImageIcon(String path) {

		URL imgURL = getClass().getClassLoader().getResource(path);

		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	
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
				setText("Avatar " + (selectedIndex+1));				
			} 
			return this;
		}
	}
}
