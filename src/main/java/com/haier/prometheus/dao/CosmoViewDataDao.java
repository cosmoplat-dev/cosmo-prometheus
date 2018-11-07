package com.haier.prometheus.dao;

import com.haier.prometheus.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CosmoViewDataDao{

	Integer insert(CosmoViewData entity);
	
	Integer update(CosmoViewData entity);
	
	Integer delete(Integer id);
	
	CosmoViewData getById(Integer id);

	int count();
	
	List<CosmoViewData> findPage(Map<String, Object> param);

	List<CosmoViewData> findByViewId(@Param("viewId") Integer viewId);

}
