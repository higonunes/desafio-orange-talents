package com.orangetalents.desafio.repository;

import com.orangetalents.desafio.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailOrCpf(String email, String cpf);

    Optional<Usuario> findByEmail(String email);
}
