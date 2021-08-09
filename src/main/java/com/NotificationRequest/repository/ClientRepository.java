package com.NotificationRequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NotificationRequest.model.ClientInfo;

@Repository
public interface ClientRepository extends JpaRepository<ClientInfo, String> {

}
