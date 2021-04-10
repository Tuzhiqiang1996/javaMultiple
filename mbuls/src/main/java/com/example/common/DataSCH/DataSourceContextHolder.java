package com.example.common.DataSCH;

/**
 * @author Tu
 * @Package com.example.common.DataSCH
 * @date 2021/4/10-9:53
 * @message 该类的作用是持有当前线程环境下的数据源，并切换数据源
 */

public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    /**
     *  设置数据源
     * @param db
     */
    public static void setDataSource(String db){
        contextHolder.set(db);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear(){
        contextHolder.remove();
    }

}


