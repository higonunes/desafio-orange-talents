package com.orangetalents.desafio.security;

import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.dto.usuario.UsuarioDTO;
import com.orangetalents.desafio.dto.usuario.UsuarioLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> login(@RequestBody @Valid UsuarioLoginDTO login) {
        UsernamePasswordAuthenticationToken dadosLogin = login.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "Bearer " + token);
            UsuarioDTO usuario = new UsuarioDTO((Usuario) authentication.getPrincipal());
            return new ResponseEntity<UsuarioDTO>(usuario, responseHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
