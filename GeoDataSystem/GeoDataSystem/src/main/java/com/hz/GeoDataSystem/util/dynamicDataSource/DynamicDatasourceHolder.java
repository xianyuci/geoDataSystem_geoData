package com.hz.GeoDataSystem.util.dynamicDataSource;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

/*
 * 通过ThreadLocal维护一个全局唯一的map来实现数据源的动态切换
 */
public class DynamicDatasourceHolder {
//	private static final ThreadLocal<String> DATASOURCE_HOLDER = new ThreadLocal<String>(){
//
//        /** * 将 master 数据源的 key 作为默认数据源的 key */
//        @Override
//        protected String initialValue() {
//            return "primaryDataSource";
//        }
//	};
	private static final ThreadLocal<String> DATASOURCE_HOLDER = new ThreadLocal<String>();
    /** * 数据源的 key 集合，用于切换时判断数据源是否存在 */
    public static List<Object> dataSourceKeys = new ArrayList<>();
    
    /** * To switch DataSource * * @param key the key */
    public static void setDataSourceKey(String key) {
    	DATASOURCE_HOLDER.set(key);
    }
    /** * Get current DataSource * * @return data source key */
    public static String getDataSourceKey() {
        return DATASOURCE_HOLDER.get();
    }
    /** * To set DataSource as default */
    public static void clearDataSourceKey() {
    	DATASOURCE_HOLDER.remove();
    }
    /** * Check if give DataSource is in current DataSource list * * @param key the key * @return boolean boolean */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
    /**
     * 设置从从库读取数据
     * 采用简单生成随机数的方式切换不同的从库
     */
//    public static void setSlave() {
//    	int flag=RandomUtils.nextInt(-1,2);
//        if ( flag> 0) {
//        	System.out.println("RandomUtils.nextInt(-1,0)"+RandomUtils.nextInt(-1,0));
//            DynamicDatasourceHolder.setDataSourceKey("primaryDataSource");
//        } else {
//        	System.out.println("RandomUtils.nextInt(-1,0)"+RandomUtils.nextInt(-1,0));
//            DynamicDatasourceHolder.setDataSourceKey("secondaryDataSource");
//        }
//    }

}
