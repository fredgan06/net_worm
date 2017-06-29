package cn.g_open.net_worm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("cn.g_open.net_worm.db.mapper")
public class NetWormApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(NetWormApplication.class, args);
    }

}
