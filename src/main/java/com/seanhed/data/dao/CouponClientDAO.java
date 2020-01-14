package com.seanhed.data.dao;

import org.springframework.http.ResponseEntity;

import com.seanhed.beans.ClientType;

public interface CouponClientDAO {
	public ResponseEntity<Object> login(String name, String password, ClientType clientType) throws Exception;

	public ResponseEntity<Object> logout(String token);

}
