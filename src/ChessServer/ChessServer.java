package ChessServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import GUI_Server.serverManagerForm;
import pieceServer.Bishop;
import pieceServer.King;
import pieceServer.Knight;
import pieceServer.Pawn;
import pieceServer.Piece;
import pieceServer.Queen;
import pieceServer.Rook;

class MoveHistory {
//	String name;
	int col;
	int row;
	int newCol;
	int newRow;
//	public MoveHistory(String name, int col, int row, int newCol, int newRow) {
	public MoveHistory(int col, int row, int newCol, int newRow) {			
//		this.name = name;
		this.col = col;
		this.row = row;
		this.newCol = newCol;
		this.newRow = newRow;
	}
}

//
class Match {
	ClientProcessing player1, player2;
	List<ClientProcessing> playerVector = new ArrayList<ClientProcessing>();
	List<MoveHistory> moveHistory = new ArrayList<MoveHistory>();
	ArrayList<Piece> pieceList = new ArrayList<>();
	
	public Match(ClientProcessing p1, ClientProcessing p2) {
		this.player1 = p1;
		this.player2 = p2;
	}
	
	public void addPieces() {
//		Black
		pieceList.add(new Rook(0, 0, false));
		pieceList.add(new Knight(1, 0, false));
		pieceList.add(new Bishop(2, 0, false));
		pieceList.add(new Queen(3, 0, false));
		pieceList.add(new King(4, 0, false));
		pieceList.add(new Bishop(5, 0, false));
		pieceList.add(new Knight(6, 0, false));
		pieceList.add(new Rook(7, 0, false));
		
		pieceList.add(new Pawn(0, 1, false));
		pieceList.add(new Pawn(1, 1, false));
		pieceList.add(new Pawn(2, 1, false));
		pieceList.add(new Pawn(3, 1, false));
		pieceList.add(new Pawn(4, 1, false));
		pieceList.add(new Pawn(5, 1, false));
		pieceList.add(new Pawn(6, 1, false));
		pieceList.add(new Pawn(7, 1, false));
		
//		White
		pieceList.add(new Rook(0, 7, true));
		pieceList.add(new Knight(1, 7, true));
		pieceList.add(new Bishop(2, 7, true));
		pieceList.add(new Queen(3, 7, true));
		pieceList.add(new King(4, 7, true));
		pieceList.add(new Bishop(5, 7, true));
		pieceList.add(new Knight(6, 7, true));
		pieceList.add(new Rook(7, 7, true));
		
		pieceList.add(new Pawn(0, 6, true));
		pieceList.add(new Pawn(1, 6, true));
		pieceList.add(new Pawn(2, 6, true));
		pieceList.add(new Pawn(3, 6, true));
		pieceList.add(new Pawn(4, 6, true));
		pieceList.add(new Pawn(5, 6, true));
		pieceList.add(new Pawn(6, 6, true));
		pieceList.add(new Pawn(7, 6, true));

	}

	public Piece getPiece(int col, int row) {
		for (Piece piece: pieceList) {
			if(piece.col == col && piece.row == row) {
				return piece;
			}
		}
		return null;
	}
	//
}

