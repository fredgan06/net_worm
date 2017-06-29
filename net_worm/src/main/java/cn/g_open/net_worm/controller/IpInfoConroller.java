package cn.g_open.net_worm.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.g_open.net_worm.biz.BizIpInfo;
import cn.g_open.net_worm.db.model.IpInfo;
import cn.g_open.net_worm.utils.ApplicationProUtil;
import cn.g_open.net_worm.utils.IpUtils;

@RestController
@RequestMapping("/ip")
public class IpInfoConroller
{
    @Autowired
    private ApplicationProUtil appPorUril;
    @Autowired
    private BizIpInfo bizIpInfo;

    @RequestMapping("/start")
    public String demo(String startIp, String endIp) throws IOException
    {
        if (StringUtils.isEmpty(startIp) || StringUtils.isEmpty(endIp))
        {
            return "输入参数有误，请重新输入！";
        }
        String[] startIps = startIp.split("\\.");
        String[] endIps = endIp.split("\\.");
        if (startIps.length != 4 || endIps.length != 4)
        {
            return "输入参数有误，请重新输入！";
        }

        Long startIpLong = IpUtils.ipToLong(startIp);
        Long endIpLong = IpUtils.ipToLong(endIp);

        for (Long i = startIpLong; i < endIpLong; i++)
        {
            String ip = IpUtils.longToIP(startIpLong);
            System.out.println(IpUtils.findByIp(ip));
        }
        return "OK";
    }

    @RequestMapping("/selectById")
    public IpInfo saveIpInfo(Long id) throws IOException
    {
        return bizIpInfo.selectById(id);
    }

    @RequestMapping("saveIp")
    public String saveIp(String startIp, String endIp) throws IOException
    {
        bizIpInfo.saveIp(startIp, endIp);
        return "OK";
    }

}
