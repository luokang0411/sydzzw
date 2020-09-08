package com.geostar.zrzy.sydzzw;

import com.alibaba.fastjson.JSONObject;
import com.geostar.zrzy.sydzzw.entity.TreeInfo;
import com.geostar.zrzy.sydzzw.service.BuildingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SydzzwApplicationTests {

	@Resource
	private BuildingService buildingService;

	@Test
	public void getJzytDatas(){
		List<TreeInfo> list = buildingService.getJzytDatas();
		System.out.println(JSONObject.toJSONString(list));
	}

}
