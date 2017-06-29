package cn.g_open.net_worm.biz;

import java.io.IOException;

import cn.g_open.net_worm.db.model.IpInfo;

public interface BizIpInfo
{
    public IpInfo selectById(Long id);
    
    public void insertIpInfo(IpInfo ipInfo);
    
    public void saveIp(String startIp, String endIp) throws IOException;
}
