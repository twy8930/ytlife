package com.wizard.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import com.wizard.model.WzCode;
import com.wizard.model.WzRecord;
import com.wizard.model.WzResult;

/**
 * 车辆违章查询工具类
 * 
 * @author wizard
 * 
 */
public class WzUtil {

	public WzResult getRecords(String account) {
		WzResult wzResult=new WzResult();
		List<WzRecord> records = new ArrayList<WzRecord>();
		account = account.toUpperCase().trim();
		try {
			URL l_url = null;
			try {
				l_url = new URL(Constants.rb.getString("wz.url"));
			} catch (MalformedURLException e1) {
				wzResult.code=1;
				wzResult.records=records;
				e1.printStackTrace();
				return wzResult;
			}
			HttpURLConnection l_connection = null;
			try {
				l_connection = (HttpURLConnection) l_url.openConnection();
			} catch (IOException e1) {
				wzResult.code=1;
				wzResult.records=records;
				e1.printStackTrace();
				return wzResult;
			}
			try {
				l_connection.setDoOutput(true);
				l_connection.setRequestMethod("POST");
				l_connection.setConnectTimeout(3000);
				l_connection.setReadTimeout(3000);
			} catch (ProtocolException e) {
				wzResult.code=1;
				wzResult.records=records;
				e.printStackTrace();
				return wzResult;
			}
			String params = "vehvio.hphm=" + account + "&" + "vehvio.hpzl=02";
			byte[] b = params.getBytes();
			try {
				l_connection.getOutputStream().write(b, 0, b.length);
			} catch (IOException e) {
				wzResult.code=1;
				wzResult.records=records;
				e.printStackTrace();
				return wzResult;
			}
			try {
				l_connection.getOutputStream().flush();
				l_connection.getOutputStream().close();
			} catch (IOException e) {
				wzResult.code=1;
				wzResult.records=records;
				e.printStackTrace();
				return wzResult;
			}
			Parser parser = new Parser(l_connection);
			parser.setEncoding(parser.getEncoding());
			NodeFilter filter = new TagNameFilter("td");
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			int size = nodes.size();
			if (size <= 5) {
				l_connection.disconnect();
				wzResult.code=0;
				wzResult.records=records;
				return wzResult;
			} else {
				int count = size / 5;
				WzRecord wZRecord = null;
				for (int i = 1; i < count; i++) {
					wZRecord = new WzRecord();
					wZRecord.wz_date = nodes.elementAt(i * 5)
							.toPlainTextString();
					wZRecord.wz_position = nodes.elementAt(i * 5 + 1)
							.toPlainTextString();
					wZRecord.wz_action = nodes.elementAt(i * 5 + 2)
							.toPlainTextString();
					wZRecord.is_handled = nodes.elementAt(i * 5 + 3)
							.toPlainTextString();
					wZRecord.is_payed = nodes.elementAt(i * 5 + 4)
							.toPlainTextString();
					WzCode wzCode = Constants.wzCodeMap.get(wZRecord.wz_action
							+ "的");
					if (wzCode != null) {
						wZRecord.score = wzCode.score;
						wZRecord.fine = wzCode.fine;
					} else {
						wZRecord.score = 0;
						wZRecord.fine = 0;
					}
					records.add(wZRecord);
				}
			}
			l_connection.disconnect();
		} catch (Exception e) {
			wzResult.code=1;
			wzResult.records=records;
			e.printStackTrace();
			return wzResult;
		}
		wzResult.code=0;
		wzResult.records=records;
		return wzResult;
	}

}
