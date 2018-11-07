package com.haier.prometheus.service;


import com.haier.prometheus.entity.CosmoViewData;

import java.util.List;

public interface CosmoViewDataService {

    List<CosmoViewData> findByViewId(Integer viewId);

}
