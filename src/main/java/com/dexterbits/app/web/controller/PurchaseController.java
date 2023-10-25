package com.dexterbits.app.web.controller;

import com.dexterbits.app.domain.Product;
import com.dexterbits.app.domain.Purchase;
import com.dexterbits.app.domain.service.ProductService;
import com.dexterbits.app.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

  private final PurchaseService purchaseService;
  private final ProductService productService;

  @Autowired
  public PurchaseController(PurchaseService purchaseService, ProductService productService) {
    this.purchaseService = purchaseService;
    this.productService = productService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Purchase>> getAll() {

    return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/client/{clientId}")
  public ResponseEntity<List<Purchase>> getPurchaseByClient(@PathVariable String clientId) {
    return purchaseService.getPurchaseByClient(clientId)
        .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/save")
  public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {

    return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
  }

  @GetMapping("/product/{categoryId}")
  public ResponseEntity<List<Product>> getProductByCategory(
      @PathVariable("categoryId") int categoryId) {
    return productService.getByCategory(categoryId)
        .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
