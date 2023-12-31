package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(new GridBagLayout());
		frame.setMinimumSize(new Dimension(800, 800));
		frame.setLocationRelativeTo(null);
		
//		Board board = new Board("localhost", 4567);
//		frame.add(board);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	} 

}
