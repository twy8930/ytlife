package com.wizard.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.wizard.model.Face;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 人脸识别工具类
 * 
 * @author wizard
 * 
 */
public class FaceUtil {

	public String getFaceInfo(String uri) {
		String result = "";
		String url = Constants.rb.getString("face.url") + uri;
		URL l_url = null;
		try {
			l_url = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		HttpURLConnection l_connection = null;
		StringBuffer buffer = new StringBuffer();
		String json = "";
		try {
			l_connection = (HttpURLConnection) l_url.openConnection();
			l_connection.setConnectTimeout(5 * 1000);
			l_connection.setRequestMethod("GET");
			InputStream inputStream = l_connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			l_connection.disconnect();
			json = buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray("face");
		if (jsonArray.size() > 1) {
			result = "对不起，小助手检测到至少两个人，请转身查看您身后是否有人。";
		} else if(jsonArray.size() ==0){
			result = "小助手未识别到人脸，请换一张清晰的照片再试试吧！";
		}else {
			JSONObject faceObject = ((JSONObject) jsonArray.get(0)).getJSONObject("attribute");
			Face face = new Face();
			face.age_value = faceObject.getJSONObject("age").getInt("value");
			face.age_range = faceObject.getJSONObject("age").getInt("range");
			face.gender_value = faceObject.getJSONObject("gender").getString(
					"value");
			face.gender_confidence = faceObject.getJSONObject("gender")
					.getDouble("confidence");
			face.race_value = faceObject.getJSONObject("race").getString(
					"value");
			face.race_confidence = faceObject.getJSONObject("race").getDouble(
					"confidence");
			face.smiling_value = faceObject.getJSONObject("smiling").getDouble(
					"value");
			result = handelFace(face);
		}
		return result;
	}

	private static String handelFace(Face face) {
		if(face==null){
			return "小助手未识别到人脸，请换一张清晰的照片再试试吧！";
		}
		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer.append("小助手掐指一算：\n");
		resultBuffer.append("您为"+raceConvert(face.race_value)+genderConvert(face.gender_value)+"\n");
		if(face.age_value<30){
			resultBuffer.append("今年"+face.age_value+"岁左右\n");
		}else{
			resultBuffer.append("今年"+(face.age_value-face.age_range/2)+"岁左右\n");
		}
		if(face.smiling_value<40){
			resultBuffer.append("看起来略显忧郁\n");
		}else{
			resultBuffer.append("看起来心情不错\n");
		}
		resultBuffer.append("精神面貌："+convertSmile(face.smiling_value)+"分！");
		return resultBuffer.toString();
	}

	private static String genderConvert(String gender) {
		String result = "男性";
		if ("Male".equals(gender))
			result = "男性";
		else if ("Female".equals(gender))
			result = "女性";
		return result;
	}

	private static String raceConvert(String race) {
		String result = "亚洲";
		if ("Asian".equals(race))
			result = "亚洲";
		else if ("White".equals(race))
			result = "欧美";
		else if ("Black".equals(race))
			result = "非洲";
		return result;
	}
	
	private static int convertSmile(double smiling_value){
		int result=(int)smiling_value/10+2;
		if(result>10){
			result=10;
		}
		if(result<5){
			result=5;
		}
		return result;
	}

}
