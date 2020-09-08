package com.geostar.zrzy.sydzzw.entity;

import lombok.Data;

/**
 * @Description  
 * @Author  luokang
 * @Date 2020-08-31 13:59:17 
 */

@Data
public class SyspGhspJzxk {

	/**
	 * 主键
	 */
	private String kid;

	/**
	 * 项目ID
	 */
	private String xmid;

	/**
	 * 建设单位名称
	 */
	private String jsdwmc;

	/**
	 * 项目名称
	 */
	private String xmmc;

	/**
	 * 项目地址
	 */
	private String xmdz;

	/**
	 * 工程性质（新建/改建/扩建/已建成（补办规划手续）
	 */
	private String gcxz;

	/**
	 * 建筑性质
	 */
	private String jzxz;

	/**
	 * 规划概要
	 */
	private String ghgy;

	/**
	 * 公共配建
	 */
	private String ggpj;

	/**
	 * 立体地上个数(停车位)
	 */
	private String ltdsgs;

	/**
	 * 立体地下个数(停车位)
	 */
	private String ltdxgs;

	/**
	 * 总计个数((停车位地上+停车位地下)
	 */
	private String zjgs;

	/**
	 * 建筑工程配建停车库个数
	 */
	private String jzgcpjtckgs;

	/**
	 * 停车配建备注
	 */
	private String tcpjbz;

	/**
	 * 附记
	 */
	private String fj;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 平面地上个数(停车位)
	 */
	private String pmdsgs;

	/**
	 * 平面地下个数(停车位)
	 */
	private String pmdxgs;

	/**
	 * 地上停车位合计(地上立体个数+地上平面个数)
	 */
	private String dshj;

	/**
	 * 地下停车位合计(地下立体个数+地下平面个数)
	 */
	private String dxhj;

	/**
	 * 证书编号
	 */
	private String zsbh;

	private String jsydxkzh;

	private String xzyjsbh;

	/**
	 * 前期审批事项证书编号
	 */
	private String qqspsxzsbh;

	/**
	 * 地上面积合计
	 */
	private String dsmjsum;

	/**
	 * 地下面积合计
	 */
	private String dxmjsum;

	/**
	 * 计容面积合计
	 */
	private String jrmjsum;

	/**
	 * 总面积合计
	 */
	private String zmjsum;

	/**
	 * 是否外网公示
	 */
	private String sfwwgs;

	/**
	 * 第几次变更
	 */
	private String djcbg;

	private String sysxmid;

	/**
	 * 续办功能前期事项证书编号
	 */
	private String xbqqspsxzsbh;

	/**
	 * 卷号
	 */
	private String jh;

	/**
	 * 审定意见
	 */
	private String sdyj;

	/**
	 * 楼面信息编码
	 */
	private String lmbm;

	/**
	 * 条件地块编号
	 */
	private String tjdkbh;

	/**
	 * 指标地块编号
	 */
	private String zbdkbh;

	/**
	 * 条件用地性质
	 */
	private String tjydxz;

	/**
	 * 指标用地性质
	 */
	private String zbydxz;

	/**
	 * 条件用地面积描述
	 */
	private String tjydmjms;

	/**
	 * 条件用地面积
	 */
	private String tjydmj;

	/**
	 * 条件用地面积备注
	 */
	private String tjydmjbz;

	/**
	 * 指标用地面积
	 */
	private String zbydmj;

	/**
	 * 指标用地面积默认
	 */
	private String zbydmjbz;

	/**
	 * 条件本次容积率符号
	 */
	private String tjbcrjlfh;

	/**
	 * 条件本次容积率
	 */
	private String tjbcrjl;

	/**
	 * 条件本次容积率备注
	 */
	private String tjbcrjlbz;

	/**
	 * 指标本次容积率符号
	 */
	private String zbbcrjlfh;

	/**
	 * 指标本次容积率
	 */
	private String zbbcrjl;

	/**
	 * 指标本次容积率默认
	 */
	private String zbbcrjlbz;

	/**
	 * 条件综合容积率符号
	 */
	private String tjzhrjlfh;

	/**
	 * 条件综合容积率
	 */
	private String tjzhrjl;

	/**
	 * 条件综合容积率备注
	 */
	private String tjzhrjlbz;

	/**
	 * 指标综合容积率符号
	 */
	private String zbzhrjlfh;

	/**
	 * 指标综合容积率
	 */
	private String zbzhrjl;

	/**
	 * 指标综合容积率默认
	 */
	private String zbzhrjlbz;

	/**
	 * 条件绿地率符号
	 */
	private String tjldlfh;

	/**
	 * 条件绿地率
	 */
	private String tjldl;

	/**
	 * 条件绿地率备注
	 */
	private String tjldlbz;

	/**
	 * 指标绿地率符号
	 */
	private String zbldlfh;

	/**
	 * 指标绿地率
	 */
	private String zbldl;

	/**
	 * 指标绿地率默认
	 */
	private String zbldlbz;

	/**
	 * 条件商业比符号
	 */
	private String tjsybfh;

	/**
	 * 条件商业比
	 */
	private String tjsyb;

	/**
	 * 条件商业比备注
	 */
	private String tjsybbz;

	/**
	 * 指标商业比符号
	 */
	private String zbsybfh;

	/**
	 * 指标商业比
	 */
	private String zbsyb;

	/**
	 * 指标商业比默认
	 */
	private String zbsybbz;

	/**
	 * 条件综合商业比符号
	 */
	private String tjzhsybfh;

	/**
	 * 条件综合商业比
	 */
	private String tjzhsyb;

	/**
	 * 条件综合商业比备注
	 */
	private String tjzhsybbz;

	/**
	 * 指标综合商业比符号
	 */
	private String zbzhsybfh;

	/**
	 * 指标综合商业比
	 */
	private String zbzhsyb;

	/**
	 * 指标综合商业比默认
	 */
	private String zbzhsybbz;

	/**
	 * 条件建筑密度符号
	 */
	private String tjjzmdfh;

	/**
	 * 条件建筑密度
	 */
	private String tjjzmd;

	/**
	 * 条件建筑密度备注
	 */
	private String tjjzmdbz;

	/**
	 * 指标建筑密度符号
	 */
	private String zbjzmdfh;

	/**
	 * 指标建筑密度
	 */
	private String zbjzmd;

	/**
	 * 指标建筑密度默认
	 */
	private String zbjzmdbz;

	/**
	 * 条件综合建筑密度符号
	 */
	private String tjzhjzmdfh;

	/**
	 * 条件综合建筑密度
	 */
	private String tjzhjzmd;

	/**
	 * 条件综合建筑密度备注
	 */
	private String tjzhjzmdbz;

	/**
	 * 指标综合建筑密度符号
	 */
	private String zbzhjzmdfh;

	/**
	 * 指标综合建筑密度
	 */
	private String zbzhjzmd;

	/**
	 * 指标综合建筑密度默认
	 */
	private String zbzhjzmdbz;

	/**
	 * 条件建筑高度
	 */
	private String tjjzgd;

	/**
	 * 条件建筑高度备注
	 */
	private String tjjzgdbz;

	/**
	 * 指标建筑高度
	 */
	private String zbjzgd;

	/**
	 * 指标建筑高度默认
	 */
	private String zbjzgdbz;

	/**
	 * 规划户数
	 */
	private String ghhs;

	/**
	 * 规划条件编号
	 */
	private String ghtjbh;

}
