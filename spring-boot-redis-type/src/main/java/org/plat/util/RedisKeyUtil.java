package org.plat.util;

public class RedisKeyUtil {

    /**
     * redis的key格式
     * @param tableName         表名
     * @param majorKey          主键名
     * @param majorKeyValue     主键值
     * @return
     */
    public static String getKey(String tableName, String majorKey, String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        return buffer.toString();
    }

}
