package com.dexterbits.app.domain.service;

import com.dexterbits.app.domain.Product;
import com.dexterbits.app.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAll() {
    return productRepository.getAll();
  }

  public Optional<Product> getProduct(int productId) {
    return productRepository.getProduct(productId);
  }

  @Secured("ROLE_ADMIN")
  public Optional<List<Product>> getByCategory(int categoryId) {
    return productRepository.getByCategory(categoryId);
  }

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public boolean delete(int productId) {
    return getProduct(productId).map(product -> {
      productRepository.delete(productId);
      return true;
    }).orElse(false);

  }

}
