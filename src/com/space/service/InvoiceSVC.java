package com.space.service;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.space.util.DBTools;

public class InvoiceSVC extends VBaseSVC{
	
	public InvoiceSVC(String _city,String _exportType){
		this.city=_city;
		this.exPath=_exportType;
	}
	@Override
	public String getFilePath(){
//		return DBTools.getConfiguration().getProperty("export.storedpath")
//				+File.separator+exPath+File.separator+city+"_Sales.csv";
		return DBTools.getConfiguration().getProperty("export.storedpath")
				+File.separator+city+"_Sales.csv";
	}
	@Override
	public String getSql() {
//		上个月第一天
//		DATEADD(MONTH,-1,DATEADD(MONTH,DATEDIFF(MONTH,0,GETDATE()),0))	
//		上个月最后一天
//		DATEADD(ms,-3,DATEADD(MONTH,DATEDIFF(MONTH,0,GETDATE()),0))	
//		本月第一天
//		DATEADD(ms,3,DATEADD(MONTH,DATEDIFF(MONTH,0,GETDATE()),0))	
//		本月第20天
//		DATEADD(DAY ,20,DATEADD(MONTH,DATEDIFF(MONTH,0,GETDATE()),0))	

		String start="lastmonth".equals(exPath.toLowerCase())?
				"DATEADD(MONTH,-1,DATEADD(MONTH,DATEDIFF(MONTH,0,GETDATE()),0))":
					"DATEADD(ms,3,DATEADD(MONTH,DATEDIFF(MONTH,0,GETDATE()),0))";
		String end ="lastmonth".equals(exPath.toLowerCase())?
				"DATEADD(ms,-3,DATEADD(MONTH,DATEDIFF(MONTH,0,GETDATE()),0))":
					"DATEADD(DAY ,20,DATEADD(MONTH,DATEDIFF(MONTH,0,GETDATE()),0))";
		String pk_corp="YSW".equals(city)?"and (si.pk_corp=1004 or si.pk_corp=1005)":
			"ZGW".equals(city)?"and (si.pk_corp=1002)":"";
		return "select si.dmakedate YEAR,si.dmakedate MONTH,si.creceiptcorpid INVOICED_CUSTOMER_CODE," +
				"si.pk_corp PRODUCTION_SITE,si.pk_corp DISPATCH_SITE,si.pk_corp CLINKER_SITE,si.vdef20 vdef20," +
				"ddoc20.doccode INCOTERM,sib.cinvbasdocid PRODUCT_PK,ibas.def2 DEF2," +
				"ddoc2.doccode PRODUCT_CODE,ibas.def1 DEF1,ddoc1.doccode PACKING_CODE," +
				"sib.nnumber QUANTITY,sib.noriginalcursummny TURNOVER_TRA," +
				"sib.ccurrencytypeid CURRENCY_PK,ctype.currtypecode CURRENCY_TRA,sib.nsummny TURNOVER_SUB," +
				"'CNY' CURRENCY_SUB	" +
				"from so_saleinvoice si " +
				"left join so_saleinvoice_b sib on si.csaleid=sib.csaleid " +
				"left join bd_invbasdoc ibas on sib.cinvbasdocid=ibas.pk_invbasdoc	" +
				"left join bd_defdoc ddoc2 on ibas.def2=ddoc2.pk_defdoc " +
				"left join bd_defdoc ddoc1 on ibas.def1=ddoc1.pk_defdoc " +
				"left join bd_defdoc ddoc20 on si.vdef20=ddoc20.pk_defdoc " +
				"left join bd_currtype ctype on sib.ccurrencytypeid=ctype.pk_currtype " +
				"where si.dr=0 and si.dmakedate between "+start+" and "+end+" "+pk_corp;
	}
	@Override
	public String getTag() {
		return "发票";
	}
	@Override
	public String getRecord(ResultSet set) throws SQLException {
		String tmp="";
		String obj="";
		obj = set.getString("YEAR")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("MONTH")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("INVOICED_CUSTOMER_CODE")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("PRODUCTION_SITE")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("DISPATCH_SITE")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("CLINKER_SITE")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("INCOTERM")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("PRODUCT_CODE")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("PACKING_CODE")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("QUANTITY")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("TURNOVER_TRA")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("CURRENCY_TRA")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("TURNOVER_SUB")+";";
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?";":obj+";";
		obj = set.getString("CURRENCY_SUB");
		tmp+= (obj == null||obj.toLowerCase().equals("null"))?"":obj+"";
		return tmp+"\r\n";
	}
	@Override
	public String getHeader() {
		return "YEAR;MONTH;INVOICED_CUSTOMER_CODE;PRODUCTION_SITE;DISPATCH_SITE;" +
				"CLINKER_SITE;INCOTERM;PRODUCT_CODE;PACKING_CODE;QUANTITY;TURNOVER_TRA;" +
				"CURRENCY_TRA;TURNOVER_SUB;CURRENCY_SUB\r\n";
	}
	/**
	 * 得到上个月的第一天的日期字符串
	 * @return
	 */
	public String getLastMonthFirstDay(){
		Calendar d = Calendar.getInstance();
		d.add(Calendar.MONTH, -1);
		return d.get(Calendar.YEAR)+"-"+d.get(Calendar.MONTH)+"-"+"01 00:00:00";
	}
	/**
	 * 得到本月第一天的日期字符串
	 * @return
	 */
	public String getCurrentMonthFirstDay(){
		Calendar d = Calendar.getInstance();
		return d.get(Calendar.YEAR)+"-"+d.get(Calendar.MONTH)+"-"+"01 00:00:00";
	}
	/**
	 * 得到本月第21天的日期字符串
	 * @return
	 */
	public String getCurrentMonth21thDay(){
		Calendar d = Calendar.getInstance();
		return d.get(Calendar.YEAR)+"-"+d.get(Calendar.MONTH)+"-"+"21 00:00:00";
	}
}
