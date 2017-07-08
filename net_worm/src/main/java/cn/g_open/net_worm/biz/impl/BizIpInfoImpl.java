package cn.g_open.net_worm.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.g_open.net_worm.biz.BizIpInfo;
import cn.g_open.net_worm.db.mapper.DbIpInfo;
import cn.g_open.net_worm.db.mapper.IpInfoMapper;
import cn.g_open.net_worm.db.model.IpInfo;
import cn.g_open.net_worm.db.model.IpInfoExample;
import cn.g_open.net_worm.db.model.IpInfoExample.Criteria;
import cn.g_open.net_worm.utils.IpUtils;

@Service
public class BizIpInfoImpl implements BizIpInfo
{
    @Autowired
    private IpInfoMapper ipInfoMapper;
    @Autowired
    private DbIpInfo dbIpInfo;

    @Override
    public IpInfo selectById(Long Id)
    {
        return ipInfoMapper.selectByPrimaryKey(Id);
    }

    @Override
    public void insertIpInfo(IpInfo ipInfo)
    {
        dbIpInfo.insertIpInfo(ipInfo);
    }

    @Override
    public void saveIp(String startIp, String endIp)
    {
        Long startLong = IpUtils.ipToLong(startIp);
        Long endLong = IpUtils.ipToLong(endIp);
        for (Long i = startLong; i <= endLong; i++)
        {
            String ip = IpUtils.longToIP(i);
            String location = IpUtils.findByIp(ip);
            IpInfo ipInfo = new IpInfo();
            ipInfo.setStartLong(i);
            ipInfo.setEndLong(i);
            ipInfo.setLocation(location);
            ipInfo.setCreateBy("admin");
            ipInfo.setModifyBy("admin");
            Integer count = dbIpInfo.selectCountByLocationAndEndLong(ipInfo);
            if(count > 0){
                dbIpInfo.updateEndLongByLocation(ipInfo);
            }else {
                dbIpInfo.insertIpInfo(ipInfo);
            }
            System.out.println(Thread.currentThread().getName() + "ip=" + ip + "; location=" + location);
        }
    }

    @Override
    public String findLocationByIp(String ip)
    {
        String location = "未查到" + ip + "的具体地址！"; 
        Long ipLong = IpUtils.ipToLong(ip);
        IpInfoExample example = new IpInfoExample();
        Criteria criteria = example.createCriteria();
        criteria.andStartLongLessThanOrEqualTo(ipLong);
        criteria.andEndLongGreaterThanOrEqualTo(ipLong);
        List<IpInfo> ipInfoList = ipInfoMapper.selectByExample(example);
        if(null != ipInfoList && !ipInfoList.isEmpty()){
            location = ipInfoList.get(0).getLocation();
        }
        return location;
    }

}
