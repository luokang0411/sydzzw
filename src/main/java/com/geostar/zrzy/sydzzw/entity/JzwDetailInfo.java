package com.geostar.zrzy.sydzzw.entity;

import lombok.Data;

/**
 * 楼面信息子表-建筑物详细信息
 */
@Data
public class JzwDetailInfo {
    //主键id
    private String kid;

    //父id
    private String pid;

    //建筑栋号
    private String jzdh;

    //建筑用途
    private String jzyt;

    //起始层
    private int qsc;

    //终止层
    private int zzc;

    //计容面积
    private double jrmj;

    //总面积
    private double zmj;

}
