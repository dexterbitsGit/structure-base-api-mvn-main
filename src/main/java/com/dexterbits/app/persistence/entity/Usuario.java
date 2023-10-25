package com.dexterbits.app.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

  @Id
  @Column(nullable = false, length = 20)
  private String username;

  @Column(nullable = false, length = 200)
  private String password;

  @Column(length = 50)
  private String email;

  @Column(nullable = false, columnDefinition = "boolean")
  private Boolean locked;

  @Column(nullable = false, columnDefinition = "boolean")
  private Boolean disabled;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<UsuarioRol> roles;

  @Override
  public String toString() {
    return "Usuario{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", locked=" + locked + ", disabled=" + disabled + ", roles=" + roles + '}';
  }
}
