package com.seanhed.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {

	public static ResponseEntity<Object> generateSuccessMessage(String message) {
		return ResponseEntity.ok().body(message);
	}

	public static ResponseEntity<Object> generateErrorCode(int errorCode, String description) {
		Map<String, Object> map = new HashMap<>();
		map.put("Error code ", errorCode);
		map.put("Description", description);
		return ResponseEntity.badRequest().body(map);
	}

}
