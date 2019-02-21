package com.hz.GeoDataSystem.util.dynamicDataSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;

import tk.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * ClassName: DataSourceConfig
 * Describe:
 * 数据源配置
 */
@MapperScan(basePackages = "com.hz.GeoDataSystem.dao")
@Configuration
@PropertySource("classpath:config/dataSourceConfig.properties")//配置文件路径 
public class DataSourceConfig{
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	 @Bean(name = "primaryDataSource")
     @ConfigurationProperties(prefix = "spring.datasource.primary")
	 @Primary
     public DataSource primaryDataSource(){
		 //若选这种连接创建方式，则url配置时需要将.url改为.jdbc-url
		 DataSource dataSource=DataSourceBuilder.create().build();
		 //DataSource dataSource=new com.alibaba.druid.pool.DruidDataSource();
         return dataSource;
     }
     @Bean(name = "secondaryDataSource")
     @ConfigurationProperties(prefix = "spring.datasource.second")
	 @Primary
     public DataSource secondaryDataSource(){
    	 //DataSource dataSource=DataSourceBuilder.create().build();
    	//若选这种连接创建方式，则url配置时需要将.jdbc-url改为.url
    	 DataSource dataSource=new com.alibaba.druid.pool.DruidDataSource();
         return dataSource;
     }

     @Bean("dynamicDataSource")
     public DataSource dynamicDataSource() throws Exception{
    	 
     DynamicDataSource dataSource = new DynamicDataSource();
     //设置默认数据源，当无法映射到数据源时会使用默认数据源
     dataSource.setDefaultTargetDataSource(primaryDataSource());
    
     Map<Object, Object> targetDataSources = new HashMap<>(2);
     targetDataSources.put("primaryDataSource", primaryDataSource());
     targetDataSources.put("secondaryDataSource", secondaryDataSource());

     DynamicDatasourceHolder.dataSourceKeys.addAll(targetDataSources.keySet());

     //设置数据源映射
     dataSource.setTargetDataSources(targetDataSources);
     dataSource.afterPropertiesSet();

     return dataSource;
 }

 /**
  * 配置 SqlSessionFactoryBean
  * @ConfigurationProperties 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到
  * Bean 的属性中，如果没有使用 *.xml 则可以不用该配置，否则将会产生 invalid bond statement 异常
  *
  * @return the sql session factory bean
 * @throws Exception 
  */
 @Bean
 public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
     SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
     // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
     sqlSessionFactoryBean.setDataSource(dynamicDataSource());
     
   //此处设置为了解决找不到mapper文件的问题
     sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
     return sqlSessionFactoryBean.getObject();
 }

 /**
  * 注入 DataSourceTransactionManager 用于事务管理
 * @throws Exception 
  */
 @Bean
 public PlatformTransactionManager transactionManager() throws Exception {
     return new DataSourceTransactionManager(dynamicDataSource());
 }
}
