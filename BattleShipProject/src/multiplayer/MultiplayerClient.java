package multiplayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MultiplayerClient extends Thread {
	private int i;

	public String getMove() {
		return getCoordinate();
	}

	private String getCoordinate() {
		try {
			DatagramSocket datagramSocket = new DatagramSocket(1600);
			byte[] buffer = new byte[3];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			datagramSocket.receive(packet);
			datagramSocket.close();

			String temp = new String(packet.getData());

			if (Character.isDigit(temp.charAt(2)))
				return temp;
			return temp.substring(0, 2);

		} catch (IOException e) {
		}
		return "";
	}

	public int getStarter() {
		try {

			DatagramSocket datagramSocket = new DatagramSocket(1600);
			byte[] buffer = new byte[1];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			datagramSocket.receive(packet);
			System.out.println("test");
			datagramSocket.close();
			interrupt();

			return Integer.parseInt(new String(packet.getData()));

		} catch (IOException e) {
		}
		return 0;
	}

	public int getint() {
		System.out.println(i);
		return i;
	}

	@Override
	public void run() {
		getStarter();
	}
}