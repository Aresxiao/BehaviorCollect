package com.example.behaviorcollect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TimeService implements Runnable{
	String ip_addr = "192.168.43.199";
	int port = 20000;
	ServerSocket serverSocket = null;
	Socket socket = null;
	ArrayList<Long> timeList = new ArrayList<Long>();
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;
	public TimeService(){
		
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			System.out.println("now,connected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pollingTime() throws IOException{
		long startTime = System.currentTimeMillis();
		dataOutputStream.writeUTF("TIME\n");
		
		String recv = dataInputStream.readUTF();
		long endTime = System.currentTimeMillis();
		long t = endTime - startTime;
		
		timeList.add(t);
	}
	
	public double getAvgTime(){
		double size = timeList.size();
		double sum = 0;
		for(int i = 0;i < size;i++)
			sum = sum + timeList.get(i);
		
		return sum / size;
	}
}
