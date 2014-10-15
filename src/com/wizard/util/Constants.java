package com.wizard.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.wizard.model.WzCode;

/**
 * 常量类
 * @author wizard
 *
 */
public class Constants {
	
	// 配置文件
    public static ResourceBundle rb=ResourceBundle.getBundle("config");
    
    //快递公司简称对应全称
    public static Map<String, String> kdShort2Long=new HashMap<String, String>();
    //快递公司全称对应简称
    public static Map<String, String> kdLong2Short=new HashMap<String, String>();
    //快递公司全称列表
    public static Set<String> kdLongAll=new HashSet<String>();
    //快递公司列表
    public static Map<String, String> kdLong=new HashMap<String, String>();
    
    public static Map<String, WzCode> wzCodeMap=new HashMap<String, WzCode>();
    
    static{
    	kdShort2Long.put("shentong", "申通");
    	kdShort2Long.put("ems", "EMS");
    	kdShort2Long.put("shunfeng", "顺丰");
    	kdShort2Long.put("yuantong", "圆通");
    	kdShort2Long.put("zhongtong", "中通");
    	kdShort2Long.put("yunda", "韵达");
    	kdShort2Long.put("tiantian", "天天");
    	kdShort2Long.put("huitongkuaidi", "汇通");
    	kdShort2Long.put("quanfengkuaidi", "全峰");
    	kdShort2Long.put("debangwuliu", "德邦");
    	kdShort2Long.put("zhaijisong", "宅急送");
    	
    	kdLong2Short.put("申通快递", "shentong");
    	kdLong2Short.put("申通", "shentong");
    	kdLong2Short.put("EMS", "ems");
    	kdLong2Short.put("ems", "ems");
    	kdLong2Short.put("顺丰快递", "");
    	kdLong2Short.put("顺丰", "shunfeng");
    	kdLong2Short.put("圆通快递", "yuantong");
    	kdLong2Short.put("圆通", "yuantong");
    	kdLong2Short.put("中通快递", "zhongtong");
    	kdLong2Short.put("中通", "zhongtong");
    	kdLong2Short.put("韵达快递", "yunda");
    	kdLong2Short.put("韵达", "yunda");
    	kdLong2Short.put("天天快递", "tiantian");
    	kdLong2Short.put("天天", "tiantian");
    	kdLong2Short.put("汇通快递", "huitongkuaidi");
    	kdLong2Short.put("汇通", "huitongkuaidi");
    	kdLong2Short.put("全峰快递", "quanfengkuaidi");
    	kdLong2Short.put("全峰", "quanfengkuaidi");
    	kdLong2Short.put("德邦快递", "debangwuliu");
    	kdLong2Short.put("德邦物流", "debangwuliu");
    	kdLong2Short.put("德邦", "debangwuliu");
    	kdLong2Short.put("宅急送", "zhaijisong");
    	
    	kdLongAll=kdLong2Short.keySet();
    	
    	kdLong.put("顺丰","4008111111");
    	kdLong.put("圆通","02169777888");
    	kdLong.put("申通","4008895543");
    	kdLong.put("中通","4008270270");
    	kdLong.put("汇通","4009565656");
    	kdLong.put("韵达","4008216789");
    	kdLong.put("EMS","11183");
    	kdLong.put("天天","4001888888");
    	kdLong.put("全峰","4001000001");
    	kdLong.put("德邦","4008305555");
    	kdLong.put("宅急送","4006789000");
    }

}
