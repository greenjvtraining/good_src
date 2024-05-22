package com.example.gptpush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gptpush.entity.NotificationSubscription;
import com.example.gptpush.entity.Product;

public interface NotificationSubscriptionRepository extends JpaRepository<NotificationSubscription, Long>{
	List<NotificationSubscription> findByProduct(Product product);
}
