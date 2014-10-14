package com.space.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.space.service.VBaseSVC;
import com.space.util.DBTools;
import com.space.util.LogUtil;

public class ExportMain{

	public void doProcess(VBaseSVC svc){
		Connection conn = null;
		Statement state = null;
		ResultSet set = null;
		try {
			long start=System.currentTimeMillis();
			conn = DBTools.getConnection();
			state = conn.createStatement();
			set	= state.executeQuery(svc.getSql());
			svc.handle(set);
			LogUtil.d("【"+svc.getTag()+"】导出用时："+(System.currentTimeMillis()-start)); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBTools.closeRs(set);
				DBTools.closeState(state);
				DBTools.closeConn(conn);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
