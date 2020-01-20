package com.yuntian.spring.mybatis.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import java.io.IOException;
import javax.sql.DataSource;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2020/1/18 0018 13:05
 * @Description:
 */
@Configuration
@MapperScan("com.yuntian.spring.mybatis.dao")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource ) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resourceLoader=new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resourceLoader.getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

}