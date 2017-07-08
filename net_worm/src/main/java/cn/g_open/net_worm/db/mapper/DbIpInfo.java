package cn.g_open.net_worm.db.mapper;

import cn.g_open.net_worm.db.model.IpInfo;

public interface DbIpInfo
{
    public Integer selectCountByLocationAndEndLong(IpInfo ipInfo);
    
    public void insertIpInfo(IpInfo ipInfo);
    
    public void updateEndLongByLocation(IpInfo ipInfo);
    
    public void selectLocationByStartLongAndEndLong(IpInfo ipInfo);
}
