package io.jc.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestHandler<T> {

	@Autowired
	RestTemplate restTemplate;

	public T callRestService(CustomRestTemplate<T> restInterface) {

		T respbody = null;
		respbody = restInterface.execute(restTemplate);
		return respbody;

	}

}
