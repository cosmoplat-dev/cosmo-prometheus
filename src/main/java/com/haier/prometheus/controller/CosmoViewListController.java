package com.haier.prometheus.controller;

import com.haier.prometheus.annotation.HttpMethodMetric;
import com.haier.prometheus.entity.CosmoViewList;
import com.haier.prometheus.service.CosmoViewListService;
import com.haier.prometheus.utils.ResponseCode;
import com.haier.prometheus.utils.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value="CosmoViewList模块-CosmoViewListController")
@RestController
@RequestMapping("/cosmoViewList")
public class CosmoViewListController {


    @Autowired
    CosmoViewListService cosmoViewListService;

    @HttpMethodMetric(name = "selectAll", description = "selectAll",tags = {"cosom-prometheus","selectAll"})
    @ApiOperation(value = "获取CosmoViewList")
    @GetMapping("/selectAll")
    public Map selectAll(){
        return cosmoViewListService.selectAll();
    }

    @HttpMethodMetric(name = "updateCosmoViewList", description = "updateCosmoViewList",tags = {"cosom-prometheus","updateCosmoViewList"})
    @ApiOperation(value = "根据id更新CosmoViewList")
    @PostMapping("/updateCosmoViewList")
    public String updateCosmoViewList(@RequestBody CosmoViewList entity){
        ResponseModel result = new ResponseModel();
        try {
            if (entity.getId() == null){
                result = new ResponseModel(ResponseCode.FAILURE,"id不能为空");
            } else {
                cosmoViewListService.update(entity);
                result = new ResponseModel(ResponseCode.SUCCESS,"更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResponseModel(ResponseCode.EXCEPTION,"系统异常");
        }
        return result.toJsonString();
    }


}
