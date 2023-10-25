package com.dexterbits.app.domain.service;

import com.dexterbits.app.persistence.UsuarioRepository;
import com.dexterbits.app.persistence.entity.Usuario;
import com.dexterbits.app.persistence.entity.UsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Autowired
  public UserSecurityService(UsuarioRepository userRepository) {
    this.usuarioRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = this.usuarioRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario: " + username + "No funciona"));

    System.out.println(usuario);

    String[] roles = usuario.getRoles().stream().map(UsuarioRol::getRole).toArray(String[]::new);

    return User.builder().username(usuario.getUsername()).password(usuario.getPassword())
        .authorities(this.grantedAuthorities(roles)).accountLocked(usuario.getLocked())
        .disabled(usuario.getDisabled()).build();
  }

  private String[] getAuthorities(String role) {
    if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
      return new String[] {"especific_role"};
    }

    return new String[] {};
  }

  private List<GrantedAuthority> grantedAuthorities(String[] roles) {

    List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

      for (String authority : this.getAuthorities(role)) {
        authorities.add(new SimpleGrantedAuthority(authority));

      }

    }

    return authorities;
  }

}
