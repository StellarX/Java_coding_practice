package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UdpDemo{
	public static void main(String[] args) throws Exception {
		
		DatagramSocket receSocket = new DatagramSocket(10000);
		DatagramSocket sendSocket = new DatagramSocket();

		new Thread(new UdpSend(sendSocket)).start();
		new Thread(new UdpReceive(receSocket)).start();
	}
}

class UdpSend implements Runnable{
	private DatagramSocket ds;
	public UdpSend(DatagramSocket ds) {this.ds = ds;}
	public void run() {
		try {
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			while((line = bufr.readLine()) != null){
				if("886".equals(line)) break;
				byte[] buf = line.getBytes();
				DatagramPacket dp = 
						new DatagramPacket(buf, buf.length, InetAddress.getByName("169.254.178.155"), 10000);
				ds.send(dp);
			}		
		}catch(Exception e){
			throw new RuntimeException("����ʧ��");
		}
	}
}

class UdpReceive implements Runnable{
	private static String ip;
	private static String data;
	private static int port;
	private DatagramSocket ds;
	public UdpReceive(DatagramSocket ds) {
		this.ds = ds;
	}
	public void run() {
		try {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			
			ip = dp.getAddress().getHostAddress();//���ͷ�ip��ַ
			data = new String(dp.getData(), 0, dp.getLength());//��ȡdata������װ��String
			port = dp.getPort();//���ͷ��˿�
			System.out.println(ip + "::" + data + "::" + port);
		}catch(Exception e) {
			throw new RuntimeException("����ʧ��");
		}
	}
}

