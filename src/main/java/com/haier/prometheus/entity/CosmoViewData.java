package com.haier.prometheus.entity;


public class CosmoViewData implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "CosmoViewData";
	public static final String ALIAS_ID = "数据自增id";
	public static final String ALIAS_VIEW_ID = "关联cosmo_view_data.id";
	public static final String ALIAS_DATA_MONTH1 = "月度数据1，曲线图使用";
	public static final String ALIAS_DATA_MONTH2 = "月度数据2";
	public static final String ALIAS_DATA_MONTH3 = "月度数据3";
	public static final String ALIAS_DATA_MONTH4 = "月度数据4";
	public static final String ALIAS_DATA_MONTH5 = "月度数据5";
	public static final String ALIAS_DATA_MONTH6 = "月度数据6";
	public static final String ALIAS_DATA_MONTH7 = "月度数据7";
	public static final String ALIAS_DATA_MONTH8 = "月度数据8";
	public static final String ALIAS_DATA_MONTH9 = "月度数据9";
	public static final String ALIAS_DATA_MONTH10 = "月度数据10";
	public static final String ALIAS_DATA_MONTH11 = "月度数据11";
	public static final String ALIAS_DATA_MONTH12 = "月度数据12";
	public static final String ALIAS_DATA_CURRENT_TOTAL = "当前数据";
	public static final String ALIAS_DATA_UNIT = "数据单位";
	public static final String ALIAS_UPDATE_DATETIME = "数据更新时间";
		
	//columns START
	private Integer id;
	private Integer viewId;
	private Integer dataMonth1;
	private Integer dataMonth2;
	private Integer dataMonth3;
	private Integer dataMonth4;
	private Integer dataMonth5;
	private Integer dataMonth6;
	private Integer dataMonth7;
	private Integer dataMonth8;
	private Integer dataMonth9;
	private Integer dataMonth10;
	private Integer dataMonth11;
	private Integer dataMonth12;
	private Integer dataCurrentTotal;
	private String dataUnit;
	private Integer updateDatetime;
	//columns END

	public CosmoViewData(){
	}

	public CosmoViewData(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setViewId(Integer value) {
		this.viewId = value;
	}
	
	public Integer getViewId() {
		return this.viewId;
	}
	public void setDataMonth1(Integer value) {
		this.dataMonth1 = value;
	}
	
	public Integer getDataMonth1() {
		return this.dataMonth1;
	}
	public void setDataMonth2(Integer value) {
		this.dataMonth2 = value;
	}
	
	public Integer getDataMonth2() {
		return this.dataMonth2;
	}
	public void setDataMonth3(Integer value) {
		this.dataMonth3 = value;
	}
	
	public Integer getDataMonth3() {
		return this.dataMonth3;
	}
	public void setDataMonth4(Integer value) {
		this.dataMonth4 = value;
	}
	
	public Integer getDataMonth4() {
		return this.dataMonth4;
	}
	public void setDataMonth5(Integer value) {
		this.dataMonth5 = value;
	}
	
	public Integer getDataMonth5() {
		return this.dataMonth5;
	}
	public void setDataMonth6(Integer value) {
		this.dataMonth6 = value;
	}
	
	public Integer getDataMonth6() {
		return this.dataMonth6;
	}
	public void setDataMonth7(Integer value) {
		this.dataMonth7 = value;
	}
	
	public Integer getDataMonth7() {
		return this.dataMonth7;
	}
	public void setDataMonth8(Integer value) {
		this.dataMonth8 = value;
	}
	
	public Integer getDataMonth8() {
		return this.dataMonth8;
	}
	public void setDataMonth9(Integer value) {
		this.dataMonth9 = value;
	}
	
	public Integer getDataMonth9() {
		return this.dataMonth9;
	}
	public void setDataMonth10(Integer value) {
		this.dataMonth10 = value;
	}
	
	public Integer getDataMonth10() {
		return this.dataMonth10;
	}
	public void setDataMonth11(Integer value) {
		this.dataMonth11 = value;
	}
	
	public Integer getDataMonth11() {
		return this.dataMonth11;
	}
	public void setDataMonth12(Integer value) {
		this.dataMonth12 = value;
	}
	
	public Integer getDataMonth12() {
		return this.dataMonth12;
	}
	public void setDataCurrentTotal(Integer value) {
		this.dataCurrentTotal = value;
	}
	
	public Integer getDataCurrentTotal() {
		return this.dataCurrentTotal;
	}
	public void setDataUnit(String value) {
		this.dataUnit = value;
	}
	
	public String getDataUnit() {
		return this.dataUnit;
	}
	public void setUpdateDatetime(Integer value) {
		this.updateDatetime = value;
	}
	
	public Integer getUpdateDatetime() {
		return this.updateDatetime;
	}

}

