package com.dexterbits.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_roles")
@IdClass(UsuarioRolId.class)
@Getter
@Setter
@NoArgsConstructor
public class UsuarioRol {

  @Id
  @Column(nullable = false, length = 20)
  private String username;

  @Id
  @Column(nullable = false, length = 20)
  private String role;

  @Column(name = "granted_date", nullable = false)
  private LocalDateTime grantedDate;

  @ManyToOne
  @JoinColumn(name = "username", referencedColumnName = "username", insertable = false,
      updatable = false)
  private Usuario user;
}
