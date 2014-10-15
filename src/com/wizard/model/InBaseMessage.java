package com.wizard.model;

/**
 * 接收消息基类
 * @author wizard
 *
 */
public class InBaseMessage {
	// 开发者微信号
	public String ToUserName;
	// 发送方帐号（一个OpenID）
	public String FromUserName;
	// 消息创建时间 （整型）
	public long CreateTime;
	// 消息类型（text/image/location/link）
	public String MsgType;
	// 消息id，64位整型
	public long MsgId;
}
