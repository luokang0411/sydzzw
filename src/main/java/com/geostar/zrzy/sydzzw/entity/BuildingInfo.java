package com.geostar.zrzy.sydzzw.entity;

import lombok.Data;

import java.util.List;

/**
 * 楼面信息
 */
@Data
public class BuildingInfo {
  //主键id
  private String kid;

  //建设单位
  private String jsdw;

  //项目名称
  private String xmmc;

  //项目地址
  private String xmdz;

  //地块编号
  private String dkbh;

  //创建者
  private String cjz;

  //是否提交
  private String sftj;

  //关联建筑物信息
  private List<JzwInfo> jzwInfos;

  //关联建筑物详细信息
  private List<JzwDetailInfo> jzwDetailInfos;

}
