package com.hz.GeoDataSystem.util.dynamicDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * ClassName: ApplicationConfiguration
 * Describe:
 * 动态数据源配置文件
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	@Override
	protected Object determineCurrentLookupKey() {
	        return DynamicDatasourceHolder.getDataSourceKey();
	}

}
