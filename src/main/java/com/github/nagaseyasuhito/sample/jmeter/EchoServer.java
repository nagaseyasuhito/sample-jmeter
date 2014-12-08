package com.github.nagaseyasuhito.sample.jmeter;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoServer {
	@OnMessage
	public String onMessage(String message) {
		return message;
	}
}
