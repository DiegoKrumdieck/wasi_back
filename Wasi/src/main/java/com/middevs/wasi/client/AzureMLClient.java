package com.middevs.wasi.client;


import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.middevs.wasi.dto.InputDTOAzure;
import com.middevs.wasi.dto.InputUnoDTOAzure;
import com.middevs.wasi.dto.InputsDTOAzure;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@AllArgsConstructor
public class AzureMLClient {
	String url; 
	String apiKey;
	public Integer obtenerRespuesta(InputDTOAzure input) 
				throws Exception{
		WebClient client = SSLWeb.webClientWihAuthorization(url,apiKey);
		
		List<InputDTOAzure> inputs = new ArrayList<InputDTOAzure>(); 
		inputs.add(input); 
		InputUnoDTOAzure inputsObject = new InputUnoDTOAzure(inputs);
		InputsDTOAzure inputGeneral = new InputsDTOAzure(inputsObject);
		Mono<Map> monoResponse = client.post()
				.body(BodyInserters.fromValue(inputGeneral))
				.retrieve().bodyToMono(Map.class);
		Map mapa = monoResponse.block();
		ArrayList<Object> output1 = (ArrayList)((Map)mapa.get("Results")).get("output1");
		Map<String,String> scores = (Map)output1.get(0);
		return Integer.parseInt( scores.get("Scored Labels"));
	}
}
