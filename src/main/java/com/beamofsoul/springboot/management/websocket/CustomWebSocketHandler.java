package com.beamofsoul.springboot.management.websocket;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Async
@Component
public class CustomWebSocketHandler implements WebSocketHandler {
	
	private static AtomicInteger onlineCount = new AtomicInteger(0);
	
	private static CopyOnWriteArraySet<WebSocketSession> webSocketSet = new CopyOnWriteArraySet<WebSocketSession>();
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception {
		webSocketSet.remove(session);
		onlineCount.decrementAndGet();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		webSocketSet.add(session);
		onlineCount.incrementAndGet();
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		if (session.isOpen()) {
			String payload = message.getPayload().toString();
			if (payload.startsWith("sendAll#")) {
				sendMessageToAll(new TextMessage(payload.split("#")[1]));
			} else {
				session.sendMessage(new TextMessage("Server has received message : " + payload));
			}
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)
			throws Exception {
		exception.printStackTrace();
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	public static void sendMessageToAll(WebSocketMessage<?> message) {
		for (WebSocketSession session : webSocketSet) {
			try {
				if (session.isOpen()) {
					session.sendMessage(new TextMessage(message.getPayload().toString()));
					System.out.println("#### WEBSOCKET MESSAGE TO ALL("+session.getPrincipal().getName()+"): " + message.getPayload().toString());
				}
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	public static Integer getOnlineCount() {
		return onlineCount.get();
	}
}
