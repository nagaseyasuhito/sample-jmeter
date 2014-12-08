package com.github.nagaseyasuhito.sample.jmeter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.StringWriter;

import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EchoEndpointTest {

	private EchoServer endpoint = new EchoServer();

	private StringWriter log;
	private WriterAppender appender;

	@Before
	public void before() {
		this.log = new StringWriter();

		WriterAppender appender = new WriterAppender(new PatternLayout("%m%n"), this.log);
		LogManager.getRootLogger().addAppender(appender);
		LogManager.getRootLogger().setAdditivity(false);
	}

	@After
	public void after() {
		LogManager.getRootLogger().removeAppender(this.appender);
		LogManager.getRootLogger().setAdditivity(true);
	}

	@Test
	public void onMessageSuccess() {
		assertThat(this.endpoint.onMessage("message"), is("message"));
	}
}
