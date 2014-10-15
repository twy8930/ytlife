package com.wizard.util;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.wizard.model.GjjMonthRecord;

/**
 * 公积金查询工具类
 * @author wizard
 *
 */
public class GjjUtil {
	
	/**
	 * 获取最近一次的记录
	 * @param account
	 * @return GjjMonthRecord
	 */
	public GjjMonthRecord getLastRecord(String account){
		if(account.startsWith("14220021")){
			return null;
		}
		account=account.trim();
		GjjMonthRecord gjjMonthRecord=new GjjMonthRecord();
		try {
			Parser parser = new Parser();
			parser.setURL(Constants.rb.getString("gjj.url")+account);
			parser.setEncoding(parser.getEncoding());
			NodeFilter filter=new HasAttributeFilter("class", "text02");
			NodeList nodes=parser.extractAllNodesThatMatch(filter);
			if(nodes.size()==0){
				return null;
			}
			String[] record=new String[6];
			for(int i=0;i<nodes.size()&&i<6;i++){
				Node node=nodes.elementAt(i);
				record[i]=node.toPlainTextString();
			}
			gjjMonthRecord.gjj_account=record[0];
			gjjMonthRecord.record_date=record[1];
			gjjMonthRecord.status=record[2];
			gjjMonthRecord.gjj_date=record[3];
			gjjMonthRecord.new_income=Double.parseDouble(record[4]);
			gjjMonthRecord.total=Double.parseDouble(record[5]);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return gjjMonthRecord;
	}

}
