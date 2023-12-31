package Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket soc;
		soc = new Socket("localhost", 6789);
		DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
		DataInputStream dis = new DataInputStream(soc.getInputStream());
		String res;
		
		dos.writeUTF("plusRequest");
		dos.writeInt(1);
		dos.writeInt(2);
		dos.writeInt(3);
		res = dis.readUTF();
		System.out.println("Get: " + res + "\n");
		
		dos.writeUTF("subRequest");
		dos.writeInt(9);
		dos.writeInt(2);
		res = dis.readUTF();
		System.out.println("Get: " + res + "\n");
		
		dos.writeUTF("plusRequest");
		dos.writeInt(1);
		dos.writeInt(2);
		dos.writeInt(3);
		res = dis.readUTF();
		System.out.println("Get: " + res + "\n");

	}
}
