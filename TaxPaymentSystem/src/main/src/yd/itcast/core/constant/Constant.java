/*
信息:
*/
package yd.itcast.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
//    系统权限集合
    private static String PRIVILEGE_XZGL="xzgl";
    private static String PRIVILEGE_HQFW="hqfw";
    private static String PRIVILEGE_NSFW="taxService";
    private static String PRIVILEGE_SPACE="spaces";
    private static String PRIVILEGE_ZXXX="zxxx";
    //系统中用户在session中的标识符
    public static String USER="SYS_USER";
    public static Map<String,String> PRIVILEGE_Map;
    static {
        PRIVILEGE_Map=new HashMap<String,String>();
        PRIVILEGE_Map.put(PRIVILEGE_XZGL,"行政管理");
        PRIVILEGE_Map.put(PRIVILEGE_HQFW,"后勤服务");
        PRIVILEGE_Map.put(PRIVILEGE_NSFW,"纳税服务");
        PRIVILEGE_Map.put(PRIVILEGE_SPACE,"我的空间");
        PRIVILEGE_Map.put(PRIVILEGE_ZXXX,"在线学习");
    }
}
