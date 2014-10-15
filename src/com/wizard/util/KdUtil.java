package com.wizard.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.wizard.model.KdRecord;

/**
 * 快递查询工具类
 * 
 * @author wizard
 * 
 */
public class KdUtil {
	
	public KdRecord getRecord(String account) {
		KdRecord kdRecord = new KdRecord();
		account=account.trim();
		String company="";
		String code="";
		for(String kd:Constants.kdLongAll){
			if(account.startsWith(kd)){
				company=Constants.kdLong2Short.get(kd);
				code=account.replaceAll(kd,"").trim();
				break;
			}
		}
		//如果不支持用户输入的快递公司，返回
		if(company.equals("")){
			kdRecord.status="400";
			StringBuffer sb=new StringBuffer();
			sb.append("对不起，小助手目前只支持查询以下快递公司的物流信息：").append("\n");
			for(String kd:Constants.kdLong.keySet()){
				sb.append(kd).append("\n");
			}
			kdRecord.message=sb.toString();
			return kdRecord;
		}
		String url=Constants.rb.getString("kd.url")+"&com="+company+"&nu="+code;
		URL l_url = null;
		try {
			l_url = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		HttpURLConnection l_connection = null;
		try {
			l_connection = (HttpURLConnection) l_url.openConnection();
			l_connection.setConnectTimeout(5 * 1000);
			l_connection.setRequestMethod("GET");
			InputStream inputStream = l_connection.getInputStream();
			JsonReader jsonReader=new JsonReader(new InputStreamReader(inputStream));
			Type type=new TypeToken<KdRecord>(){}.getType();
			kdRecord = new Gson().fromJson(jsonReader,type);
			if(kdRecord.status.equals("1")){
				if(kdRecord.data!=null&&kdRecord.data.size()>0)
					kdRecord.kd_name=Constants.kdShort2Long.get(kdRecord.com);
				else{
					kdRecord.message="暂时未能查出物流信息。";
				}
			}
			jsonReader.close();
			inputStream.close();
			l_connection.disconnect();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return kdRecord;
	}

}
