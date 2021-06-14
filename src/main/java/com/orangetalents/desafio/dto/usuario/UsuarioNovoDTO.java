package com.orangetalents.desafio.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orangetalents.desafio.domain.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UsuarioNovoDTO {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String senha;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
    private String cpf;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    public Usuario toUsuario(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new Usuario(nome, cpf, email, dataNascimento, bCryptPasswordEncoder.encode(senha));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
