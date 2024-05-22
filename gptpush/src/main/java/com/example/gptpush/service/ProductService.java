package com.example.gptpush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gptpush.entity.Product;
import com.example.gptpush.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getOutOfStockProducts() {
		return productRepository.findByOutOfStock(true);
	}

	public void updateProductStock(Long productId, boolean inStock) {
		Optional<Product> result = productRepository.findById(productId);
		Product product = result.get();
		product.setOutOfStock(!inStock);
		productRepository.save(product);
	}

	public Product getProductById(Long id) {
		Optional<Product> result = productRepository.findById(id);
		return result.get();
	}
}
