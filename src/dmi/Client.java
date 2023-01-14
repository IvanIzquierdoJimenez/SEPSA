package dmi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import dmi.*;

public class Client {
	Socket s;
	DataOutputStream out;
	BufferedReader in;
	public Client()
	{
		while(s==null)
		{
			String ip = "localhost";
			try
			{
				DatagramSocket ds = new DatagramSocket(null);
				ds.setBroadcast(true);
				ds.bind(new InetSocketAddress("0.0.0.0", 5091));
				ds.setSoTimeout(1000);
				byte[] buff = new byte[50];
				DatagramPacket packet = new DatagramPacket(buff, buff.length);
				ds.receive(packet);
				ip = packet.getAddress().getCanonicalHostName();
				ds.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				s = new Socket(ip, 5090);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		while(!s.isConnected()) 
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void sendData(String s)
	{
		s = s+'\n';
		try {
			out.writeBytes(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String readData()
	{
		try {
			return in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
