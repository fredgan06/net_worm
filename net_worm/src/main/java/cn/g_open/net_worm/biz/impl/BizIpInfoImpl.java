package cn.g_open.net_worm.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.g_open.net_worm.biz.BizIpInfo;
import cn.g_open.net_worm.db.mapper.IpInfoMapper;
import cn.g_open.net_worm.db.model.IpInfo;

@Service
public class BizIpInfoImpl implements BizIpInfo
{
    @Autowired
    private IpInfoMapper ipInfoMapper;

    @Override
    public IpInfo selectById(Long Id)
    {
        return ipInfoMapper.selectByPrimaryKey(Id);
    }

}
