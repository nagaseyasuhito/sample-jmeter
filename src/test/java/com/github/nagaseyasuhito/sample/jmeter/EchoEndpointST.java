package com.github.nagaseyasuhito.sample.jmeter;

import java.net.URL;
import java.util.ResourceBundle;

import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EchoEndpointST extends EchoEndpointIT {
	@Before
	@SneakyThrows
	public void before() {
		this.url = new URL(ResourceBundle.getBundle("stress-test").getString("url"));
	}
}
