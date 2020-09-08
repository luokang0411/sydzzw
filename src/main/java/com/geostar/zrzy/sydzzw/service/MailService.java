package com.geostar.zrzy.sydzzw.service;

import com.geostar.zrzy.sydzzw.utils.HttpClientUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class MailService {

    private final static Logger logger = LoggerFactory.getLogger(MailService.class);

//    final static String  SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;

    @Value("${mail.host}")
    private String mailHost;

    @Value("${mail.url}")
    private String mailUrl;

    /**
     * 发送验证码
     * @param request
     * @return
     */
    public Map<String,Object> sendMail(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String code = (String) session.getAttribute(email);
        if(!StringUtils.isEmpty(code)){
            //已发送验证码
            result.put("status","fail");
            result.put("msg","请不要重复发送！");
        }else{
            code = getCode();
//            String title = "修改密码";
//            String text = "您的验证码是：<span style='font-weight:bold;color:red;'>" + code + "</span>,  有效时间为30分钟。";
//                sendMail(email,title,text);
                String status = sendMail(email,code);
                if(StringUtils.equals(status,"1")){
                    result.put("status","success");
                    result.put("msg","发送成功！");
                    session.setAttribute(email,code);
                    final Timer timer= new Timer();
                    timer.schedule(new TimerTask(){
                        @Override
                        public void run(){
                            session.removeAttribute(email);
                            timer.cancel();
                        }
                    },30*60*1000);
                }else{
                    result.put("status","fail");
                    result.put("msg","发送失败,请联系技术支持024-22972212");
                }
        }
        return result;
    }

    /**
     * 通过java.mail发送邮件
     * @param email
     * @param title
     * @param text
     * @throws MessagingException
     */
    public void sendMail(String email,String title, String text) throws MessagingException {
        //邮箱服务器
        String smtpHost = mailHost;
        //邮箱用户名
        String username = mailUsername;
        //邮箱授权码（自定义）
        String password = mailPassword;
        //要发送到的邮箱（自定义）
        String to = email;
        //自己的邮箱（自定义）
        String from = mailUsername;
        Properties props = new Properties();
//        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", mailHost);
//        props.put("mail.smtp.username", username);
//        props.put("mail.smtp.password", password);
        Session session = Session.getInstance(props, new Authenticator() {
            //身份认证
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);
        InternetAddress[] addresses = {new InternetAddress(to)};
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject(title);
        message.setSentDate(new Date());
//        message.setText(text);
        message.setContent(text,"text/html;charset=utf-8");
        Transport transport = session.getTransport("smtp");
        transport.connect(smtpHost, username, password);
        transport.send(message);

    }

    /**
     * 生成6位随机数字验证码
     * @return
     */
    private String getCode(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<6;i++){
            int num = (int)(Math.random()*10);
            sb.append(num);
        }
        return sb.toString();
    }

    /**
     * 调用webservice接口发送邮件
     * @param email
     * @param code
     * @return
     */
    private String sendMail(String email,String code){
        String result = null;
        String text = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soap:Body><SendEmail xmlns=\"http://tempuri.org/\">" +
                "<mailTo>"+ email +"</mailTo>"+
                "<mailContent>"+ code +"</mailContent>"+
                "</SendEmail></soap:Body></soap:Envelope>";
        result = HttpClientUtil.sendHttpPostXml(mailUrl,text);
        if(StringUtils.isNotEmpty(result)){
            result = result.split("<SendEmailResult>")[1].split("</SendEmailResult>")[0];
        }
        return result;
    }




}
