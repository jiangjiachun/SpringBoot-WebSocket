package com.jjc.config;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler {

	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	
	/**
	 * 接收消息时
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		System.out.println("接收到的消息=" + message.getPayload());
	}

	/**
	 * 成功连接时
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		sessions.add(session);
	}

	/**
	 * 断开连接时
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
		sessions.remove(session);
	}

	public List<WebSocketSession> getSessions() {
		return sessions;
	}

}
