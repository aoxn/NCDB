package com.space.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.space.util.LogUtil;
import com.space.util.TSConfig;

public abstract class VBaseSVC {
	protected String city="";
	protected String exPath="";
	public int handle(ResultSet set){
		int currRec = 0;
		int cnt=0;
		String tmp = "";
		BufferedWriter w = null;
		try {
			w = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(getFilePath())));
			long currStart=System.currentTimeMillis();
			w.write(getHeader());
			while (set.next()) {
				currRec++;
				tmp = getRecord(set);
//				System.out.println("A:"+tmp);
				w.write(tmp);
				if (currRec % TSConfig.BA_SIZE == 0) {
					LogUtil.d(getTag()+"已写入" + TSConfig.BA_SIZE + "行,用时("+(System.currentTimeMillis()-currStart)/1000+"s)");
					currStart=System.currentTimeMillis();
				}
			}
			w.flush();
			LogUtil.d(getTag()+"已写入" + currRec + " 行");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return cnt;
	}
	
	public abstract String getFilePath();
	
	public abstract String getHeader();
	
	public abstract String getRecord(ResultSet set) throws SQLException;
	
	public abstract String getSql();
	
	public abstract String getTag();
}
