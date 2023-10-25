package com.dexterbits.app.persistence;

import com.dexterbits.app.domain.Purchase;
import com.dexterbits.app.domain.repository.PurchaseRepository;
import com.dexterbits.app.persistence.crud.CompraCrudRepository;
import com.dexterbits.app.persistence.entity.Compra;
import com.dexterbits.app.persistence.mapper.PurchaseMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompraRepository implements PurchaseRepository {

  private final CompraCrudRepository compraCrudRepository;
  private final PurchaseMapper mapper;

  @Autowired
  public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
    this.compraCrudRepository = compraCrudRepository;
    this.mapper = mapper;
  }

  @Override
  public List<Purchase> getAll() {
    return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
  }

  @Override
  public Optional<List<Purchase>> getByClient(String clientId) {
    return compraCrudRepository.findByIdCliente(clientId)
        .map(compras -> mapper.toPurchases(compras));
  }

  @Override
  public Purchase save(Purchase purchase) {
    Compra compra = mapper.toCompra(purchase);
    compra.getProductos().forEach(producto -> producto.setCompra(compra));

    return mapper.toPurchase(compraCrudRepository.save(compra));
  }

}
