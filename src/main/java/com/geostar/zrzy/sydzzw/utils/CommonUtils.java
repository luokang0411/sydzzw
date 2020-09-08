package com.geostar.zrzy.sydzzw.utils;

import java.util.UUID;

public class CommonUtils {

    /**
     * 获取32位随机编码
     * @return
     */
    public static String  getUUID(){
        String uuid =  UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

}
