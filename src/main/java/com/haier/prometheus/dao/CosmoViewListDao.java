package com.haier.prometheus.dao;

import com.haier.prometheus.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CosmoViewListDao{

	Integer insert(CosmoViewList entity);
	
	Integer update(CosmoViewList entity);
	
	Integer delete(Integer id);
	
	CosmoViewList getById(Integer id);

	int count();
	
	List<CosmoViewList> findPage(Map<String, Object> param);

	List<CosmoViewList> selectAll();

	List<CosmoViewList> getByIds(@Param(value = "viewIdList") List<Integer> viewIdList);
}
