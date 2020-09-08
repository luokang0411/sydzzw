package com.geostar.zrzy.sydzzw.dao.dao1;

import com.geostar.zrzy.sydzzw.entity.UserInfo;

import java.util.Map;

public interface UserDao {

    void insertUserInfo(UserInfo us);

    int isUserExists(String username);

    int passwordValidate(Map<String, Object> params);

    void updatePassword(Map<String, Object> params);

}
