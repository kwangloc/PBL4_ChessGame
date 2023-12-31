package Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class TestServer {
	Vector<ClientProcessing> playerList = new Vector<ClientProcessing>();
	
	public TestServer() {
		try {
			ServerSocket server = new ServerSocket(6789);
			Socket soc = server.accept();
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			while(true) {
				try {
//					System.out.println("begin while");
					
					String request = dis.readUTF();
					System.out.println("request:" + request);
					switch (request) {
						case "plusRequest": {
							int num1 = dis.readInt();
							int num2 = dis.readInt();
							int num3 = dis.readInt();
							int res = num1 + num2 + num3;
							dos.writeUTF(res + "");
							break;
						}
						case "subRequest": {
							int num1 = dis.readInt();
							int num2 = dis.readInt();
							int res = num1 - num2;
							dos.writeUTF(res + "");
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + request);
					}
					
				} catch (Exception e1) {
//					System.out.println(e1.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		new TestServer();
	}
}

class ClientProcessing extends Thread {
	Socket soc;
	TestServer server;
	
	public ClientProcessing(Socket soc, TestServer server) {
		this.soc = soc;
		this.server = server;
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
				boolean ok = true;
				if (ok) {
					try {
						DataOutputStream dos = new DataOutputStream(server.playerList.get(0).soc.getOutputStream());
						dos.writeUTF(col + "");
						dos.writeUTF(row + "");
						dos.writeUTF(newCol + "");
						dos.writeUTF(newRow + "");
						System.out.println("Da gui: " + col + "," + row + "," + newCol + "," + newRow);
					} catch (Exception e) {

					}
					
				}
			} catch (Exception e) {

			}
		}
	}
	
}