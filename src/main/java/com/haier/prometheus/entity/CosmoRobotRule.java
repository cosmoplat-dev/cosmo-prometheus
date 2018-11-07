package com.haier.prometheus.entity;

import lombok.Data;

@Data
public class CosmoRobotRule implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "CosmoRobotRule";
	public static final String ALIAS_ID = "自增id";
	public static final String ALIAS_VIEW_ID = "数据视图id";
	public static final String ALIAS_UPDATE_RATE = "周期更新的频率";
	public static final String ALIAS_MAX = "数据最高值";
	public static final String ALIAS_MIN = "数据最高值";
		
	//columns START
	private Integer id;
	private Integer viewId;
	private Integer updateRate;
	private Integer max;
	private Integer min;
	private String type;
	private Integer value;
	//columns END

	public CosmoRobotRule(){
	}

}

