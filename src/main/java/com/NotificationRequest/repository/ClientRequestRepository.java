package com.NotificationRequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NotificationRequest.model.ClientRequest;

@Repository
public interface ClientRequestRepository extends JpaRepository<ClientRequest, Integer> {

}
