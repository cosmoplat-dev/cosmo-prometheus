package com.haier.prometheus.service.impl;

import com.haier.prometheus.dao.CosmoViewListDao;
import com.haier.prometheus.entity.CosmoViewList;
import com.haier.prometheus.service.CosmoViewListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cosmoViewListService")
@Transactional
public class CosmoViewListServiceImpl implements CosmoViewListService {

    @Autowired
    CosmoViewListDao cosmoViewListDao;

    @Override
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        List<CosmoViewList> list = cosmoViewListDao.selectAll();
        for (CosmoViewList entity : list) {
            //判断是否属于一级指标
            if (entity.getRemark() != null && "1".equals(entity.getRemark())) {
                //判断map中是否包含该平台结构
                if (entity.getPlatformId() != null && map.containsKey(entity.getPlatformId())) {
                    //如果包含，获取平台结构platformIdMap
                    Map<String, Object> platformIdMap = (Map<String, Object>) map.get(entity.getPlatformId());
                    //判断平台结构中是否包含该一级指标
                    if (platformIdMap != null && !platformIdMap.containsKey(entity.getIndicator1())) {
                        //不包含该一级指标, 将该一级指标按照结构标准放入到map中
                        map = setIndicator1Value(map, platformIdMap, entity);
                    }
                } else {
                    //map中不包含该平台结构
                    Map<String, Object> platformIdMap = new HashMap<>();
                    //创建platformIdMap，并创建对应的一级指标及值按照结构标准放入到map中
                    map = setIndicator1Value(map, platformIdMap, entity);
                }
            }
            //判断是否属于二级指标
            if (entity.getRemark() != null && "2".equals(entity.getRemark())) {
                //判断platformId是否存在
                if (entity.getPlatformId() != null && map.containsKey(entity.getPlatformId())) {
                    Map<String, Object> platformIdMap = (Map<String, Object>) map.get(entity.getPlatformId());
                    //判断indicator1是否存在
                    if (platformIdMap != null && platformIdMap.containsKey(entity.getIndicator1())) {
                        //获取indicator1Map
                        Map<String, Object> indicator1Map = (Map<String, Object>) platformIdMap.get(entity.getIndicator1());
                        //判断indicator2是否存在，不存在添加进去
                        if (indicator1Map != null && !indicator1Map.containsKey(entity.getIndicator2())) {
                            map = setIndicator2Value(map, platformIdMap, indicator1Map, entity);
                        }
                    } else {
                        //indicator1不存在，创建一级指标indicator1Map
                        Map<String, Object> indicator1Map = new HashMap<>();
                        //按照结构标准放入到map中
                        map = setIndicator2Value(map, platformIdMap, indicator1Map, entity);
                    }
                } else {
                    //map中不包含该平台结构，创建platformIdMap，
                    Map<String, Object> platformIdMap = new HashMap<>();
                    //并创建对应的一级指标indicator1Map
                    Map<String, Object> indicator1Map = new HashMap<>();
                    //再创建对应的二级指标，按照结构标准放入到map中
                    map = setIndicator2Value(map, platformIdMap, indicator1Map, entity);
                }
            }
            //判断是否属于三级指标
            if (entity.getRemark() != null && "3".equals(entity.getRemark())) {
                if (entity.getPlatformId() != null && map.containsKey(entity.getPlatformId())) {
                    Map<String, Object> platformIdMap = (Map<String, Object>) map.get(entity.getPlatformId());
                    if (platformIdMap != null && platformIdMap.containsKey(entity.getIndicator1())) {
                        Map<String, Object> indicator1Map = (Map<String, Object>) platformIdMap.get(entity.getIndicator1());
                        if (indicator1Map != null && indicator1Map.containsKey(entity.getIndicator2())) {
                            if (entity.getId() == 99 || entity.getId() == 98) {
                                System.out.println(123);
                            }
                            Map<String, Object> indicator2Map = (Map<String, Object>) indicator1Map.get(entity.getIndicator2());
                            if (indicator2Map != null && !indicator2Map.containsKey(entity.getIndicator3())) {
                                map = setIndicator3Value(map, platformIdMap, indicator1Map, indicator2Map, entity);
                            }
                        } else {
                            Map<String, Object> indicator2Map = new HashMap<>();
                            map = setIndicator3Value(map, platformIdMap, indicator1Map, indicator2Map, entity);
                        }
                    } else {
                        Map<String, Object> indicator1Map = new HashMap<>();
                        Map<String, Object> indicator2Map = new HashMap<>();
                        map = setIndicator3Value(map, platformIdMap, indicator1Map, indicator2Map, entity);
                    }
                } else {
                    Map<String, Object> platformIdMap = new HashMap<>();
                    Map<String, Object> indicator1Map = new HashMap<>();
                    Map<String, Object> indicator2Map = new HashMap<>();
                    map = setIndicator3Value(map, platformIdMap, indicator1Map, indicator2Map, entity);
                }

            }
            //判断是否为类型4，如果为类型4，将二级指标作为key放入到一级指标的map中，并设置一个空的list（后面用来存放类型为5的三级指标对象）为其值。
            if (entity.getRemark() != null && "4".equals(entity.getRemark())) {
                if (entity.getPlatformId() != null && map.containsKey(entity.getPlatformId())) {
                    Map<String, Object> platformIdMap = (Map<String, Object>) map.get(entity.getPlatformId());
                    if (platformIdMap != null && platformIdMap.containsKey(entity.getIndicator1())) {
                        Map<String, Object> indicator1Map = (Map<String, Object>) platformIdMap.get(entity.getIndicator1());
                        if (indicator1Map != null && !indicator1Map.containsKey(entity.getIndicator2())) {
                            indicator1Map.put(entity.getIndicator2(), new ArrayList<Map<String, Object>>());
                            platformIdMap.put(entity.getIndicator1(), indicator1Map);
                            map.put(entity.getPlatformId(), platformIdMap);
                        }
                    } else {
                        Map<String, Object> indicator1Map = new HashMap<>();
                        indicator1Map.put(entity.getIndicator2(), new ArrayList<Map<String, Object>>());
                        platformIdMap.put(entity.getIndicator1(), indicator1Map);
                        map.put(entity.getPlatformId(), platformIdMap);
                    }
                } else {
                    Map<String, Object> platformIdMap = new HashMap<>();
                    Map<String, Object> indicator1Map = new HashMap<>();
                    indicator1Map.put(entity.getIndicator2(), new ArrayList<Map<String, Object>>());
                    platformIdMap.put(entity.getIndicator1(), indicator1Map);
                    map.put(entity.getPlatformId(), platformIdMap);
                }
            }
            //判断是否为类型5，如果为5，将三级指标放入到二级指标的list中
            if (entity.getRemark() != null && "5".equals(entity.getRemark())) {
                if (entity.getPlatformId() != null && map.containsKey(entity.getPlatformId())) {
                    Map<String, Object> platformIdMap = (Map<String, Object>) map.get(entity.getPlatformId());
                    if (platformIdMap != null && platformIdMap.containsKey(entity.getIndicator1())) {
                        Map<String, Object> indicator1Map = (Map<String, Object>) platformIdMap.get(entity.getIndicator1());
                        if (indicator1Map != null && indicator1Map.containsKey(entity.getIndicator2())) {
                            List<Map<String, Object>> indicator3List = (List<Map<String, Object>>) indicator1Map.get(entity.getIndicator2());
                            map = setIndicator5Value(map, platformIdMap, indicator1Map, indicator3List, entity);
                        } else {
                            List<Map<String, Object>> indicator3List = new ArrayList<Map<String, Object>>();
                            map = setIndicator5Value(map, platformIdMap, indicator1Map, indicator3List, entity);
                        }
                    } else {
                        Map<String, Object> indicator1Map = new HashMap<>();
                        List<Map<String, Object>> indicator3List = new ArrayList<Map<String, Object>>();
                        map.put(entity.getPlatformId(), platformIdMap);
                        map = setIndicator5Value(map, platformIdMap, indicator1Map, indicator3List, entity);
                    }
                } else {
                    Map<String, Object> platformIdMap = new HashMap<>();
                    Map<String, Object> indicator1Map = new HashMap<>();
                    List<Map<String, Object>> indicator3List = new ArrayList<Map<String, Object>>();
                    map = setIndicator5Value(map, platformIdMap, indicator1Map, indicator3List, entity);
                }
            }
        }
        return map;
    }

    @Override
    public CosmoViewList getById(Integer id) {
        return cosmoViewListDao.getById(id);
    }


    @Override
    public void update(CosmoViewList view) {
        cosmoViewListDao.update(view);
    }

    @Override
    public List<CosmoViewList> getByIds(List<Integer> viewIdList) {
        return cosmoViewListDao.getByIds(viewIdList);
    }

    /**
     * 保存一级指标的值
     *
     * @param map           总结构map
     * @param platformIdMap 一级指标所属平台结构
     * @param entity        CosmoViewList
     * @return
     */
    private Map setIndicator1Value(Map<String, Object> map, Map<String, Object> platformIdMap, CosmoViewList entity) {
        String indicator1 = entity.getIndicator1();
        //创建indicator1Value，用来存放一级指标的各项值
        Map<String, Object> indicator1Value = new HashMap<>();
        indicator1Value.put("key", entity.getIndicator1());
        indicator1Value.put("value", entity.getDataCurrentTotal());
        indicator1Value.put("unit", entity.getDataUnit());
        indicator1Value.put("曲线图数据", getMouthData(entity));
        //将一级指标放入到该平台结构platformIdMap中
        platformIdMap.put(indicator1, indicator1Value);
        //将平台结构放入到map中。
        map.put(entity.getPlatformId(), platformIdMap);
        return map;
    }

    /**
     * 保存二级指标的值到map中
     *
     * @param map           总结构map
     * @param platformIdMap 平台结构map
     * @param indicator1Map 一级指标map
     * @param entity
     * @return
     */
    private Map setIndicator2Value(Map<String, Object> map
            , Map<String, Object> platformIdMap
            , Map<String, Object> indicator1Map
            , CosmoViewList entity) {
        //indicator2Value，用来存放二级指标的各项值
        Map<String, Object> indicator2Value = setIndicatorValue(entity.getIndicator2(), entity);
        indicator1Map.put(entity.getIndicator2(), indicator2Value);
        platformIdMap.put(entity.getIndicator1(), indicator1Map);
        map.put(entity.getPlatformId(), platformIdMap);
        return map;
    }

    /**
     * 保存三级指标的值到map中
     *
     * @param map           总结构map
     * @param platformIdMap 平台结构map
     * @param indicator1Map 一级指标map
     * @param indicator2Map 二级指标map
     * @param entity
     * @return
     */
    private Map setIndicator3Value(Map<String, Object> map
            , Map<String, Object> platformIdMap
            , Map<String, Object> indicator1Map
            , Map<String, Object> indicator2Map
            , CosmoViewList entity) {
        Map<String, Object> indicator3Value = setIndicatorValue(entity.getIndicator3(), entity);
        indicator2Map.put(entity.getIndicator3(), indicator3Value);
        indicator1Map.put(entity.getIndicator2(), indicator2Map);
        platformIdMap.put(entity.getIndicator1(), indicator1Map);
        map.put(entity.getPlatformId(), platformIdMap);
        return map;
    }


    /**
     * 保存类型5的值到一个map中
     *
     * @param map            总结构map
     * @param platformIdMap  平台结构map
     * @param indicator1Map  一级指标map
     * @param indicator3List 三级列表
     * @param entity         CosmoViewList
     * @return
     */
    private Map setIndicator5Value(Map<String, Object> map
            , Map<String, Object> platformIdMap
            , Map<String, Object> indicator1Map
            , List<Map<String, Object>> indicator3List
            , CosmoViewList entity) {
        Map<String, Object> indicator3Value = new HashMap<>();
        indicator3Value = setIndicatorValue(entity.getIndicator3(), entity);
        indicator3List.add(indicator3Value);
        indicator1Map.put(entity.getIndicator2(), indicator3List);
        platformIdMap.put(entity.getIndicator1(), indicator1Map);
        map.put(entity.getPlatformId(), platformIdMap);
        return map;
    }

    /**
     * 将指标展现值放入到一个map中
     *
     * @param key    指标的key
     * @param entity CosmoViewList
     * @return
     */
    private Map<String, Object> setIndicatorValue(String key, CosmoViewList entity) {
        Map<String, Object> indicatorValue = new HashMap<>();
        indicatorValue.put("key", key);
//        indicatorValue.put("value", (double) Math.round(entity.getDataCurrentTotal() * 10000) / 10000);
        indicatorValue.put("value", new BigDecimal(entity.getDataCurrentTotal()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
        indicatorValue.put("unit", entity.getDataUnit());
        indicatorValue.put("曲线图数据", getMouthData(entity));
        return indicatorValue;
    }

    /**
     * 将12个月的数据放入到列表中.
     *
     * @param entity
     * @return
     */
    private List<Integer> getMouthData(CosmoViewList entity) {
        List<Integer> list = new ArrayList<>();
        list.add(entity.getDataMonth1());
        list.add(entity.getDataMonth2());
        list.add(entity.getDataMonth3());
        list.add(entity.getDataMonth4());
        list.add(entity.getDataMonth5());
        list.add(entity.getDataMonth6());
        list.add(entity.getDataMonth7());
        list.add(entity.getDataMonth8());
        list.add(entity.getDataMonth9());
        list.add(entity.getDataMonth10());
        list.add(entity.getDataMonth11());
        list.add(entity.getDataMonth12());
        return list;
    }
}
