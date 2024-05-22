package com.example.gptpush.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gptpush.config.NotificationWebSocketHandler;
import com.example.gptpush.entity.NotificationSubscription;
import com.example.gptpush.entity.Product;
import com.example.gptpush.service.NotificationService;
import com.example.gptpush.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
    private ProductService productService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationWebSocketHandler webSocketHandler;

    @PostMapping("/{id}/restock")
    public ResponseEntity<?> restockProduct(@PathVariable Long id) {
        productService.updateProductStock(id, true);
        Product product = productService.getProductById(id);
        notificationService.notifyUsers(product);

        List<NotificationSubscription> subscriptions = notificationService.getSubscriptionsByProduct(product);
        for (NotificationSubscription subscription : subscriptions) {
            try {
                webSocketHandler.sendNotification(subscription.getUser().getUsername(), "Product " + product.getName() + " is back in stock!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok("Product restocked");
    }
}
