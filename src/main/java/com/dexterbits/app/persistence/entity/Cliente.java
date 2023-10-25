package com.dexterbits.app.persistence.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    private String id;

    private String nombre;

    @Column(name = "apellidos")
    private String apellido;

    private Long celular;

    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @Column(name = "correo_electronico")
    private String correoElectronico;

}
