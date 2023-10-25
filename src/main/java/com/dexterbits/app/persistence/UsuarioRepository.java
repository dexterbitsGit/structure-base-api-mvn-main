package com.dexterbits.app.persistence;

import com.dexterbits.app.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {



}
