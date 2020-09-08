package com.geostar.zrzy.sydzzw.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class UserInfo {

    //用户编码
    private String usercode;

    //用户名;
    @JSONField(name="user_name")
    private String username;

    //密码
    @JSONField(name="user_password")
    private String password;

    //用户类型
    private String cardType;

    //证件号
    private String cardId;

    //姓名
    private String name;

    //电话
    private String phone;
}
