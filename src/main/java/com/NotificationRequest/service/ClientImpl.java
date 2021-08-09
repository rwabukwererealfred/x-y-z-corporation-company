package com.NotificationRequest.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NotificationRequest.model.ClientInfo;
import com.NotificationRequest.model.ClientRequest;
import com.NotificationRequest.model.ClientRequest.ClientCategory;
import com.NotificationRequest.repository.ClientRepository;
import com.NotificationRequest.repository.ClientRequestRepository;

@Service
public class ClientImpl implements ClientInterface {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientRequestRepository requestRepository;

	@Override
	public ClientInfo saveClient(String clientName, String category) {
		String id = createKeyId(clientName);
		ClientInfo client = new ClientInfo();
		client.setId(id);
		client.setClientName(clientName);
		ClientRequest request = new ClientRequest();

		setClientCategory(category, request);
		clientRepository.save(client);
		request.setClient(client);
		request.setRequestMadeInMonth(0);
		request.setStartDate(LocalDateTime.now());
		request.setEndDate(request.getStartDate().plusDays(30));
		

		requestRepository.save(request);

		return client;

	}

	private static String createKeyId(String client) {
		String date = LocalDateTime.now().getYear() + "" + LocalDateTime.now().getMonthValue()
				+ LocalDateTime.now().getDayOfMonth() + LocalDateTime.now().getSecond();
		String uiid = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
		String name = client.substring(0, 1).toUpperCase();
		return date + uiid + name;
	}

	@Override
	public List<ClientRequest> findClientRateLimite(String clientId) {
		List<ClientRequest>list = requestRepository.findAll().stream().filter(i -> i.getClient().getId().equals(clientId))
				.sorted((x, y) -> y.getStartDate().compareTo(x.getStartDate())).collect(Collectors.toList());
		return list;
	}

	@Override
	public void update(String clientId, String category) {
		Optional<ClientInfo>client = clientRepository.findById(clientId);
		if(client.isPresent()) {
			Optional<ClientRequest>request = requestRepository.findAll().stream().filter(i->i.getClient().getId().equals(client.get().getId())
					&& i.getCategory().toString().equals(category)).findAny();
			
			if(request.isPresent()) {
				int permonth = request.get().getRequestPerMonth();
				request.get().setStartDate(LocalDateTime.now());
				request.get().setEndDate(LocalDateTime.now().plusDays(30));
				int perMonth =setClientCategory(category, request.get()).getRequestPerMonth();
				request.get().setRequestPerMonth(permonth+perMonth);
				}
			else {
				request.get().setStartDate(LocalDateTime.now());
				request.get().setEndDate(LocalDateTime.now().plusDays(30));
				request.get().setRequestMadeInMonth(0);
				setClientCategory(category, request.get());
			}
			requestRepository.save(request.get());
		}
		
	}

	@Override
	public String sendRequest(String clientId) {
		Optional<ClientRequest> request = requestRepository.findAll().stream()
				.filter(i -> i.getClient().getId().equals(clientId) && i.getEndDate().isAfter(LocalDateTime.now())
						&& i.getRequestMadeInMonth() < i.getRequestPerMonth())
				.findAny();
		if (request.isPresent()) {
			request.get().setRequestMadeInMonth(request.get().getRequestMadeInMonth() + 1);
			requestRepository.save(request.get());
			if (request.get().getCategory() == ClientCategory.premium) {
				return "premium";
			} else {
				return "normal";
			}

		} else {
			return "";
		}
	}
	
	private static ClientRequest setClientCategory(String category, ClientRequest request) {
		if(category.equals(ClientCategory.premium.toString())) {
			request.setRequestPerMonth(15);
			request.setRequestPerSecond(4);
			request.setCategory(ClientCategory.premium);
		}else if(category.equals(ClientCategory.normal.toString())) {
			request.setRequestPerMonth(10);
			request.setRequestPerSecond(2);
			request.setCategory(ClientCategory.normal);
		}else {
			throw new RuntimeException("sorry! category must be (premium) or (normal)");
		}
		return request;
	}

}
