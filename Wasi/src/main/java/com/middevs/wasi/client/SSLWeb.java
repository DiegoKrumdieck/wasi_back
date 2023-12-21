package com.middevs.wasi.client;

import javax.net.ssl.SSLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

public class SSLWeb {
	public static WebClient webClient(String baseUrl) throws SSLException {
		SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();
		HttpClient httpClient = HttpClient.create().secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));
		ReactorClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

		return WebClient.builder().clientConnector(httpConnector)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).baseUrl(baseUrl).build();
	}
	
	public static WebClient webClientWihAuthorization(String baseUrl, String authorization) throws SSLException {
		SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();
		HttpClient httpClient = HttpClient.create().secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));
		ReactorClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

		return WebClient.builder().clientConnector(httpConnector)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultHeader("Authorization", String.format("Bearer %s", authorization)).baseUrl(baseUrl).build();
	}
}
