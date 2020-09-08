package com.geostar.zrzy.sydzzw.controller;

import com.geostar.zrzy.sydzzw.entity.BuildingInfo;
import com.geostar.zrzy.sydzzw.entity.TreeInfo;
import com.geostar.zrzy.sydzzw.service.BuildingService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/building/data")
public class BuildingDataController {

    @Resource
    private BuildingService buildingService;

    /**
     * 保存楼面信息
     * @param buildingInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveBuildingInfos",method = RequestMethod.POST)
    @Transactional
    public Map<String,Object> saveBuildingInfos(@RequestBody BuildingInfo buildingInfo,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("username");
        buildingInfo.setCjz(username);
        return buildingService.saveBuildingInfos(buildingInfo);
    }

    /**
     * 根据编码获取楼面信息
     * @param buildingCode
     * @return
     */
    @RequestMapping(value = "/getBuildingInfos",method = RequestMethod.GET)
    public BuildingInfo getBuildingInfos(String buildingCode){
        return buildingService.getBuildingInfos(buildingCode);
    }

    /**
     * 获取当前用户名下的楼面信息列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getBuildingInfoList",method = RequestMethod.POST)
    public Map<String,Object> getBuildingInfoList(HttpServletRequest request){
        return buildingService.getBuildingInfoList(request);
    }

    /**
     * 获取建筑用途字典数据
     * @return
     */
    @RequestMapping(value = "/getJzytDatas",method = RequestMethod.POST)
    public List<TreeInfo> getJzytDatas(){
        return buildingService.getJzytDatas();
    }

    @RequestMapping(value = "/exportBuildingInfos",method = RequestMethod.GET)
    public void exportBuildingInfos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        buildingService.exportBuildingInfos(request,response);
    }

}
