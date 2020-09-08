package com.geostar.zrzy.sydzzw.controller;

import com.geostar.zrzy.sydzzw.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/building")
public class BuildingController {

    /**
     * 楼面信息
     * @param request
     * @return
     */
    @RequestMapping("/buildingInfo")
    public String newBuildingInfo(HttpServletRequest request){
        String buildingCode = request.getParameter("buildingCode");
        if(StringUtils.isEmpty(buildingCode)){
            buildingCode = CommonUtils.getUUID();
            request.setAttribute("type","add");
        }
        request.setAttribute("buildingCode",buildingCode);
        return "building";
    }

    /**
     * 楼面信息列表
     * @return
     */
    @RequestMapping("/buildingInfoList")
    public String buildingInfoList(){
        return "buildingList";
    }


}
