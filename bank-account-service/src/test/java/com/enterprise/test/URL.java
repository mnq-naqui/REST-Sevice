package com.enterprise.test;

public interface URL {
	
	String IP = "http://localhost:8080";
	String CONTEXT_PATH = "/rest/every";
	String BASE_PATH = IP+CONTEXT_PATH;
	String GET_BANKACCOUNT = "/bankaccount";
	String GET_BANKACCOUNT_BY_ID = "/bankaccount/{customerId}";

}
