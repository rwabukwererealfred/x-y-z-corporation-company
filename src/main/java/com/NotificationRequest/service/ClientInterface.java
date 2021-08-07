package com.NotificationRequest.service;

import java.util.List;

import com.NotificationRequest.model.Client;
import com.NotificationRequest.model.ClientRequest;

public interface ClientInterface {

	public Client saveClient(String clientName, String Category);
	public List<ClientRequest>findClientRateLimite(String clientId);
	public void update(String clientId, String category);
	public String sendRequest(String clientId);
	
}
