package com.lucien.dap.user.authority.server.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.lucien.dap.user.authority.server.mapper.*"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfig {

    @Bean(name = {"ompDataSource"})
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource hotelDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = {"transactionManager"})
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("ompDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = {"sqlSessionFactory"})
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("ompDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations((new PathMatchingResourcePatternResolver()).getResources("classpath*:/mapper/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = {"sqlSessionTemplate"})
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}