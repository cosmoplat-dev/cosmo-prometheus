package com.haier.prometheus.service;

import com.haier.prometheus.entity.CosmoViewList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("cosmoViewListService")
public interface CosmoViewListService {

    Map<String, Object> selectAll();

    CosmoViewList getById(Integer id);

    List<CosmoViewList> getByIds(List<Integer> viewIdList);

    void update(CosmoViewList view);
}
