package com.wizard.model;

/**
 * 公积金记录
 * @author wizard
 *
 */
public class GjjMonthRecord {
	
	public String gjj_account;
	public String record_date;
	public String status;
	public String gjj_date;
	public double new_income;
	public double total;
	
	public String toString(){
		return gjj_account+" "+record_date+" "+status+" "+gjj_date+" "+new_income+" "+total;
	}

}
