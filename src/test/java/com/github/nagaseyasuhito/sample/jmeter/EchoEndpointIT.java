package com.github.nagaseyasuhito.sample.jmeter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URI;
import java.net.URL;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import lombok.SneakyThrows;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@RunAsClient
public class EchoEndpointIT {
	@ArquillianResource
	protected URL url;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class).addClass(EchoServer.class);
	}

	@Test
	@SneakyThrows
	public void echoSuccess() {
		URI uri = new URI(this.url.toString().replaceAll("http://", "ws://") + "/echo").normalize();

		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		EchoClient client = new EchoClient();
		container.connectToServer(client, uri);
		client.getLatch().await();

		assertThat(client.getReceivedMessages().size(), is(EchoClient.NUMBER_OF_LOOPS));
	}
}
