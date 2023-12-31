package pieces;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Board;

public class Piece {

	
	public int col, row;
	public int xPos, yPos;
	
	public boolean isWhite;
	public String name;
	public int value;
	
	public boolean isFirstMove = true;
	// read image
	BufferedImage sheet;
	{
		try {
			sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("./res/pieces.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// get width of each piece
	protected int sheetScale = sheet.getWidth()/6;
	
	Image sprite;
	
	Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	// move validation
	
	public boolean isValidMovement(int col, int row) {return true;}
	public boolean moveCollidesWithPiece(int col, int row) {return false;}
	//
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xPos, yPos, null);
	}
}
