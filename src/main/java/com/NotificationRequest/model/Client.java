package com.NotificationRequest.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(description = "Client model")
public class Client {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="CLIENT_NAME")
	private String clientName;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Client() {
		
	}

	public Client(String clientName) {
		super();
		this.clientName = clientName;
	}

	
	
	
}
