package com.seanhed.utils;

import java.util.HashMap;
import java.util.Map;
import static com.seanhed.utils.MinLog.*;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;

public class ResponseUtil {

	public static ResponseEntity<Object> generateSuccessMessage(String message) {
		Gson gson = new Gson();
		info(gson.toJson(message));
		return ResponseEntity.ok().body(gson.toJson(message));
	}

	public static ResponseEntity<Object> generateErrorCode(int errorCode, String description) {
		Map<String, Object> map = new HashMap<>();
		map.put("Error code ", errorCode);
		map.put("Description", description);
		return ResponseEntity.badRequest().body(map);
	}

}
