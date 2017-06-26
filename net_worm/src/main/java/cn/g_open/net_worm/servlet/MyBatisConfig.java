package cn.g_open.net_worm.servlet;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;

public class MyBatisConfig /*implements TransactionManagementConfigurer*/
{

/*    @Autowired
    DruidDataSource druidDataSource;

    @Bean(name = "sqlSessionFactory") // 3
    public SqlSessionFactory sqlSessionFactoryBean()
    {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(druidDataSource);
        bean.setTypeAliasesPackage("cn.g_open.net_worm.db.model");

        // 分页插件 //4
        // PageHelper pageHelper = new PageHelper();
        // Properties properties = new Properties();
        // properties.setProperty("reasonable", "true");
        // properties.setProperty("supportMethodsArguments", "true");
        // properties.setProperty("returnPageInfo", "check");
        // properties.setProperty("params", "count=countSql");
        // pageHelper.setProperties(properties);
        // 添加插件
        // bean.setPlugins(new Interceptor[]{pageHelper});

        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try
        {
            bean.setMapperLocations(resolver.getResources("classpath*:cn/g_open/net_worm/db/mapper/*Mapper.xml"));
            return bean.getObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(druidDataSource);
    }*/
    
}
