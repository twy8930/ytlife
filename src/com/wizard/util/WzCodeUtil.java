package com.wizard.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.wizard.model.WzCode;

/**
 * 加载违章代码工具类
 * 
 * @author wizard
 * 
 */
public class WzCodeUtil {

	private static Logger logger = Logger.getLogger(WzCodeUtil.class.getName());

	public static void loadWzCode() {
		// 驱动程序名
		String driver = Constants.rb.getString("sql.driver");
		// URL指向要访问的数据库名scutcs
		String url = Constants.rb.getString("sql.url");
		// MySQL配置时的用户名
		String user = Constants.rb.getString("sql.user");
		// MySQL配置时的密码
		String password = Constants.rb.getString("sql.password");
		Connection conn = null;
		try {
			// 加载驱动程序
			Class.forName(driver);
			// 连续数据库
			conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			// 要执行的SQL语句
			String sql = "select max(code) code,action,min(score) score,min(fine) fine from wz_action group by action";
			// 结果集
			ResultSet rs = statement.executeQuery(sql);
			WzCode wzCode;
			while (rs.next()) {
				wzCode = new WzCode();
				wzCode.code = rs.getString("code");
				wzCode.action = rs.getString("action");
				wzCode.score = rs.getInt("score");
				wzCode.fine = rs.getInt("fine");
				Constants.wzCodeMap.put(wzCode.action, wzCode);
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		logger.info("违章代码信息加载完毕");
	}

}
