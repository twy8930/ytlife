package com.wizard.util;

/**
 * 路由类
 * @author wizard
 *
 */
public class RouterUtil {

	/**
	 * blank    空
	 * author   作者
	 * wz       违章
	 * gjj      公积金
	 * kd       快递
	 * unknown  无法识别
	 *
	 */
	public enum Router {  
	    blank,author,wz,gjj,kd,whatkd,unknown 
	} 
	
	public static Router route(String content){
		if(content==null||content.equals("")){
			return Router.blank;
		}
		content=content.trim();
		if(content.equals("作者是谁")){
			return Router.author;
		}
		if(content.startsWith("鲁")&&content.length()==7){
			return Router.wz;
		}
		if(content.startsWith("申通")||content.startsWith("申通快递")||content.startsWith("EMS")||
				content.startsWith("ems")||content.startsWith("顺丰")||content.startsWith("顺丰快递")||
				content.startsWith("顺风")||content.startsWith("顺风快递")||content.startsWith("圆通")||
				content.startsWith("圆通快递")||content.startsWith("中通")||content.startsWith("中通快递")||
				content.startsWith("韵达")||content.startsWith("韵达快递")||content.startsWith("天天")||
				content.startsWith("天天快递")||content.startsWith("汇通")||content.startsWith("汇通快递")||
				content.startsWith("全峰")||content.startsWith("全峰快递")||content.startsWith("德邦")||
				content.startsWith("德邦物流")||content.startsWith("德邦快递")||content.startsWith("宅急送")){
			return Router.kd;
		}
		if(content.equals("快递")){
			return Router.whatkd;
		}
		if(content.length()==14&&isDigital(content)){
			return Router.gjj;
		}
		return Router.unknown;
	}
	
	/**
	 * 判断一个字符串是否为数字
	 * @param content
	 * @return boolean
	 */
	private static boolean isDigital(String content){
		if(content==null||content.equals("")){
			return false;
		}
		for (int i = content.trim().length(); --i >= 0;) {
			if (!Character.isDigit(content.trim().charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
}
