package com.geostar.zrzy.sydzzw.service;

import com.geostar.zrzy.sydzzw.dao.dao1.BuildingDao;
import com.geostar.zrzy.sydzzw.dao.dao2.DictDao;
import com.geostar.zrzy.sydzzw.entity.BuildingInfo;
import com.geostar.zrzy.sydzzw.entity.JzwDetailInfo;
import com.geostar.zrzy.sydzzw.entity.JzwInfo;
import com.geostar.zrzy.sydzzw.entity.TreeInfo;
import com.geostar.zrzy.sydzzw.utils.CommonUtils;

import com.geostar.zrzy.sydzzw.utils.ExportExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService {

    @Resource
    private BuildingDao buildingDao;

    @Resource
    private DictDao dictDao;

    /**
     * 保存楼面信息
     *
     * @param buildingInfo
     * @return
     */
    public Map<String, Object> saveBuildingInfos(BuildingInfo buildingInfo) {
        String kid = buildingInfo.getKid();
        //删除旧的主表信息
        buildingDao.deleteBuildingInfo(kid);
        //保存主表信息
        buildingDao.insertBuildingInfo(buildingInfo);
        List<JzwInfo> jzwInfos = buildingInfo.getJzwInfos();
        List<JzwDetailInfo> jzwDetailInfos = buildingInfo.getJzwDetailInfos();
        //删除旧的2子表数据
        buildingDao.deleteJzwInfos(kid);
        buildingDao.deleteJzwDetailInfos(kid);
        //保存新的子表数据
        saveJzwInfos(jzwInfos);
        saveJzwDetailInfos(jzwDetailInfos);
        Map<String, Object> result = new HashMap<>();
        result.put("buildingCode", kid);
        result.put("status", "success");
        return result;
    }

    public List<TreeInfo> getJzytDatas() {
        List<TreeInfo> list = dictDao.getJzytDatas();
        list = list.stream().sorted(Comparator.comparing(TreeInfo::getSortno)).collect(Collectors.toList());
        return list;
    }

    /**
     * 保存建筑物信息
     *
     * @param jzwInfos
     */
    private void saveJzwInfos(List<JzwInfo> jzwInfos) {
        if (!CollectionUtils.isEmpty(jzwInfos)) {
            for (JzwInfo jzwInfo : jzwInfos) {
                jzwInfo.setKid(CommonUtils.getUUID());
            }
            buildingDao.insertJzwInfos(jzwInfos);
        }
    }

    /**
     * 保存建筑物详细信息
     *
     * @param jzwDetailInfos
     */
    private void saveJzwDetailInfos(List<JzwDetailInfo> jzwDetailInfos) {
        if (!CollectionUtils.isEmpty(jzwDetailInfos)) {
            for (JzwDetailInfo jzwDetailInfo : jzwDetailInfos) {
                jzwDetailInfo.setKid(CommonUtils.getUUID());
            }
            buildingDao.insertJzwDetailInfos(jzwDetailInfos);
        }
    }

    /**
     * 根据楼面编码获取楼面信息
     *
     * @param buildingCode
     * @return
     */
    public BuildingInfo getBuildingInfos(String buildingCode) {
        BuildingInfo buildingInfo = buildingDao.getBuildingInfo(buildingCode);
        if (buildingInfo == null) {
            BuildingInfo info = new BuildingInfo();
            info.setKid(buildingCode);
            return info;
        }
        List<JzwInfo> jzwInfos = buildingDao.getJzwInfos(buildingCode);
        List<JzwDetailInfo> jzwDetailInfos = buildingDao.getJzwDetailInfos(buildingCode);
        buildingInfo.setJzwInfos(jzwInfos);
        buildingInfo.setJzwDetailInfos(jzwDetailInfos);
        return buildingInfo;
    }

    /**
     * 根据登录用户获取名下的楼面信息列表
     *
     * @param request
     * @return
     */
    public Map<String, Object> getBuildingInfoList(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String username = (String) request.getSession().getAttribute("username");
        if (StringUtils.isEmpty(username)) {
            result.put("rows", new ArrayList<>());
            result.put("total", 0);
            return result;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("cjz", username);
        int total = buildingDao.getBuildingInfoCount(params);
        int begin = transStrToInt(request.getParameter("begin"));
        int length = transStrToInt(request.getParameter("length"));
        int end = begin + length;
        params.put("begin", begin);
        params.put("end", end);
        List<BuildingInfo> buildingInfos = buildingDao.getBuildingInfoList(params);
        result.put("rows", buildingInfos);
        result.put("total", total);
        return result;
    }

    /**
     * 将字符串转换为数字
     *
     * @param str
     * @return
     */
    private int transStrToInt(String str) {
        int num = 0;
        if (StringUtils.isEmpty(str)) {
            return num;
        }
        try {
            num = Integer.parseInt(str);
        } catch (Exception e) {
            num = 0;
        }
        return num;
    }

    public void exportBuildingInfos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = (String) request.getSession().getAttribute("username");
        List<BuildingInfo> buildingInfos = new ArrayList<>();
        if (StringUtils.isNotEmpty(username)) {
            buildingInfos = buildingDao.getBuildingInfos(username);
        }
        String[] headers = new String[]{"项目名称","建设单位","项目地址","地块编号","编码"};
        String[] fields = new String[]{"xmmc","jsdw","xmdz","dkbh","kid"};
        ExportExcelUtil.exportExcel(headers,fields,buildingInfos,response,"楼面信息列表");
    }
}
