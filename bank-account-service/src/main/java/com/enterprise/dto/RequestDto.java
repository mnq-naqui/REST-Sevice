package com.enterprise.dto;

import java.io.Serializable;

public class RequestDto implements Serializable{
	
	private static final long serialVersionUID = -1982718622869443726L;
	
	private UserDetailResource customer;

	public UserDetailResource getCustomer() {
		return customer;
	}

	public void setCustomer(UserDetailResource customer) {
		this.customer = customer;
	}
	
}
