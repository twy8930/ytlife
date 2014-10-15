package com.wizard.model;

/**
 * 接收地理位置信息
 * @author wizard
 *
 */
public class InLocationMessage extends InBaseMessage {
	// 地理位置维度
	public String Location_X;
	// 地理位置经度
	public String Location_Y;
	// 地图缩放大小
	public String Scale;
	// 地理位置信息
	public String Label;
}