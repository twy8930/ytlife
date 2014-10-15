package com.wizard.model;

/**
 * 	发送消息基类
 * @author wizard
 *
 */
public class OutBaseMessage {
	// 接收方帐号（收到的OpenID）
	public String ToUserName;
	// 开发者微信号
	public String FromUserName;
	// 消息创建时间 （整型）
	public long CreateTime;
	// 消息类型（text/music/news）
	public String MsgType;
	// 位0x0001被标志时，星标刚收到的消息
	public int FuncFlag;
}
