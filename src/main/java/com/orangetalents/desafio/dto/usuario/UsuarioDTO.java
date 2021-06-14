package com.orangetalents.desafio.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.enums.Perfil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO {

    private Long id;
    private String nome, cpf, email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private Set<Perfil> perfis = new HashSet<>();

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        this.perfis = usuario.getPerfis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }
}
