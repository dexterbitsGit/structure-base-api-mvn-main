package com.dexterbits.app.domain.service;

import com.dexterbits.app.domain.Purchase;
import com.dexterbits.app.domain.repository.PurchaseRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;

  @Autowired
  public PurchaseService(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  public List<Purchase> getAll() {
    return purchaseRepository.getAll();
  }

  public Optional<List<Purchase>> getPurchaseByClient(String clientId) {
    return purchaseRepository.getByClient(clientId);
  }

  public Purchase save(Purchase purchase){
    return purchaseRepository.save(purchase);
  }

}