public class ChessServer {
//	ArrayList<Piece> pieceList = new ArrayList<>();
//	List<MoveHistory> moveHistory = new ArrayList<MoveHistory>();
	Vector<ClientProcessing> playerList = new Vector<ClientProcessing>();
	List<Match> matchList = new ArrayList<Match>();
	
//	public String listMoves = ""; // for chess engine
	ServerSocket server;
	serverManagerForm serverManagerForm;
	
//	public static void main(String[] args) throws Exception{
//		new ChessServer();
//	}
//	public ChessServer(serverManagerForm serverManagerForm) {
//		this.serverManagerForm = serverManagerForm;
//	}
	public ChessServer() {
		
	}
	public void startServer() {
//		addPieces();
		try {
			server = new ServerSocket(4444);
			System.out.println("server run 4444");
			while(true) {
				try {
					Socket soc = server.accept();
					System.out.println("IP client: " + soc.getInetAddress().getHostAddress());
					ClientProcessing t = new ClientProcessing(soc, this);
					playerList.add(t);
					t.start();
//					GUI_Server.serverManagerForm.updateServer1(playerList.size()+"", getClientsAddress());
					GUI_Server.serverManagerForm.updateServer1(getClientsAddress());
					
					if (playerList.size() % 2 == 0) { 
						Match match = new Match(playerList.get(playerList.size()-2), playerList.get(playerList.size()-1));
						match.playerVector.add(playerList.get(playerList.size()-2));
						match.playerVector.add(playerList.get(playerList.size()-1));
						matchList.add(match);
						setColorPlayer(match);
						match.addPieces();
						
//						setColorPlayer();
//						break;
					} 
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void stopServer() { // stop accept connections
		try {
			server.close();
			System.out.println("Server 1 stopped accepting connections");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<String> getClientsAddress() {
		ArrayList<String> addressesContainer = new ArrayList<String>();
		for (ClientProcessing a: playerList) {
			addressesContainer.add(a.soc.getInetAddress().getHostAddress());
		}
		return addressesContainer;
	}
	
	public Boolean removeClient(String ipToRemove) {
		for (ClientProcessing client : playerList) {
			System.out.println("InetAddress: " + client.soc.getInetAddress().getHostAddress());
			if(client.soc.getInetAddress().getHostAddress().equals(ipToRemove)) {
				System.out.println("line 105");
				try {
					client.soc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				playerList.remove(client);
				GUI_Server.serverManagerForm.updateServer1(getClientsAddress());
				return true;
			}
		}
		return false;
	}
	
	public void closeAllConnections() { // close all connections
		try {
			for (int i = 0; i < playerList.size(); i++) {
				playerList.get(i).soc.close();
				playerList.get(i).interrupt();
			}
			System.out.println("Server 1 closed all connections");
			GUI_Server.serverManagerForm.updateServer1(getClientsAddress());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public void setColorPlayer(Match match) {
		try {
//			DataOutputStream dos = new DataOutputStream(this.playerList.get(0).soc.getOutputStream());
			DataOutputStream dos = new DataOutputStream(match.player1.soc.getOutputStream());
			dos.writeUTF("You are white player");
//			DataOutputStream dos2 = new DataOutputStream(this.playerList.get(1).soc.getOutputStream());
			DataOutputStream dos2 = new DataOutputStream(match.player2.soc.getOutputStream());
			dos2.writeUTF("You are black player");
		} catch (Exception e) {
			
		}
	}

	private void removeAllClient() {
		for (int i = 0; i < playerList.size(); i++) {
			try {
				DataOutputStream dos2 = new DataOutputStream(
						playerList.get(i).soc.getOutputStream());
				dos2.writeUTF("-9999");
				dos2.writeUTF("-9999");
				System.out.println(i + ":" + playerList.get(i).soc.isClosed());
			} catch (Exception e2) {
				
			}
		}
	}
	
	public void SendError2All(Match match) {
		for (int j = 0; j < match.playerVector.size(); j++) {
			try {
				DataOutputStream dos = new DataOutputStream(playerList.get(j).soc.getOutputStream());
				dos.writeUTF("-9999");
				dos.writeUTF("-9999");
				dos.writeUTF("-9999");
				dos.writeUTF("-9999");
//				System.out.println(j + ":" + server.playerList.get(j).soc.isClosed());
			} catch (Exception e2) {
				System.out.println("Loi 234");
			}
		}
	}
	// standardize functions	
	
}

class ClientProcessing extends Thread {
	Socket soc;
	ChessServer server;
	
	public ClientProcessing(Socket soc, ChessServer server) {
		this.soc = soc;
		this.server = server;
	}
	
	public boolean isContainedInMatch(Socket soc) {
		for (int i = 0; i < server.matchList.size(); i++) {
			for (int j = 0; j < server.matchList.get(i).playerVector.size(); j++) {
				if (server.matchList.get(i).playerVector.get(j).soc == soc) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Match getMatch(Socket soc) {
		for (int i = 0; i < server.matchList.size(); i++) {
			for (int j = 0; j < server.matchList.get(i).playerVector.size(); j++) {
				if (server.matchList.get(i).playerVector.get(j).soc == soc) {
					return server.matchList.get(i);
				}
			}
		}
		return null;
	}
	
	public void run() {
		// gửi các điểm đã đánh cho client
//		try {
//			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
//			for (int i = 0; i < server.dadanh.size(); i++) {
//				dos.writeUTF(server.dadanh.get(i).x + "");
//				dos.writeUTF(server.dadanh.get(i).y + "");
//			}
//		} catch (Exception e) {
//			
//		}
		// chờ nhận tín hiệu -> xử lý -> hồi đáp cho client
		while(true) {
			try {
				DataInputStream dis = new DataInputStream(soc.getInputStream()); // loi khi ngat ket noi
				int col = Integer.parseInt(dis.readUTF());
				int row = Integer.parseInt(dis.readUTF());
				int newCol = Integer.parseInt(dis.readUTF());
				int newRow = Integer.parseInt(dis.readUTF());
				System.out.println(col + "," + row + "," + newCol + "," + newRow);
				
				
				if (!isContainedInMatch(soc)) {
					continue;
				} else {
					Match match = getMatch(soc);
					// Kiểm tra đủ 2 người hay chưa
					if (match.playerVector.size() < 2) {
						continue;
					}
					// Xu ly yeu cau xin hoa tu client gui len
					if (col == -8888) {
						if (this == match.playerVector.get(0)) {
							DataOutputStream dos = new DataOutputStream(match.playerVector.get(1).soc.getOutputStream());
							dos.writeUTF("-8888");
							dos.writeUTF("-8888");
							dos.writeUTF("-8888");
							dos.writeUTF("-8888");
							continue;
						} else {
							DataOutputStream dos = new DataOutputStream(match.playerVector.get(0).soc.getOutputStream());
							dos.writeUTF("-8888");
							dos.writeUTF("-8888");
							dos.writeUTF("-8888");
							dos.writeUTF("-8888");
							continue;
						}
						
					}
	
					// Hien thong bao cho client ben kia rang doi thu da dong y hoa
					if (col == -8885) {
						if (this == match.playerVector.get(0)) {
							DataOutputStream dos = new DataOutputStream(match.playerVector.get(1).soc.getOutputStream());
							dos.writeUTF("-8885");
							dos.writeUTF("-8885");
							dos.writeUTF("-8885");
							dos.writeUTF("-8885");
							continue;
						} else {
							DataOutputStream dos = new DataOutputStream(match.playerVector.get(0).soc.getOutputStream());
							dos.writeUTF("-8885");
							dos.writeUTF("-8885");
							dos.writeUTF("-8885");
							dos.writeUTF("-8885");
							continue;
						}
						
					}
					//
					Piece tempPiece = match.getPiece(col, row);
					if (tempPiece == null) {
						System.out.println("tempPiece null");
						continue;
					} else {
						System.out.println("tempPiece(" + tempPiece.col + "," +tempPiece.row + ")");
					}
					// Kiểm tra lượt đánh của từng người có hợp lệ hay không
					boolean ok = true;
					
					if ((this == match.playerVector.get(0) && match.moveHistory.size() % 2 == 0 && tempPiece.isWhite)
							|| (this == match.playerVector.get(1) && match.moveHistory.size() % 2 != 0 && !tempPiece.isWhite))
					{
						ok = true;
					}
					else {
						ok = false;
					}
					
					if (ok) {
						match.moveHistory.add(new MoveHistory(col, row, newCol, newRow));
//						server.listMoves += " " + server.convertStandardMove(col, row, newCol, newRow);
						if(match.getPiece(newCol, newRow) != null) {
							match.getPiece(newCol, newRow).isWhite = tempPiece.isWhite;
//							tempPiece.isWhite = server.getPiece(newCol, newRow).isWhite;
						}
						tempPiece.col = newCol;
						tempPiece.row = newRow;
						// castling
						if (tempPiece.name.equals("King") && Math.abs(newCol - col) == 2) {
							Piece rook;

							if (col < newCol) {
								rook = match.getPiece(7, row);
								rook.col = 5;
							} else {
								rook = match.getPiece(0, row);
								rook.col = 3;
							}
						}
						//
						for (int i = 0; i < match.playerVector.size(); i++) {
							try {
								DataOutputStream dos = new DataOutputStream(
										match.playerVector.get(i).soc.getOutputStream());
								dos.writeUTF(col + "");
								dos.writeUTF(row + "");
								dos.writeUTF(newCol + "");
								dos.writeUTF(newRow + "");
								System.out.println("Da gui: " + col + "," + row + "," + newCol + "," + newRow);
							} catch (Exception e) {
//								// nếu ko gửi đc tín hiệu tới client (client ngắt kết nối) thì sẽ xuất hiện lỗi ở đây
//								System.out.println("Loi1");
////								server.playerList.remove(this);
//								SendError2All();
							}
						}
					}
				}		
			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				System.out.println("1 Client da ngat ket noi");
//				if(this == server.playerList.get(0) || this == server.playerList.get(1)) {
//					match.SendError2All();
//				}
//				server.playerList.remove(this);
//				serverManagerForm.updateServer1(server.getClientsAddress());
//				break;
				//
				System.out.println(e.getMessage());
				System.out.println("1 client da ngat ket noi server 4444");
				if (this == getMatch(soc).playerVector.get(0) || this == getMatch(soc).playerVector.get(1)) {
					server.SendError2All(getMatch(soc));
				}
				server.playerList.remove(this);
				serverManagerForm.updateServer1(server.getClientsAddress());
				break;
			}
		}
	}
}









