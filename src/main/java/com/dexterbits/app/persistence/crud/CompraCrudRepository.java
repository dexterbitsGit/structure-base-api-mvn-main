package com.dexterbits.app.persistence.crud;

import com.dexterbits.app.persistence.entity.Compra;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

  Optional<List<Compra>> findByIdCliente(String idCliente);

}
