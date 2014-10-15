package com.wizard.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wizard.model.GjjMonthRecord;
import com.wizard.model.KdRecord;
import com.wizard.model.OutTextMessage;
import com.wizard.model.WzRecord;
import com.wizard.model.WzResult;
import com.wizard.util.Constants;
import com.wizard.util.FaceUtil;
import com.wizard.util.GjjUtil;
import com.wizard.util.KdUtil;
import com.wizard.util.MessageUtil;
import com.wizard.util.RouterUtil;
import com.wizard.util.RouterUtil.Router;
import com.wizard.util.WzUtil;

/**
 * 核心业务逻辑类
 * 
 * @author wizard
 * 
 */
public class CoreService {

	private static Logger logger = Logger
			.getLogger(CoreService.class.getName());

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			OutTextMessage textMessage = new OutTextMessage();
			textMessage.ToUserName = fromUserName;
			textMessage.FromUserName = toUserName;
			textMessage.CreateTime = new Date().getTime();
			textMessage.MsgType = MessageUtil.RESP_MESSAGE_TYPE_TEXT;
			textMessage.FuncFlag = 0;

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String inContent = requestMap.get("Content");
				logger.info("[" + fromUserName + "] - [" + inContent + "]");
				Router router = RouterUtil.route(inContent);
				switch (router) {
				case blank:
					respContent = "亲，不知道您说的啥呢！";
					break;
				case author:
					respContent = "twy";
					break;
				case wz:
					WzResult wzResult = new WzUtil().getRecords(inContent);
					if(wzResult.code>0){
						if(wzResult.code==1){
							respContent = "交警违章查询系统出现故障，请稍后再试。";
						}
					}else{
						if (wzResult.records == null || wzResult.records.size() == 0) {
							respContent = "无违章记录";
						} else {
							respContent="";
							int scoreTotal = 0;
							int fineTotal = 0;
							for (WzRecord record : wzResult.records) {
								String tempString="";
								tempString+="违章时间：" + record.wz_date+"\n";
								tempString+="违章地点：" + record.wz_position+"\n";
								tempString+="违章行为：" + record.wz_action+"\n";
								tempString+="违章扣分：" + record.score+"分\n";
								tempString+="违章罚款：" + record.fine+"元\n";
								tempString+="是否处理：" + record.is_handled+"\n";
								tempString+="是否交款：" + record.is_payed+"\n";
								tempString+="---\n";
								//2048-53=1995,2048是微信允许返回的最大字节数，53是结尾语句的最大长度
								if(respContent.getBytes().length+tempString.getBytes().length>=1995){
									tempString="[违章记录较多，无法全部显示]\n";
									if(respContent.getBytes().length+tempString.getBytes().length<2006){
										respContent+=tempString;
									}
									break;
								}else{
									respContent+=tempString;
									scoreTotal += record.score;
									fineTotal += record.fine;
								}
							}
							respContent+="以上记录共计扣" + scoreTotal + "分，罚"+fineTotal + "元(仅供参考)";
						}
					}
					break;
				case gjj:
					GjjMonthRecord lastRecord = new GjjUtil()
							.getLastRecord(inContent);
					if (lastRecord == null) {
						respContent = "对不起，没有相关记录。";
					} else {
						StringBuffer buffer = new StringBuffer();
						buffer.append("您查询的账号为：" + lastRecord.gjj_account)
								.append("\n");
						buffer.append("最近的记账日期：" + lastRecord.gjj_date).append(
								"\n");
						buffer.append("最近的发生额为：" + lastRecord.new_income)
								.append("\n");
						buffer.append("目前账户余额为：" + lastRecord.total).append(
								"\n");
						respContent = buffer.toString();
					}
					break;
				case whatkd:
					StringBuffer sb = new StringBuffer();
					sb.append("小助手目前支持查询以下快递公司的物流信息：").append("\n");
					Set<Map.Entry<String, String>> entrySet = Constants.kdLong
							.entrySet();
					for (Iterator<Map.Entry<String, String>> it = entrySet
							.iterator(); it.hasNext();) {
						Map.Entry<String, String> entry = it.next();
						if (entry.getKey().equals("EMS")) {
							sb.append(
									entry.getKey() + "    " + entry.getValue()
											+ "(客服)").append("\n");
						} else if (entry.getKey().equals("宅急送")) {
							sb.append(
									entry.getKey() + " " + entry.getValue()
											+ "(客服)").append("\n");
						} else {
							sb.append(
									entry.getKey() + "   " + entry.getValue()
											+ "(客服)").append("\n");
						}
					}
					respContent = sb.toString();
					break;
				case kd:
					KdRecord record = new KdUtil().getRecord(inContent);
					StringBuffer buffer = new StringBuffer();
					if (record.status.equals("1")) {
						buffer.append("快递公司：" + record.kd_name).append("\n");
						buffer.append("快递单号：" + record.nu).append("\n");
						buffer.append("----------").append("\n");
						for (int i = record.data.size(); i > 0; i--) {
							buffer.append(record.data.get(i - 1).context)
									.append("\n");
						}
					} else {
						buffer.append(record.message);
					}
					respContent = buffer.toString();
					break;
				case unknown:
					respContent = printMenu();
					break;
				default:
					respContent = printMenu();
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// 取得图片地址  
                String picUrl = requestMap.get("PicUrl");
                logger.info("[" + fromUserName + "] - [" + picUrl + "]");
                respContent = new FaceUtil().getFaceInfo(picUrl);
                logger.info("[" + fromUserName + "] - [" + respContent + "]");
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = printMenu();
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
				}
			}
			textMessage.Content = respContent;
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	public static String printMenu() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，我是烟台生活小助手，目前提供以下服务：").append("\n\n");
		buffer.append(">发送完整车牌号查询违章信息（例如，鲁f00000）；").append("\n");
		buffer.append(">发送14位数字公积金账号查询公积金信息；").append("\n");
		buffer.append(">发送自拍照，帮你分析性别、年龄、心情等信息；").append("\n");
		// buffer.append("2)发送快递公司名称及单号查询物流信息（例如，中通762411541253），发送“快递”查询支持哪些快递公司；").append("\n");
		buffer.append("<后续服务即将推出，敬请期待...>").append("\n");
		// buffer.append("2  发送公积金账号（14位数字）查询公积金信息").append("\n\n");
		buffer.append("回复其它任意信息显示此帮助菜单");
		return buffer.toString();
	}

}
