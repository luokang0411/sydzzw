package com.geostar.zrzy.sydzzw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geostar.zrzy.sydzzw.entity.UserInfo;
import com.geostar.zrzy.sydzzw.utils.HttpClientUtil;
import com.jayway.jsonpath.JsonPath;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Demo {
    public static void main(String[] args) throws Exception {

        System.out.println(13 & -1);

    }

    public static String getUserMacdz(String ip) throws Exception {
        StringBuilder sb = new StringBuilder();
        InetAddress address = InetAddress.getByName(ip);
        NetworkInterface network = NetworkInterface.getByInetAddress(address);
        if(network==null){
            return "";
        }
        byte[] mac = network.getHardwareAddress();
        if(mac==null){
            return "";
        }
        for(int i = 0; i < mac.length; i++){
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        return sb.toString();
    }

    public static String getMacInWindows(String ip) {
        String result = "";
        String[] cmd = {"cmd", "/c", "ping " + ip};
        String[] another = {"cmd", "/c", "ipconfig -all"};
        // 获取执行命令后的result
        String cmdResult = callCmd(cmd, another);
        // 从上一步的结果中获取mac地址
        result = filterMacAddress(ip, cmdResult, "-");
        return result;
    }

    // 命令执行
    public static String callCmd(String[] cmd, String[] another) {
        String result = "";
        String line = "";
        try {
            Runtime rt = Runtime.getRuntime();
            // 执行第一个命令
            Process proc = rt.exec(cmd);
            proc.waitFor();
            // 执行第二个命令
            proc = rt.exec(another);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 获取mac地址
    public static String filterMacAddress(final String ip, final String sourceString, final String macSeparator) {
        String result = "";
        String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(sourceString);
        while (matcher.find()) {
            result = matcher.group(1);
            // 因计算机多网卡问题，截取紧靠IP后的第一个mac地址
            int num = sourceString.indexOf(ip) - sourceString.indexOf(": "+result + " ");
            if (num>0&&num<300) {
                break;
            }
        }
        return result;
    }


}
