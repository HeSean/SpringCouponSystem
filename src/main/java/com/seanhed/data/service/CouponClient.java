package com.seanhed.data.service;

import org.springframework.http.ResponseEntity;

import com.seanhed.beans.ClientType;

public interface CouponClient {
	public ResponseEntity<Object> login(String name, String password, ClientType clientType) throws Exception;

	public ResponseEntity<Object> logout(String token);

}
