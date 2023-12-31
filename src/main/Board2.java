//package main;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.awt.geom.AffineTransform;
//import java.awt.image.AffineTransformOp;
//import java.awt.image.BufferedImage;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.net.Socket;
//import java.util.ArrayList;
//
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import pieces.Bishop;
//import pieces.King;
//import pieces.Knight;
//import pieces.Pawn;
//import pieces.Piece;
//import pieces.Queen;
//import pieces.Rook;
//
//public class Board extends JPanel implements MouseListener,MouseMotionListener,Runnable {
//	public int tileSize = 85;
//	int cols = 8;
//	int rows = 8;
//	
//	int off = 150;
//	ArrayList<Piece> pieceList = new ArrayList<>();
//	public Piece selectedPiece; // the piece that you're moving
////	Input input = new Input(this);
//	public CheckScanner checkScanner = new CheckScanner(this); // #7
//	public int enPassantTile = -1;
//	
//	
//	BufferedImage image;
//	Graphics g;
//	
//	//
//	Socket soc;
//	//
//	
////	public static void main(String[] args) {
////		new Board();
////		
////	}
//	public Board() {
//		image = new BufferedImage(cols * tileSize + 2*off, rows * tileSize + 2*off, 3);
//		g = image.getGraphics();
//		
//		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
//		
////		this.setTitle("Chess Client");
////		this.setDefaultCloseOperation(3);
////		this.setSize(cols * tileSize + 2*off, rows * tileSize + 2*off);
//		//
////		this.getContentPane().setBackground(Color.black);
////		this.setLayout(new GridBagLayout());
////		this.setMinimumSize(new Dimension(1000, 1000));
////		this.setLocationRelativeTo(null);
//		
//		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
//		//
//		
//		addPieces();
//		
//		this.addMouseListener(this);
//		this.addMouseMotionListener(this);
////		this.setVisible(true);
////		this.add(this);
////		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		
//		
////		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
////		this.addMouseListener(input);
////		this.addMouseListener(this);
////		this.addMouseMotionListener(input);
////		this.addMouseMotionListener(this);
//		
//		
////		addPieces();
//		//
//		try {
//			this.soc = new Socket("localhost", 4567);
//			Thread t = new Thread(this); // luồng theo dõi tín hiệu từ server
//			t.start();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//	}
//	
//	public void run() {
//		while(true) {
//			try {
//				DataInputStream dis = new DataInputStream(soc.getInputStream());
//				int col = Integer.parseInt(dis.readUTF());
//				int row = Integer.parseInt(dis.readUTF());
//				int newCol = Integer.parseInt(dis.readUTF());
//				int newRow = Integer.parseInt(dis.readUTF());
//				
//				
//				if (col == -9999)
//				{
//					JOptionPane.showMessageDialog(this, "The opponent quit. You win!");
//					System.exit(0);
//				}
//				
////				dadanh.add(new Point(ix, iy));
//				System.out.println("Client get: " + col + "," + row + "," + newCol + "," + newRow);
//				makeMove(new Move(this, getPiece(col, row),	 newCol, newRow));
//				this.repaint();
//			} catch (Exception e) {
//				// trong trường hợp server ko phản hồi, ko để while(true) chạy vô hạn
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e1) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
//	public Piece getPiece(int col, int row) {
//		for (Piece piece: pieceList) {
//			if(piece.col == col && piece.row == row) {
//				return piece;
//			}
//		}
//		return null;
//	}
//	
//	public void makeMove(Move move) {
//		
//		if (move.piece.name.equals("Pawn")) { // En Passant
//			movePawn(move);
//		} else {
//			move.piece.col = move.newCol;
//			move.piece.row = move.newRow;
//			move.piece.xPos = move.newCol*tileSize;
//			move.piece.yPos = move.newRow*tileSize;
//		
//			move.piece.isFirstMove = false;
//			
//			capture(move.capture);
//		}
//	}
//	
//	private void movePawn(Move move) {
//		// en passant
//		
//		int colorIndex = move.piece.isWhite ? 1 : -1;
//		
//		if (getTileNum(move.newCol, move.newRow) == enPassantTile) {
//			move.capture = getPiece(move.newCol, move.newRow + colorIndex);
//		}
//		
//		if(Math.abs(move.piece.row - move.newRow) == 2) {
//			enPassantTile = getTileNum(move.newCol, move.newRow + colorIndex);
//		} else {
//			enPassantTile = -1;
//		}
//		
//		// promotions
//		colorIndex = move.piece.isWhite ? 0 : 7;
//		if (move.newRow == colorIndex) {
//			promotionPawn(move);
//		}
//		
//		move.piece.col = move.newCol;
//		move.piece.row = move.newRow;
//		move.piece.xPos = move.newCol*tileSize;
//		move.piece.yPos = move.newRow*tileSize;
//	
//		move.piece.isFirstMove = false;
//		
//		capture(move.capture);
//	}
//	
//	public void promotionPawn(Move move) {
//		pieceList.add(new Queen(this, move.newCol, move.newRow, move.piece.isWhite));
//		capture(move.piece);
//	}
//	
////	public void capture(Move move) {
////		pieceList.remove(move.capture);
////	}
//	public void capture(Piece piece) {
//		pieceList.remove(piece);
//	}
//	
//	public boolean isValidMove(Move move) {
//		if (sameTeam(move.piece, move.capture)) {
//			return false;
//		}
//		
//		if(!move.piece.isValidMovement(move.newCol, move.newRow)) {
//			return false;
//		}
//		if(move.piece.moveCollidesWithPiece(move.newCol, move.newRow)) {
//			return false;
//		}
//		// #7 king check and pinned pieces
//		if (checkScanner.isKingChecked(move)) {
//			return false;
//		}
//		//
//		
//		return true;
//	}
//	
//	public boolean sameTeam(Piece p1, Piece p2) {
//		if (p1 == null || p2 == null) {
//			return false;
//		}
//		return p1.isWhite == p2.isWhite;
//	}
//	
//
//	public int getTileNum(int col, int row) {
//		return row*rows+col;
//	}
//	
//	Piece findKing(boolean isWhite) {
//		for(Piece piece:pieceList) {
//			if (isWhite == piece.isWhite && piece.name.equals("King")) {
//				return piece;
//			}
//		}
//		return null;
//	}
//	
//	public void addPieces() {
//// 		WHITE VIEW
////		Black tiles
//		pieceList.add(new Rook(this, 0, 0, false));
//		pieceList.add(new Knight(this, 1, 0, false));
//		pieceList.add(new Bishop(this, 2, 0, false));
//		pieceList.add(new Queen(this, 3, 0, false));
//		pieceList.add(new King(this, 4, 0, false));
//		pieceList.add(new Bishop(this, 5, 0, false));
//		pieceList.add(new Knight(this, 6, 0, false));
//		pieceList.add(new Rook(this, 7, 0, false));
//		
//		pieceList.add(new Pawn(this, 0, 1, false));
//		pieceList.add(new Pawn(this, 1, 1, false));
//		pieceList.add(new Pawn(this, 2, 1, false));
//		pieceList.add(new Pawn(this, 3, 1, false));
//		pieceList.add(new Pawn(this, 4, 1, false));
//		pieceList.add(new Pawn(this, 5, 1, false));
//		pieceList.add(new Pawn(this, 6, 1, false));
//		pieceList.add(new Pawn(this, 7, 1, false));
//		
////		White tiles
//		pieceList.add(new Rook(this, 0, 7, true));
//		pieceList.add(new Knight(this, 1, 7, true));
//		pieceList.add(new Bishop(this, 2, 7, true));
//		pieceList.add(new Queen(this, 3, 7, true));
//		pieceList.add(new King(this, 4, 7, true));
//		pieceList.add(new Bishop(this, 5, 7, true));
//		pieceList.add(new Knight(this, 6, 7, true));
//		pieceList.add(new Rook(this, 7, 7, true));
//		
//		pieceList.add(new Pawn(this, 0, 6, true));
//		pieceList.add(new Pawn(this, 1, 6, true));
//		pieceList.add(new Pawn(this, 2, 6, true));
//		pieceList.add(new Pawn(this, 3, 6, true));
//		pieceList.add(new Pawn(this, 4, 6, true));
//		pieceList.add(new Pawn(this, 5, 6, true));
//		pieceList.add(new Pawn(this, 6, 6, true));
//		pieceList.add(new Pawn(this, 7, 6, true));
//
//	}
//	
////	public void paintComponent(Graphics g) {
////		Graphics2D g2d = (Graphics2D) g;
////		// paint board
////		for (int r = 0; r < rows; r++) {
////			for (int c = 0; c < cols; c++) {
////				g2d.setColor((c + r)%2 == 0 ? new Color(227, 198, 181):new Color(157, 105, 53));
////				g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
////			}
////		}
////		// paint highlight available moves of selectedPiece
////		if(selectedPiece != null) {
////			for (int r = 0; r < rows; r++) {
////				for (int c = 0; c < cols; c++) {
////					if (isValidMove(new Move(this, selectedPiece, c, r))) {
////						g2d.setColor(new Color(68, 180, 57, 190));
////						g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
////					}
////				}
////			}
////		}
////		// paint pieces
////		for (Piece piece:pieceList) {
////			piece.paint(g2d);
////		}
////	}
//	
//	public void paint(Graphics g1) {
//		Graphics2D g2d = (Graphics2D) g;
//		// paint board
//		for (int r = 0; r < rows; r++) {
//			for (int c = 0; c < cols; c++) {
//				g2d.setColor((c + r)%2 == 0 ? new Color(227, 198, 181):new Color(157, 105, 53));
//				g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
//			}
//		}
//		// paint highlight available moves of selectedPiece
//		if(selectedPiece != null) {
//			for (int r = 0; r < rows; r++) {
//				for (int c = 0; c < cols; c++) {
//					if (isValidMove(new Move(this, selectedPiece, c, r))) {
//						g2d.setColor(new Color(68, 180, 57, 190));
//						g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
//					}
//				}
//			}
//		}
//		// paint pieces
//		for (Piece piece:pieceList) {
//			piece.paint(g2d);
//		}
//		
//		g1.drawImage(image, 0, 0, null);
//		//
////		AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
////		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
////		tx.translate(-image.getWidth(null), -image.getHeight(null));
////		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
////		image = op.filter(image, null);
////		g1.drawImage(image, 0, 0, null);
//	}
//	
//	
//	
//	// Mouse event handling
//	@Override
//	public void mousePressed(MouseEvent e) {
//		int col = e.getX()/this.tileSize;
//		int row = e.getY()/this.tileSize;
//		
//		Piece pieceXY = this.getPiece(col, row);
//		if (pieceXY != null) {
//			this.selectedPiece = pieceXY;
//		}
//		this.repaint();
//	}
//	
//	public void mouseDragged(MouseEvent e) {
//		if(this.selectedPiece != null) {
//			// cập nhật xPos, yPos của selectedPiece
//			this.selectedPiece.xPos = e.getX() - this.tileSize/2; // the piece is centered on the mouse
//			this.selectedPiece.yPos = e.getY() - this.tileSize/2;
//			
//			this.repaint();
//		}
//	}
//	
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		int col = e.getX()/this.tileSize;
//		int row = e.getY()/this.tileSize;
//		
//		if(this.selectedPiece != null) {
//			Move move = new Move(this, this.selectedPiece, col, row);
//			
//			if(this.isValidMove(move)) {
//				// thay vi makeMove, gui move cho server
//				try {
//					DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
//					dos.writeUTF(this.selectedPiece.col + "");
//					dos.writeUTF(this.selectedPiece.row + "");
//					dos.writeUTF(col + "");
//					dos.writeUTF(row + "");
//				} catch (Exception e1) {
//					
//				}
////				this.makeMove(move);
//				
//				//
//			} else {
//				// nếu nước đi không hợp lệ, reset xPos, yPos lại như ban đầu
//				this.selectedPiece.xPos = this.selectedPiece.col * this.tileSize;
//				this.selectedPiece.yPos = this.selectedPiece.row * this.tileSize;
//			}
//		}
//		// nếu nước đi không hợp lệ, reset xPos, yPos lại như ban đầu
//		this.selectedPiece.xPos = this.selectedPiece.col * this.tileSize;
//		this.selectedPiece.yPos = this.selectedPiece.row * this.tileSize;
//		this.selectedPiece = null;
//		this.repaint();
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
////