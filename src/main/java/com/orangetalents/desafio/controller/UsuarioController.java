package com.orangetalents.desafio.controller;

import com.orangetalents.desafio.dto.usuario.UsuarioNovoDTO;
import com.orangetalents.desafio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> inserirUsuario(@RequestBody @Valid UsuarioNovoDTO usuarioNovoDTO) {
        usuarioService.inserirUsuario(usuarioNovoDTO);
        return ResponseEntity.status(201).build();
    }


}
