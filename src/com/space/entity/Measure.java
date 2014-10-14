package com.space.entity;

public class Measure {
	/**
	 * (select mi.measurename,mi.reportpk,mi.dbtable,mi.dbcolumn,mi.keycombpk,r.id,r.name,r.time,
	 * 		r.reportcode,r.pk_key_comb,r.ver,r.report_type,r.modify_time 
	 * from iufo_measure_info mi left join iufo_report r on mi.reportpk=r.id 
	 * where r.reportcode='TL-B151' and r.name='资产负债表(2011年用)');
	 */
	 private String measureName;	//指标名称
	 private String reportPK;		//报表主键
	 private String dbTable;
	 private String dbColumn;
	 private String keyCombPK;
	 private String id;
	 private String name;
	 private String time;
	 private String reportCode;
	 private String pk_key_comb;
	 private String ver;
	 private String reportType;
	 private String modifyTime;
	 
	public String getMeasureName() {
		return measureName;
	}
	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}
	public String getReportPK() {
		return reportPK;
	}
	public void setReportPK(String reportPK) {
		this.reportPK = reportPK;
	}
	public String getDbTable() {
		return dbTable;
	}
	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}
	public String getDbColumn() {
		return dbColumn;
	}
	public void setDbColumn(String dbColumn) {
		this.dbColumn = dbColumn;
	}
	public String getKeyCombPK() {
		return keyCombPK;
	}
	public void setKeyCombPK(String keyCombPK) {
		this.keyCombPK = keyCombPK;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getPk_key_comb() {
		return pk_key_comb;
	}
	public void setPk_key_comb(String pkKeyComb) {
		pk_key_comb = pkKeyComb;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	 
	 
}
