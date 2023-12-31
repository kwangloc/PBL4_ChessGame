//package main;
//
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.net.Socket;
//
//import pieces.Piece;
//
//public class Input extends MouseAdapter {
//	
//	Board board;
//	Socket soc;
//	
//	public Input(Board board, Socket soc) {
//		this.board = board;
//		this.soc = soc;
//	}
//	
//	@Override
//	public void mousePressed(MouseEvent e) {
//		
//		int col = e.getX()/board.tileSize;
//		int row = e.getY()/board.tileSize;
//		
//		Piece pieceXY = board.getPiece(col, row);
//		if (pieceXY != null) {
//			board.selectedPiece = pieceXY;
//		}
//	}
//	
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		if(board.selectedPiece != null) {
//			// cập nhật xPos, yPos của selectedPiece
//			board.selectedPiece.xPos = e.getX() - board.tileSize/2; // the piece is centered on the mouse
//			board.selectedPiece.yPos = e.getY() - board.tileSize/2;
//			
//			board.repaint();
//		}
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		
//		int col = e.getX()/board.tileSize;
//		int row = e.getY()/board.tileSize;
//		
//		if(board.selectedPiece != null) {
//			Move move = new Move(board, board.selectedPiece, col, row);
//			
//			if(board.isValidMove(move)) {
//				// thay vi makeMove, gui move cho server
////				board.makeMove(move);
//				
//				//
//			} else {
//				// nếu nước đi không hợp lệ, reset xPos, yPos lại như ban đầu
//				board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
//				board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
//			}
//		}
//		
//		board.selectedPiece = null;
////		board.repaint();
//	}
//}
