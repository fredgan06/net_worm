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


        for (int i = Integer.parseInt(startIps[3]); i < Integer.parseInt(endIps[3]); i++)
        {
            String ip = startIps[0] + "." + startIps[1] + "." + startIps[2] + "." + i;
            String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip + "&resource_id=6006";
            // 获取url返回的json数据
            String body = Jsoup.connect(url).ignoreContentType(true).execute().body();
            // 解析json数据获取location值
            String location = JSONObject.parseObject(body).getJSONArray("data").getJSONObject(0).getString("location");
            System.out.println(location);
        }
        return "OK";
    }

    @RequestMapping("/selectById")
    public IpInfo saveIpInfo(Long id) throws IOException
    {
        return bizIpInfo.selectById(id);
    }

}
