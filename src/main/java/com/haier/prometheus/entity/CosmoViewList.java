package com.haier.prometheus.entity;


public class CosmoViewList implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "CosmoViewList";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PLATFORM_ID = "平台解构";
	public static final String ALIAS_INDICATOR1 = "一级指标";
	public static final String ALIAS_INDICATOR2 = "二级指标";
	public static final String ALIAS_INDICATOR3 = "三级指标";
	public static final String ALIAS_DATA_CURRENT_TOTAL = "当前数据";
	public static final String ALIAS_DATA_UNIT = "数据单位";
	public static final String ALIAS_TARGET_VALUE = "18年验收指标";
	public static final String ALIAS_CREATE_DATETIME = "创建时间";
	public static final String ALIAS_REMARK = "信息等级备注";
	public static final String ALIAS_DEFINATION = "指标定义";
	public static final String ALIAS_CACULATE_METHOD = "计算逻辑";
	public static final String ALIAS_MODEL_PLATFORM = "示范平台(底数)";
	public static final String ALIAS_DOUBLE_PLATFORM = "双跨平台(底数)";
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
	public static final String ALIAS_UPDATE_DATETIME = "数据更新时间";
		
	//columns START
	private Integer id;
	private String platformId;
	private String indicator1;
	private String indicator2;
	private String indicator3;
	private double dataCurrentTotal;
	private String dataUnit;
	private String targetValue;
	private Integer createDatetime;
	private String remark;
	private String defination;
	private String caculateMethod;
	private String modelPlatform;
	private String doublePlatform;
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
	private Integer updateDatetime;
	//columns END

	public CosmoViewList(){
	}

	public CosmoViewList(
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
	public void setPlatformId(String value) {
		this.platformId = value;
	}
	
	public String getPlatformId() {
		return this.platformId;
	}
	public void setIndicator1(String value) {
		this.indicator1 = value;
	}
	
	public String getIndicator1() {
		return this.indicator1;
	}
	public void setIndicator2(String value) {
		this.indicator2 = value;
	}
	
	public String getIndicator2() {
		return this.indicator2;
	}
	public void setIndicator3(String value) {
		this.indicator3 = value;
	}
	
	public String getIndicator3() {
		return this.indicator3;
	}
	public void setDataCurrentTotal(double value) {
		this.dataCurrentTotal = value;
	}
	
	public double getDataCurrentTotal() {
		return this.dataCurrentTotal;
	}
	public void setDataUnit(String value) {
		this.dataUnit = value;
	}
	
	public String getDataUnit() {
		return this.dataUnit;
	}
	public void setTargetValue(String value) {
		this.targetValue = value;
	}
	
	public String getTargetValue() {
		return this.targetValue;
	}
	public void setCreateDatetime(Integer value) {
		this.createDatetime = value;
	}
	
	public Integer getCreateDatetime() {
		return this.createDatetime;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	public void setDefination(String value) {
		this.defination = value;
	}
	
	public String getDefination() {
		return this.defination;
	}
	public void setCaculateMethod(String value) {
		this.caculateMethod = value;
	}
	
	public String getCaculateMethod() {
		return this.caculateMethod;
	}
	public void setModelPlatform(String value) {
		this.modelPlatform = value;
	}
	
	public String getModelPlatform() {
		return this.modelPlatform;
	}
	public void setDoublePlatform(String value) {
		this.doublePlatform = value;
	}
	
	public String getDoublePlatform() {
		return this.doublePlatform;
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
	public void setUpdateDatetime(Integer value) {
		this.updateDatetime = value;
	}
	
	public Integer getUpdateDatetime() {
		return this.updateDatetime;
	}

}

