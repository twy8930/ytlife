package com.wizard.model;

import java.util.List;

/**
 * 发送图文消息
 * @author wizard
 *
 */
public class OutNewsMessage extends OutBaseMessage{
	// 图文消息个数，限制为10条以内
	public int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	public List<Article> Articles;
}
