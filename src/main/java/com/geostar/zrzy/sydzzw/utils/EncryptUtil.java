package com.geostar.zrzy.sydzzw.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.util.StringUtils;

public class EncryptUtil {

    /**
     * 加密
     * @param res 原文
     * @return
     */
    public static String Base64Encode(String res) {
        if(StringUtils.isEmpty(res)){
            return "";
        }
        return Base64.encode(res.getBytes());
    }

    /**
     * 解密
     * @param res 密文
     * @return
     */
    public static String Base64Decode(String res){
        if(StringUtils.isEmpty(res)){
            return "";
        }
        return new String(Base64.decode(res));
    }

}
