package com.github.nagaseyasuhito.sample.jmeter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import lombok.Getter;

@Getter
@ClientEndpoint
public class EchoClient {
	public static final int NUMBER_OF_LOOPS = 10;
	private List<String> receivedMessages = new ArrayList<>();
	private CountDownLatch latch = new CountDownLatch(1);

	@OnOpen
	public void onOpen(Session session) throws IOException {
		session.getBasicRemote().sendText(UUID.randomUUID().toString());
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		this.receivedMessages.add(message);

		if (this.receivedMessages.size() == NUMBER_OF_LOOPS) {
			session.close();
			this.latch.countDown();
		} else {
			session.getBasicRemote().sendText(UUID.randomUUID().toString());
		}
	}
}