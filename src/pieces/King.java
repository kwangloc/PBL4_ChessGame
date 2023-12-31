package pieces;

import java.awt.image.BufferedImage;

import main.Board;
import main.Move;

public class King extends Piece{

	public King(Board board, int col, int row, boolean isWhite) {
		super(board);
		this.col = col;
		this.row = row;
		this.xPos = col * board.tileSize;
		this.yPos = row * board.tileSize;
		
		this.isWhite = isWhite;
		this.name = "King";
		
		this.sprite = sheet.getSubimage(0*sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
	}

	public boolean isValidMovement(int col, int row) {
//		return Math.abs(this.col - col) <= 1 && Math.abs(this.row - row) <= 1 ;
		return Math.abs((col - this.col)*(row - this.row)) == 1 || Math.abs(col - this.col)+Math.abs(row - this.row) == 1 || canCastle(col, row);
	}
	
	// #8
	private boolean canCastle(int newCol, int newRow) {
		if (this.row == newRow) {
			if (isWhite) { // white
				if (newCol == 6) { // short-castle
					Piece rook = board.getPiece(7, newRow);
					if (rook != null && rook.isFirstMove && isFirstMove) {
						return board.getPiece(5, newRow) == null
								&& board.getPiece(6, newRow) == null
								&& !board.checkScanner.isKingChecked(new Move(board, this, 5, newRow));
					}
				} else if (newCol == 2) { // long-castle
					Piece rook = board.getPiece(0, newRow);
					if (rook != null && rook.isFirstMove && isFirstMove) {
						return board.getPiece(3, newRow) == null
								&& board.getPiece(2, newRow) == null
								&& board.getPiece(1, newRow) == null
								&& !board.checkScanner.isKingChecked(new Move(board, this, 3, newRow));
					}
				}
			} else { // black
				if (newCol == 1) { // short-castle
					Piece rook = board.getPiece(0, newRow);
					if (rook != null && rook.isFirstMove && isFirstMove) {
						return board.getPiece(1, newRow) == null
								&& board.getPiece(2, newRow) == null
								&& !board.checkScanner.isKingChecked(new Move(board, this, 2, newRow));
					}
				} else if (newCol == 5) { // long-castle
					Piece rook = board.getPiece(7, newRow);
					if (rook != null && rook.isFirstMove && isFirstMove) {
						return board.getPiece(6, newRow) == null
								&& board.getPiece(5, newRow) == null
								&& board.getPiece(4, newRow) == null
								&& !board.checkScanner.isKingChecked(new Move(board, this, 4, newRow));
					}
				}
			}
			
		}
		return false;
	}
	
}










