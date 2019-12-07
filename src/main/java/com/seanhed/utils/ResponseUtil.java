package com.seanhed.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import com.seanhed.beans.Customer;



public class ResponseUtil {
	public static ResponseEntity<Object> generateErrorCode(int errorCode, String description) {
		Map<String, Object> map = new HashMap<>();
		map.put("Error code ", errorCode);
		map.put("Description", description);
		return ResponseEntity.badRequest().body(map);
	}

	public static ResponseEntity<Object> generateSuccessMessage(String message) {
		return ResponseEntity.ok().body(message);
	}
	
	public static ResponseEntity<List<Object>> generateSuccessMessageWithListBody(List list) {
		return ResponseEntity.ok().body(list);
	}
}
