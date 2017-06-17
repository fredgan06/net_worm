package cn.g_open.net_worm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.g_open.net_worm.db.DbIp4Info;
import cn.g_open.net_worm.db.DbUserInfo;
import cn.g_open.net_worm.db.model.Ip4Info;
import cn.g_open.net_worm.db.model.UserInfo;
import cn.g_open.net_worm.utils.ApplicationProUtil;

@RestController
@RequestMapping("/test")
public class TestConroller
{
    @Autowired
    private ApplicationProUtil appPorUril;
    @Autowired
    private DbUserInfo dbUserInfo;
    @Autowired
    private DbIp4Info dbIpInfo;
    
    @RequestMapping("/say")
    public List<Ip4Info> sayHello()
    {
//        List<UserInfo> list = dbUserInfo.findAll();
//        UserInfo user = list.get(0);
        List<Ip4Info> list = dbIpInfo.findAll();
        return list;
    }
}
