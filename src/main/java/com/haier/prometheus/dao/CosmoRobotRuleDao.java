package com.haier.prometheus.dao;

import com.haier.prometheus.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CosmoRobotRuleDao{

	Integer insert(CosmoRobotRule entity);
	
	Integer update(CosmoRobotRule entity);
	
	Integer delete(Integer id);
	
	CosmoRobotRule getById(Integer id);

	int count();
	
	List<CosmoRobotRule> findPage(Map<String, Object> param);

	List<CosmoRobotRule> findAll();

}
