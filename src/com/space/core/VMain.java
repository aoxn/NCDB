package com.space.core;

import com.space.service.CustSVC;
import com.space.service.InvoiceSVC;
import com.space.util.DBTools;

public class VMain {

	
	public static void main(String[] args) {
		ExportMain exp = new ExportMain();
		String type = DBTools.getConfiguration().getProperty("export.invoice.retrieve.type");
		if("lastmonth".equals(type.toLowerCase())||"full".equals(type.toLowerCase())){
			exp.doProcess(new InvoiceSVC("ZGW","lastmonth"));	//֣��  �ϸ���
			exp.doProcess(new InvoiceSVC("YSW","lastmonth"));	//�����ϸ���
			exp.doProcess(new CustSVC("ZGW","lastmonth"));
			exp.doProcess(new CustSVC("YSW","lastmonth"));
		}
		if("currentmonth".equals(type.toLowerCase())||"full".equals(type.toLowerCase())){
			exp.doProcess(new InvoiceSVC("ZGW","currentmonth"));	//֣��  ����1�ŵ�20��
			exp.doProcess(new InvoiceSVC("YSW","currentmonth"));	//���� ����1�ŵ�20��
			exp.doProcess(new CustSVC("ZGW","currentmonth"));
			exp.doProcess(new CustSVC("YSW","currentmonth"));
		}
	}

	
	
}
