package com.founder.interservice.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by summer on 2016/11/25.
 */
@Configuration
@MapperScan(basePackages = "com.founder.interservice.mapper.xzxt", sqlSessionTemplateRef  = "xzxtSqlSessionTemplate")
public class DataSourceXzxtConfig {

    @Bean(name = "xzxtDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.xzxt")
    @Primary
    public DataSource xzxtDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "xzxtSqlSessionFactory")
    @Primary
    public SqlSessionFactory xzxtSqlSessionFactory(@Qualifier("xzxtDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/xzxt/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "xzxtSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate xzxtSqlSessionTemplate(@Qualifier("xzxtSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
