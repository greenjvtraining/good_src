package com.example.gptpush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.gptpush.entity.NotificationSubscription;
import com.example.gptpush.entity.Product;
import com.example.gptpush.repository.NotificationSubscriptionRepository;

@Service
public class NotificationService {
	@Autowired
    private NotificationSubscriptionRepository subscriptionRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyUsers(Product product) {
        List<NotificationSubscription> subscriptions = subscriptionRepository.findByProduct(product);
        for (NotificationSubscription subscription : subscriptions) {
            // Implement logic to send notifications to the users
        }
    }

	public List<NotificationSubscription> getSubscriptionsByProduct(Product product) {
		return subscriptionRepository.findByProduct(product);
	}
}
