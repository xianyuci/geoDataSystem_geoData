package com.hz.GeoDataSystem.util.aop;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hz.GeoDataSystem.util.dynamicDataSource.DynamicDataSource;
import com.hz.GeoDataSystem.util.dynamicDataSource.DynamicDatasourceHolder;
/*
 * 动态数据源切面类
 */
@Aspect
//该切面应当先于 @Transactional 执行
@Order(-1)
@Component
public class DynamicDataSourceAspect{
	//匹配com.hz.GeoDataSystem.application.service包及其子包下的所有类的所有方法
  @Pointcut("execution(*com.hz.GeoDataSystem.application.service..*.*(..))")
  public void pointCut() {
  	
  }
    /**
     * 执行方法前更换数据源
     *
     * @param joinPoint 切点
     * @throws Exception 
     */
    @Before("@annotation(targetDataSource)")
    public void switchDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) throws Exception {
    	if (!DynamicDatasourceHolder.containDataSourceKey(targetDataSource.value())) {
            System.out.println("DataSource [{}] doesn't exist, use default DataSource [{}] " + targetDataSource.value());
        } else {
            // 切换数据源
            DynamicDatasourceHolder.setDataSourceKey(targetDataSource.value());
            System.out.println("Switch DataSource to [{}] in Method [{}] " +
                    DynamicDatasourceHolder.getDataSourceKey() + joinPoint.getSignature());
        }
    }
    /**
     * 执行方法后清除数据源设置
     * @param joinPoint 切点
     */
    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint joinPoint,TargetDataSource targetDataSource) {
        DynamicDatasourceHolder.clearDataSourceKey();
        System.out.println("Restore DataSource to [{}] in Method [{}] " +
        DynamicDatasourceHolder.getDataSourceKey() + joinPoint.getSignature());
    }
}
