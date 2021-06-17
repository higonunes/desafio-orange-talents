package com.orangetalents.desafio.service;

import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.dto.usuario.UsuarioNovoDTO;
import com.orangetalents.desafio.enums.Perfil;
import com.orangetalents.desafio.exceptions.UsuarioCadastradoException;
import com.orangetalents.desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void inserirUsuario(UsuarioNovoDTO usuarioNovoDTO) {
        verificaEmailCpf(usuarioNovoDTO.getEmail(), usuarioNovoDTO.getCpf());
        Usuario usuario = usuarioNovoDTO.toUsuario();
        usuario.setHashSenha(bCryptPasswordEncoder.encode(usuarioNovoDTO.getSenha()));
        usuario.addPerfil(Perfil.USUARIO);
        usuarioRepository.save(usuario);
    }

    private void verificaEmailCpf(String email, String cpf) {
        if (usuarioRepository.findByEmailOrCpf(email, cpf).isPresent()) {
            throw new UsuarioCadastradoException("Email ou CPF já cadastrado");
        }
    }
}
