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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by summer on 2016/11/25.
 */
@Configuration
@MapperScan(basePackages = "com.founder.interservice.mapper.gxzxt", sqlSessionTemplateRef  = "gxzxtSqlSessionTemplate")
public class DataSourceGxzxtConfig {

    @Bean(name = "gxzxtDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.gxzxt")
    public DataSource gxzxtDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "gxzxtSqlSessionFactory")
    public SqlSessionFactory gxzxtSqlSessionFactory(@Qualifier("gxzxtDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/gxzxt/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "gxzxtTransactionManager")
    public DataSourceTransactionManager gxzxtTransactionManager(@Qualifier("gxzxtDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "gxzxtSqlSessionTemplate")
    public SqlSessionTemplate gxzxtSqlSessionTemplate(@Qualifier("gxzxtSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
