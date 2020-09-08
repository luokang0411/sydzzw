package com.geostar.zrzy.sydzzw.controller;

import com.geostar.zrzy.sydzzw.entity.UserInfo;
import com.geostar.zrzy.sydzzw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserDataController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertUserInfo",method = RequestMethod.POST)
    public Map<String,Object> insertUserInfo(@RequestBody UserInfo user){
        return userService.insertUserInfo(user);
    }

    /**
     * 判断账号是否存在
     * @param username  若不存在，则验证通过
     * @return
     */
    @RequestMapping(value = "/isUserExists",method = RequestMethod.POST)
    public Map<String,Object> isUserExists(String username){
        return userService.isUserExists(username);
    }

    /**
     * 判断账号是否存在
     * @param username  若存在，则验证通过
     * @return
     */
    @RequestMapping(value = "/isUserExists2",method = RequestMethod.POST)
    public Map<String,Object> isUserExists2(String username){
        return userService.isUserExists2(username);
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login(HttpServletRequest request){
        return userService.login(request);
    }

    /***
     * 修改用户密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Map<String,Object> update(HttpServletRequest request){
        return userService.update(request);
    }

}
