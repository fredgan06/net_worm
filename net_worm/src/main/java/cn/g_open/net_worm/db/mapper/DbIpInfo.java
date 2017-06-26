package cn.g_open.net_worm.db.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.g_open.net_worm.db.model.IpInfo;

@Mapper
public interface DbIpInfo
{
    public String selectLocationByEndIp(IpInfo ipInfo);
    
    public void insertIpInfo(IpInfo ipInfo);
    
    public void updateEndIpByStartIp(IpInfo ipInfo);
}
