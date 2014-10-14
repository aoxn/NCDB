package com.space.service;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.space.util.DBTools;

public class CustSVC extends VBaseSVC{
	
	public CustSVC(String city,String _exPath) {
		super();
		this.city = city;
		this.exPath=_exPath;
	}
	
	@Override
	public String getFilePath(){
//		Calendar d = Calendar.getInstance();
//		String year = d.get(Calendar.YEAR)+"";
//		String month = d.get(Calendar.MONTH)+"";
//		return DBTools.getConfiguration().getProperty("export.storedpath")
//				+File.separator+exPath+File.separator+city+"_Customer.csv";
		return DBTools.getConfiguration().getProperty("export.storedpath")
				+File.separator+city+"_Customer.csv";
	}
	@Override
	public String getSql() {
		String pk_corp="YSW".equals(city)?"and (man.pk_corp=1004 or man.pk_corp=1005)":
			"ZGW".equals(city)?"and (man.pk_corp=1002)":"";
		return "select distinct bas.custcode CUSTOMER_CODE,bas.custshortname CUSTOMER_NAME," +
				"bas.def16 DEF16,ddoc16.doccode MARKET_SEGMENT,bas.def17 DEF17," +
				"ddoc17.doccode DELIVERY_COUNTRY_CODE,'' LOGISTIC_DEPARTMENT," +
				"case man.pk_corp when '1002' then 'Z' when '1004' then 'Y' when '1005' then 'Y' " +
				"else 'U' end SUBSIDIARY_CODE ," +
				"'' SUBSIDIARY_BUYER_CODE ,bas.engname LATIN_NAME " +
				"from bd_cubasdoc bas " +
				"left join bd_cumandoc man on bas.pk_cubasdoc=man.pk_cubasdoc " +
				"left join bd_defdoc ddoc16 on bas.def16=ddoc16.pk_defdoc " +
				"left join bd_defdoc ddoc17 on bas.def17=ddoc17.pk_defdoc "+
				" where 1=1 "+pk_corp;
	}
	@Override
	public String getTag() {
		return "¿Í»§";
	}
	@Override
	public String getRecord(ResultSet set) throws SQLException {
		String tmp="";
		String obj="";
		obj = set.getString("CUSTOMER_CODE");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("CUSTOMER_NAME");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("MARKET_SEGMENT");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("DELIVERY_COUNTRY_CODE");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("LOGISTIC_DEPARTMENT");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("SUBSIDIARY_CODE");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("SUBSIDIARY_BUYER_CODE");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("LATIN_NAME");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?"":obj+"";
		return tmp+"\r\n";
	}
	@Override
	public String getHeader() {
		return "CUSTOMER_CODE;CUSTOMER_NAME;MARKET_SEGMENT;DELIVERY_COUNTRY_CODE;" +
				"LOGISTIC_DEPARTMENT;SUBSIDIARY_CODE;SUBSIDIARY_BUYER_CODE;LATIN_NAME\r\n";
	}
}
