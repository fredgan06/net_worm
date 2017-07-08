package cn.g_open.net_worm.controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.g_open.net_worm.biz.BizIpInfo;
import cn.g_open.net_worm.db.model.FindIpPoolModel;
import cn.g_open.net_worm.db.model.IpInfo;
import cn.g_open.net_worm.utils.ApplicationProUtil;
import cn.g_open.net_worm.utils.IpUtils;
import cn.g_open.net_worm.utils.TestApplicationUtil;

@RestController
@RequestMapping("/ip")
public class IpInfoConroller
{
    @Autowired
    private ApplicationProUtil appPorUtil;
    
    @Autowired
    private TestApplicationUtil testPorUtil;
    
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

    @RequestMapping("/saveIp")
    public String saveIp(String startIp, String endIp) throws IOException
    {
        bizIpInfo.saveIp(startIp, endIp);
        return "OK";
    }

    @RequestMapping("/findLocationByIp")
    public String findLocationByIp(String ip)
    {
        String location = "您查询的IP地址有误，请核实后再查询！";
        if (IpUtils.checkIpFormat(ip))
        {
            location = bizIpInfo.findLocationByIp(ip);
        }
        return location;
    }
    
    @RequestMapping("/getPor")
    public String findLocationByIp()
    {
        return testPorUtil.getUserName() + " ; " + testPorUtil.getPassword();
    }

    @RequestMapping("/startPool")
    public String startPool()
    {
        ExecutorService pool = Executors.newFixedThreadPool(300);

        for (int i = 0; i < 255; i++)
        {
            pool.submit(new FindIpPoolModel("2." + i + ".0.0", "2." + i + ".255.255", bizIpInfo));
        }
        pool.shutdown();

        while (true)
        {
            if (pool.isTerminated())
            {
                System.out.println("结束了！");
                break;
            }
            try
            {
                Thread.sleep(10000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return "OK";
    }
    

}
