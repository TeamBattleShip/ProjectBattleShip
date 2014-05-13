package multiplayer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class MultiplayerServer extends Thread {

	private static String coordinate;

	public MultiplayerServer(String coordinate) {
		MultiplayerServer.coordinate = coordinate;
		new MultiplayerServer().start();
	}

	private MultiplayerServer() {

	}

	public void run() {
		sendMove("169.254.168.26");
	}

	public boolean isHit() {
		if (new MultiplayerClient().getMove().equals("hit"))
			return true;
		return false;
	}

	private void sendMove(String address) {
		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			byte[] buffer = coordinate.getBytes();
			InetAddress receiverAddress = InetAddress.getByName(address);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
					receiverAddress, 1600);
			datagramSocket.send(packet);
			datagramSocket.close();
		} catch (Exception e) {
		}

	}

	public boolean checkWhoStarts() {
		Random rand = new Random();
		MultiplayerClient mpc = new MultiplayerClient();
		mpc.start();
		int i = rand.nextInt(100);
		sendMove("" + i);
		while (mpc.isAlive())
			;
		if (i > mpc.getint())
			return true;
		return false;
	}
}
