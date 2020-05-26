package com.heliton.repository;

// JPA //
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Model //
import com.heliton.model.Usuario;

@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}