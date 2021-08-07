package com.NotificationRequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NotificationRequest.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

}
