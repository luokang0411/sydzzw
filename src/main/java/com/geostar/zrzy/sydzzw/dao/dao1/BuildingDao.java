package com.geostar.zrzy.sydzzw.dao.dao1;

import com.geostar.zrzy.sydzzw.entity.BuildingInfo;
import com.geostar.zrzy.sydzzw.entity.JzwDetailInfo;
import com.geostar.zrzy.sydzzw.entity.JzwInfo;

import java.util.List;
import java.util.Map;

public interface BuildingDao {

    void insertBuildingInfo(BuildingInfo building);

    void deleteBuildingInfo(String kid);

    void insertJzwInfos(List<JzwInfo> jzwInfos);

    void deleteJzwInfos(String pid);

    void insertJzwDetailInfos(List<JzwDetailInfo> jzwDetailInfos);

    void deleteJzwDetailInfos(String pid);

    BuildingInfo getBuildingInfo(String kid);

    List<JzwInfo> getJzwInfos(String pid);

    List<JzwDetailInfo> getJzwDetailInfos(String pid);

    List<BuildingInfo> getBuildingInfoList(Map<String, Object> params);

    int getBuildingInfoCount(Map<String, Object> params);

    List<BuildingInfo> getBuildingInfos(String cjz);
}
