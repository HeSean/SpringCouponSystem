package com.seanhed.beans;

public enum ClientType {
	 ADMINISTRATOR("Administrator"),
	COMPANY("Company"),
	CUSTOMER("Customer");
	
private String clientType;

 private ClientType (String value) {
         this. clientType = value;
 }
}