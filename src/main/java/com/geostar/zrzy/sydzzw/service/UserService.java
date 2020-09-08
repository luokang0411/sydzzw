package com.geostar.zrzy.sydzzw.service;

import com.geostar.zrzy.sydzzw.dao.dao1.UserDao;
import com.geostar.zrzy.sydzzw.entity.UserInfo;
import com.geostar.zrzy.sydzzw.utils.CommonUtils;
import com.geostar.zrzy.sydzzw.utils.EncryptUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public Map<String,Object> insertUserInfo(UserInfo user){
        Map<String,Object> result = new HashMap<>();
        String userCode = CommonUtils.getUUID();
        user.setUsercode(userCode);
        //对用户密码加密
        String password = user.getPassword();
        password = EncryptUtil.Base64Encode(password);
        user.setPassword(password);
        userDao.insertUserInfo(user);
        result.put("status","success");
        result.put("msg","用户注册成功");
        return result;
    }

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    public Map<String,Object> isUserExists(String username){
        Map<String,Object> result = new HashMap<>();
        int num = userDao.isUserExists(username);
        if(num > 0){
            result.put("valid",false);
        }else{
            result.put("valid",true);
        }
        return result;
    }

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    public Map<String,Object> isUserExists2(String username){
        Map<String,Object> result = new HashMap<>();
        int num = userDao.isUserExists(username);
        if(num > 0){
            result.put("valid",true);
        }else{
            result.put("valid",false);
        }
        return result;
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    public Map<String,Object> login(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        String checkCode = request.getParameter("checkCode");
        String code = (String)request.getSession().getAttribute("code");
        if(!StringUtils.equalsIgnoreCase(checkCode,code)){
            result.put("status","fail");
            result.put("msg","验证码输入错误");
            return result;
        }
        request.getSession().removeAttribute("checkCode");
        String username = request.getParameter("username");
        int m = userDao.isUserExists(username);
        if(m == 0){
            result.put("status","fail");
            result.put("msg","用户不存在");
            return result;
        }
        String password = request.getParameter("password");
        Map<String,Object> params = new HashMap<>();
        params.put("username",username);
        //获取加密后的密码
        password = EncryptUtil.Base64Encode(password);
        params.put("password",password);
        int n = userDao.passwordValidate(params);
        if(n == 0){
            result.put("status","fail");
            result.put("msg","用户密码错误");
            return result;
        }
        request.getSession().setAttribute("username",username);
        result.put("status","success");
        return result;
    }

    /**
     * 修改用户密码
     * @param request
     * @return
     */
    public Map<String,Object> update(HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        //验证码
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        String mailCode = (String)request.getSession().getAttribute(username);
        if(StringUtils.isEmpty(mailCode)){
            result.put("status","fail");
            result.put("msg","未发送验证码或者验证码已失效");
            return result;
        }else if(!StringUtils.equals(code,mailCode)){
            result.put("status","fail");
            result.put("msg","验证码输入错误");
            return result;
        }
        String password = request.getParameter("password");
        Map<String,Object> params = new HashMap<>();
        params.put("username",username);
        //获取加密后的密码
        password = EncryptUtil.Base64Encode(password);
        params.put("password",password);
        userDao.updatePassword(params);
        result.put("status","success");
        result.put("msg","密码修改成功");
        return result;
    }

}
