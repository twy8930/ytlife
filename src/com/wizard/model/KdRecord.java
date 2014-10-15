package com.wizard.model;

import java.util.List;

/**
 * 快递信息类
 * @author wizard
 *
 */
public class KdRecord {
	
	public String kd_name;//快递名称,中文
	public String message;
	public String state;
	public String status;
	public String com;
	public String nu;
	public List<KdPerRecord> data;

}
