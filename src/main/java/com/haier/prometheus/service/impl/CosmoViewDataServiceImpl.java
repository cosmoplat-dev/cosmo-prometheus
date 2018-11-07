package com.haier.prometheus.service.impl;

import com.haier.prometheus.dao.CosmoViewDataDao;
import com.haier.prometheus.entity.CosmoViewData;
import com.haier.prometheus.service.CosmoViewDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cosmoViewDataService")
public class CosmoViewDataServiceImpl implements CosmoViewDataService {

    @Autowired
    CosmoViewDataDao cosmoViewDataDao;

    @Override
    public List<CosmoViewData> findByViewId(Integer viewId) {
        return cosmoViewDataDao.findByViewId(viewId);
    }
}
