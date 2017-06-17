package cn.g_open.net_worm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.g_open.net_worm.db.DbUserInfo;
import cn.g_open.net_worm.db.model.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetWormApplicationTests
{
    @Autowired
    private DbUserInfo dbUserInfo;
    
    @Test
    public void contextLoads()
    {
        UserInfo user = dbUserInfo.findByName("admin");
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        
    }

}
