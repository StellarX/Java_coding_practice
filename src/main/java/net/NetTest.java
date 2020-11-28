package net;

import java.net.InetAddress;
import java.net.UnknownHostException; 

public class NetTest {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress i = InetAddress.getLocalHost();//获取IP地址对象
		
		System.out.println(i);//SpaceX/169.254.178.155
		System.out.println("name:" + i.getHostName());//name:SpaceX
		System.out.println("address:" + i.getHostAddress());//address:169.254.178.155
		
		InetAddress[] ips = InetAddress.getAllByName("www.baidu.com");
		for(int j = 0;  j < ips.length; j++)
			System.out.println(ips[j]);
		//out:
		//www.baidu.com/14.215.177.38
		//www.baidu.com/14.215.177.39
			
		
		
	}

}
