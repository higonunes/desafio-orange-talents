package com.orangetalents.desafio.controller;

import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.dto.usuario.UsuarioNovoDTO;
import com.orangetalents.desafio.dto.usuario.UsuarioVeicuosDTO;
import com.orangetalents.desafio.enums.Perfil;
import com.orangetalents.desafio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> inserirUsuario(@RequestBody @Valid UsuarioNovoDTO usuarioNovoDTO) {
        usuarioService.inserirUsuario(usuarioNovoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{idusuario}")
    public ResponseEntity<UsuarioVeicuosDTO> getUsuarioVeiculos(@PathVariable("idusuario") Long idusuario) {
        UsuarioVeicuosDTO usuarioVeicuosDTO = usuarioService.getUsuario(idusuario);
        return ResponseEntity.ok(usuarioVeicuosDTO);
    }

}
