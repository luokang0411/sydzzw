package com.geostar.zrzy.sydzzw.entity;

import lombok.Data;

/**
 * 楼面信息子表-建筑物信息
 */
@Data
public class JzwInfo {

    //主键id
    private String kid;

    //父id
    private String pid;

    //地块编号
    private String dkbh;

    //建筑栋号
    private String jzdh;

    //建筑用途
    private String jzyt;

    //地上层数
    private int dscs;

    //地下层数
    private int dxcs;

    //地上高度
    private double dsgd;

    //地上面积
    private double dsmj;

    //地下面积
    private double dxmj;

    //计容面积
    private double jrmj;

    //备注
    private String bz;

    //备注
    private int xh;
}
