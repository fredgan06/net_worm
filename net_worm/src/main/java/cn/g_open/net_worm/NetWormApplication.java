package cn.g_open.net_worm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.g_open.net_worm.db.mapper")
@EnableAutoConfiguration
public class NetWormApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(NetWormApplication.class, args);
    }

}
