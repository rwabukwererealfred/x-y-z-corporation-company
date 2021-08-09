package com.NotificationRequest.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;


@Entity
@ApiModel(description = "Request model")
public class ClientRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	@Column(name="CATEGORY")
	@Enumerated(EnumType.STRING)
	private ClientCategory category;
	
	@Column(name="REQUEST_MADE_IN_MONTH")
	private int requestMadeInMonth;
	@Column(name="REQUEST_PER_MONTH")
	private int requestPerMonth;
	@Column(name="REQUEST_PER_SECOND")
	private int requestPerSecond;
	
	@Column(name="START_DATE")
	private LocalDateTime startDate;
	@Column(name="END_DATE")
	private LocalDateTime endDate;
	
	
	
	@ManyToOne
	@JoinColumn(name ="client")
	private ClientInfo client;
	
	public static enum ClientCategory{
		premium,normal
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientCategory getCategory() {
		return category;
	}

	public void setCategory(ClientCategory category) {
		this.category = category;
	}

	public int getRequestMadeInMonth() {
		return requestMadeInMonth;
	}

	public void setRequestMadeInMonth(int requestMadeInMonth) {
		this.requestMadeInMonth = requestMadeInMonth;
	}

	public int getRequestPerMonth() {
		return requestPerMonth;
	}

	public void setRequestPerMonth(int requestPerMonth) {
		this.requestPerMonth = requestPerMonth;
	}

	public int getRequestPerSecond() {
		return requestPerSecond;
	}

	public void setRequestPerSecond(int requestPerSecond) {
		this.requestPerSecond = requestPerSecond;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	

	public ClientInfo getClient() {
		return client;
	}

	public void setClient(ClientInfo client) {
		this.client = client;
	}

	public ClientRequest() {
		super();
		
	}

	public ClientRequest(ClientCategory category, int requestMadeInMonth, int requestPerMonth, int requestPerSecond,
			LocalDateTime startDate, LocalDateTime endDate) {
		
		this.category = category;
		this.requestMadeInMonth = requestMadeInMonth;
		this.requestPerMonth = requestPerMonth;
		this.requestPerSecond = requestPerSecond;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}
	
	
}
