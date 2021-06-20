package com.orangetalents.desafio.dto.usuario;

import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.dto.veiculo.VeiculoUsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioVeicuosDTO {

    private UsuarioDTO usuario;
    private List<VeiculoUsuarioDTO> veiculos;

    public UsuarioVeicuosDTO(Usuario usuario) {
        this.usuario = new UsuarioDTO(usuario);
        this.veiculos = usuario.getVeiculos().stream().map(VeiculoUsuarioDTO::new).collect(Collectors.toList());
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<VeiculoUsuarioDTO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoUsuarioDTO> veiculos) {
        this.veiculos = veiculos;
    }
}
