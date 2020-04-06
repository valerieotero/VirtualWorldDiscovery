package Panels;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* Author: Valerie Otero | Date: March 8 2020	
 * Class initializes the main panel where the user can choose if it wants
 * to Design or Play. If the user clicks Design button, the constructor calls another method
 * that initializes the Design Panel. (Play button is not enabled for now)	 */		
public class GameModePanel extends JPanel {
	
	
	private JPanel gameModePanel;		
	

	public GameModePanel(JFrame frame) {

		//NEW GAME MODE PANEL
		gameModePanel = new JPanel();
		gameModePanel.setBounds(0, 0, 1220, 681);
		frame.getContentPane().add(gameModePanel);
		gameModePanel.setLayout(null);

		//LABEL-CHOOSE GAME MODE
		JLabel lblChooseGameMode = new JLabel("Choose game mode:");
		lblChooseGameMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseGameMode.setBounds(556, 127, 139, 23);
		gameModePanel.add(lblChooseGameMode);

		//DESIGN BUTTON
		JButton btnDesign = new JButton("Design");
		btnDesign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				gameModePanel.setVisible(false);
				
				DesignPanel designPanel = new DesignPanel(frame);				

			}
		});
		btnDesign.setBounds(566, 160, 89, 23);
		gameModePanel.add(btnDesign);

		//PLAY BUTTON
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPlay.setBounds(566, 194, 89, 23);
		gameModePanel.add(btnPlay);	
		
	}
}
