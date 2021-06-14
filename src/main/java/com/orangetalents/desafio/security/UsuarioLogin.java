package com.orangetalents.desafio.security;

import com.orangetalents.desafio.domain.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UsuarioLogin extends Usuario implements UserDetails {

    public UsuarioLogin(Usuario usuario) {
        super.setNome(usuario.getNome());
        super.setCpf(usuario.getCpf());
        super.setEmail(usuario.getEmail());
        super.setHashSenha(usuario.getHashSenha());
        super.setId(usuario.getId());
        super.setDataNascimento(usuario.getDataNascimento());
        super.setPerfis(usuario.getPerfis());
        super.setVeiculos(usuario.getVeiculos());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getPerfis().stream().map(x -> new SimpleGrantedAuthority(x.toString())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getHashSenha();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
