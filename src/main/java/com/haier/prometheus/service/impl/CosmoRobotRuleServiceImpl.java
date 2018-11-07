package com.haier.prometheus.service.impl;

import com.haier.prometheus.dao.CosmoRobotRuleDao;
import com.haier.prometheus.entity.CosmoRobotRule;
import com.haier.prometheus.service.CosmoRobotRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CosmoRobotRuleService")
public class CosmoRobotRuleServiceImpl implements CosmoRobotRuleService {

    @Autowired
    private CosmoRobotRuleDao cosmoRobotRuleDao;

    @Override
    public List<CosmoRobotRule> findAll() {
        return cosmoRobotRuleDao.findAll();
    }
}
