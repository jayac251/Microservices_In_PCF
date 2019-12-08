package io.jc.sales.service;

import org.springframework.web.client.RestTemplate;


public interface CustomRestTemplate<T>{
	public T execute(RestTemplate t);

}
