package com.seanhed.utils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import lombok.Data;


public class Database {
	private static String imageURL = "https://tinyurl.com/y3rauvft";
	private static Clock startClock = Clock.fixed(Instant.parse("2019-01-01T16:45:42.01Z"), ZoneId.of("Australia/Sydney"));
	private static Clock endClock = Clock.fixed(Instant.parse("2019-12-01T16:45:42.01Z"), ZoneId.of("Australia/Sydney"));
	private static Instant startInstant = Instant.parse("2019-01-01T16:45:42.01Z");
	private static Instant endInstant = Instant.parse("2019-12-01T16:45:42.01Z");

	public static  String getImageURL() {
		return imageURL;
	}

	public static Clock getStartClock() {
		return startClock;
	}

	public static Clock getEndClock() {
		return endClock;
	}
	
	public static Instant getStartInstant() {
		return startInstant;
	}
	
	public static Instant getEndInstant() {
		return endInstant;
	}




}
