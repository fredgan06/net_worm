package cn.g_open.net_worm.db.model;

import cn.g_open.net_worm.biz.BizIpInfo;

public class FindIpPoolModel extends Thread
{
    private String startIp;
    private String endIp;
    private BizIpInfo bizIpInfo;

    public FindIpPoolModel(String startIp, String endIp, BizIpInfo bizIpInfo)
    {
        this.startIp = startIp;
        this.endIp = endIp;
        this.bizIpInfo = bizIpInfo;
    }

    @Override
    public void run()
    {
        bizIpInfo.saveIp(startIp, endIp);
    }
}
