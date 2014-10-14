package com.space.core;

import com.space.service.CustSVC;
import com.space.service.InvoiceSVC;
import com.space.util.DBTools;

public class VMain {

	
	public static void main(String[] args) {
		ExportMain exp = new ExportMain();
		String type = DBTools.getConfiguration().getProperty("export.invoice.retrieve.type");
		if("lastmonth".equals(type.toLowerCase())||"full".equals(type.toLowerCase())){
			exp.doProcess(new InvoiceSVC("ZGW","lastmonth"));	//郑州  上个月
			exp.doProcess(new InvoiceSVC("YSW","lastmonth"));	//贵阳上个月
			exp.doProcess(new CustSVC("ZGW","lastmonth"));
			exp.doProcess(new CustSVC("YSW","lastmonth"));
		}
		if("currentmonth".equals(type.toLowerCase())||"full".equals(type.toLowerCase())){
			exp.doProcess(new InvoiceSVC("ZGW","currentmonth"));	//郑州  本月1号到20号
			exp.doProcess(new InvoiceSVC("YSW","currentmonth"));	//贵阳 本月1号到20号
			exp.doProcess(new CustSVC("ZGW","currentmonth"));
			exp.doProcess(new CustSVC("YSW","currentmonth"));
		}
	}

	
	
}
