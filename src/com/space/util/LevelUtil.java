package com.space.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class LevelUtil {
	HashMap relaMap;
	String sql="";
	public LevelUtil(String type) {
		if("公司".equals(type)){
			sql="SELECT CO.UNITNAME MNAME,CO.PK_CORP UCODE,CO.FATHERCORP FATHER,CO2.UNITNAME FANAME " +
					"FROM BD_CORP CO LEFT JOIN BD_CORP CO2 ON CO.FATHERCORP=CO2.PK_CORP";
		}else if("部门".equals(type)){
			sql="SELECT BD.PK_DEPTDOC UCODE,BD.DEPTNAME MNAME,BD.PK_FATHEDEPT FATHER,FA.DEPTNAME FANAME " +
					"FROM BD_DEPTDOC BD LEFT JOIN BD_DEPTDOC FA ON BD.PK_FATHEDEPT=FA.PK_DEPTDOC";
		}else if("科目".equals(type)){
			sql="SELECT P.SUBJCODE UCODE,P.SUBJNAME MNAME,P.SUBJLEV,P.FATHER,TAC.SUBJNAME FANAME FROM (SELECT AC.SUBJCODE ,AC.SUBJNAME ,AC.SUBJLEV," +
					"DECODE(SUBJLEV,'1','','2',SUBSTR(SUBJCODE,1,4),'3',SUBSTR(SUBJCODE,1,6),'4',SUBSTR(SUBJCODE,1,8),'5',SUBSTR(SUBJCODE,1,10)) FATHER " +
					"FROM BD_ACCSUBJ AC) P LEFT JOIN BD_ACCSUBJ TAC ON P.FATHER=TAC.SUBJCODE";
		}else if("客商".equals(type)){
			sql="SELECT CU.CUSTCODE UCODE,CU.CUSTNAME MNAME,'' FATHER,'' FANAME FROM BD_CUBASDOC CU WHERE CU.PK_CORP='0001'";
		}else if("项目".equals(type)){
			sql="SELECT JO.JOBCODE UCODE,JO.JOBNAME MNAME,'' FATHER,'' FANAME FROM BD_JOBBASFIL JO";
		}else if("明细".equals(type)){
			sql="SELECT CI.ACCIDCODE UCODE,CI.ACCIDNAME MNAME,'' FATHER,'' FANAME FROM BD_ACCID CI WHERE CI.PK_CORP!='0001' " +
					"UNION    " +
					"SELECT CL.PSNCLASSCODE UCODE,CL.PSNCLASSNAME MNAME,'' FATHER,'' FANAME FROM BD_PSNCL CL " +
					"UNION " +
					"SELECT BO.DOCCODE UCODE,BO.DOCNAME MNAME,'' FATHER,'' FANAME FROM BD_DEFDOC BO " +
					"WHERE BO.PK_DEFDOCLIST='0001V21000000000OUP3' OR BO.PK_DEFDOCLIST='0001V41000000000P85S'";
		}else {
			System.out.println("未知【层级】类型，");
			return;
		}
		relaMap=getCode();
	}
	private HashMap<String,String> getCode(){
		Connection conn = null;
		Statement state = null;
		ResultSet set = null;
		HashMap<String,String> map=new HashMap<String,String>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@whxltec.oicp.net:1521:UFIDA", "zynf","zynf");
			state = conn.createStatement();
			set = state.executeQuery(sql);
			while(set.next()){
				map.put(set.getString("UCODE")+"$"+set.getString("MNAME"), set.getString("FATHER")==null?null:set.getString("FATHER")+"$"+set.getString("FANAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (set != null)
					set.close();
				if (state != null)
					state.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	public void getSuper(ArrayList<String> list,String corp){
		list.add(corp);
		if(relaMap.get(corp)==null)return;
		getSuper(list,(String)relaMap.get(corp));
	}
	
}
