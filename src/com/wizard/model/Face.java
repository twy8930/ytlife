package com.wizard.model;

/**
 * 人脸识别信息
 * @author wizard
 *
 */
public class Face {
	//年龄值
	public int age_value;
	//年龄值偏差范围
	public int age_range;
	//性别
	public String gender_value;
	//性别确定性
	public double gender_confidence;
	//种族
	public String race_value;
	//种族确定性
	public double race_confidence;
	//微笑值
	public double smiling_value;
}
