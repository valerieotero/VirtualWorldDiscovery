package Panels;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Classes.Line;
import Classes.Reader;
import Classes.Walls;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class EditExistingMapPanel extends JPanel{


	JLabel lblbackgroundImage;


	public EditExistingMapPanel(JFrame frame, String mapName){

		frame.getContentPane().add(this);
		this.setBounds(0, 0, 1220, 681);
		this.setLayout(null);				
		this.setVisible(true);	
		initializePanelComponents(mapName);	
	}

	
	public void initializePanelComponents(String mapName) {

		try {
			Reader.mapReaderController(mapName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");			
		}

		addBackgorundImage();
		addWalls();
	}		
	
	
	public void addBackgorundImage() {
		
		//LABEL-WHERE BACKGROUND IMAGE IS SET	
		lblbackgroundImage = new JLabel("");
		lblbackgroundImage.setIcon(new ImageIcon(Reader.getBackground()));
		lblbackgroundImage.setBounds(0, 46, 1220, 678);
		this.add(lblbackgroundImage);
	}
	

	public void addWalls() {

		for(Map.Entry<Integer,LinkedList<Walls>> buildings : Reader.getBuildings().entrySet()) {

			for(Walls wall : buildings.getValue()) {

				Line line = new Line();

				line.setForeground(Color.BLACK);
				line.setBounds(0, 0, 1008, 681);
				line.setOpaque(false);

				this.add(line);
				this.add(lblbackgroundImage);

				line.coordinateList.set(0, wall.getX1());
				line.coordinateList.set(1, wall.getY1());
				line.coordinateList.set(2, wall.getX2());
				line.coordinateList.set(3, wall.getY2());

				line.repaint();
			}

		}

	}
}
