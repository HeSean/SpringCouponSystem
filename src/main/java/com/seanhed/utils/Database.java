package com.seanhed.utils;

import java.time.Instant;
import java.util.Date;

public class Database {
	private static String imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNzrDEfsIOv6mjAECjNOkOZ5I8MWPE13xW9bUO8lYzqZ8cEtVa&s";
	private static Instant startInstant = Instant.parse("2020-01-01T16:45:42.01Z");
	private static Instant endInstant = Instant.parse("2020-09-01T16:45:42.01Z");

	public static String getImageURL() {
		return imageURL;
	}

	public static Instant getStartInstant() {
		return startInstant;
	}

	public static Instant getEndInstant() {
		return endInstant;
	}

	public static Date parseDate(String str) {
		System.out.println(str);
		Date date = new Date();
		if (str.length() < 13) {
			date = Date.from(Instant.parse(str.concat("T16:45:42.01Z")));
		} else {
			date = Date.from(Instant.parse(str));
		}
		System.out.println(date);
		return date;
	}
	
}
