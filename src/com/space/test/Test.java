package com.space.test;


public class Test {

	
	public static void main(String[] args) {
		String xx="a/b";
		String[] p=xx.split("/");
		System.out.println(p[0]+" ff"+p[1]);
		
//		Connection conn = null;
//		Statement state = null;
//		long start=System.currentTimeMillis();
//		ResultSet set = null;
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@whxltec.oicp.net:1521:UFIDA", "zynf",
//					"zynf");
//			// conn = DriverManager.getConnection(urlIN,usernameIN,passwordIN);
//			String sql = "SELECT WMSYS.WM_CONCAT(FV.CHECKTYPE), WMSYS.WM_CONCAT(FV.VALUECODE),WMSYS.WM_CONCAT(FV.VALUENAME),"
//					+ "VD.YEARV,FV.CHECKTYPE,FV.VALUECODE,FV.VALUENAME,SU.SUBJCODE,SU.SUBJNAME,VD.PK_DETAIL,VD.DR,VD.PK_VOUCHER,VD.PK_CURRTYPE,VD.PK_CORP,"
//					+ "VD.DEBITAMOUNT,VD.CREDITAMOUNT,VD.LOCALDEBITAMOUNT,VD.LOCALCREDITAMOUNT,VD.TS,'' ENTERED_BALANCE,'' ACCOUNTED_BALANCE "
//					+ "FROM GL_DETAIL VD "
//					+ "LEFT JOIN BD_ACCSUBJ SU ON VD.PK_ACCSUBJ=SU.PK_ACCSUBJ "
//					+ "LEFT JOIN GL_FREEVALUE FV ON VD.ASSID=FV.FREEVALUEID "
//					+ "GROUP BY VD.YEARV,FV.CHECKTYPE,FV.VALUECODE,FV.VALUENAME,SU.SUBJCODE,SU.SUBJNAME,VD.PK_DETAIL,VD.DR,VD.PK_VOUCHER,VD.PK_CURRTYPE,VD.PK_CORP,"
//					+ "VD.DEBITAMOUNT,VD.CREDITAMOUNT,VD.LOCALDEBITAMOUNT,VD.LOCALCREDITAMOUNT,VD.TS ";
//			state = conn.createStatement();
//			System.out.println("正在执行查询语句.....");
//			set = state.executeQuery(sql);
//			System.out.println("查询语句执行完,HandleSet.....");
//			VDetailSVC2 vdsvc=new VDetailSVC2();
//			vdsvc.handle(set);
//			System.out.println("["+new Date()+"]数据处理完成....");
////			int k = 0;
////			while (set.next()) {
////				System.out.println("当前处理记录：" + k++);
////			}
//			System.out.println("共用时："+(System.currentTimeMillis()-start)); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (set != null)
//					set.close();
//				if (state != null)
//					state.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}

}
