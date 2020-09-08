package com.geostar.zrzy.sydzzw.controller;

import com.geostar.zrzy.sydzzw.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailDataController {

    @Autowired
    private MailService mailService;

    /**
     * 发送验证码
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendMail",method = RequestMethod.POST)
    public Map<String,Object> sendMail(HttpServletRequest request){
        return mailService.sendMail(request);
    }

}
