package com.space.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 * 加载时间
 * @author 李佳
 *
 */
public class TSConfig {

	public static final String fileName1 = getClassRunURL() + "/ts.properties";
	
	public static String VOUHEAD_LASTUPDATE_TS;		//凭证头上次更新时间
	public static String VOUDETAIL_LASTUPDATE_TS;	//凭证主体上次更新时间
	public static String BALANCE_LASTUPDATE_TS;		//总帐或余额上次更新时间
	
	public static int SIZE=34600;
	public static int HEAD_SIZE=10000;
	public static int BA_SIZE=10000;
	public static int CODE_SIZE=1000;
	
	public static String getClassRunURL(){
		File directory = new File("."); 
		String path = "";
		try {
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	/**
	 * 查询最大时间
	 * @param tableName
	 * @return
	 */
	public static void initTs(){
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(fileName1));
			Properties pro = new Properties();     
			pro.load(in);
			VOUHEAD_LASTUPDATE_TS 	= 	pro.getProperty("VOUHEAD_LASTUPDATE_TS");
			VOUDETAIL_LASTUPDATE_TS	=	pro.getProperty("VOUDETAIL_LASTUPDATE_TS");
			BALANCE_LASTUPDATE_TS	=	pro.getProperty("BALANCE_LASTUPDATE_TS");
			System.out.println("凭证头          VOUHEAD_LASTUPDATE_TS:"+VOUHEAD_LASTUPDATE_TS);
			System.out.println("凭证明细VOUDETAIL_LASTUPDATE_TS:"+VOUDETAIL_LASTUPDATE_TS);
			System.out.println("总帐               BALANCE_LASTUPDATE_TS:"+BALANCE_LASTUPDATE_TS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置最大时间
	 * @param tableName
	 * @param ts
	 */
	public static void setMaxCreationDate(String tableName,String ts){
		InputStream in=null;
		try {
			in = new BufferedInputStream(new FileInputStream(fileName1));
			
			Properties pro = new Properties();     
			pro.load(in); 
			OutputStream fos = new FileOutputStream(fileName1);
			pro.setProperty(tableName, ts);
			pro.store(fos,tableName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
