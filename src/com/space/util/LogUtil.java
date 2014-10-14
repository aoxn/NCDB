package com.space.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
	private static String LOGFILE="debug.log";
	private static String ERRORFILE="error.log";
	private static String DATA="data.csv";
	
	public static void d(String msg){
		System.out.println("["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"]:"+ msg);
		
	}
	
	public static void e(String msg){
		System.out.println("["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"->ERROR]:"+ msg);
		
	}
	public static void w(String msg){
		System.out.println("["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"]->WARRNING]:"+ msg);
		
	}
	
	public static void re(String msg){
		System.out.println("["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"]"+ msg);
		FileWriter writer;
		try {
			writer = new FileWriter(DATA, true);
			writer.write("["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"]"+ msg + "\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
