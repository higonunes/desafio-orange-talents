package com.orangetalents.desafio.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.domain.Veiculo;
import com.orangetalents.desafio.dto.veiculo.*;
import com.orangetalents.desafio.exceptions.NaoEncontradoException;
import com.orangetalents.desafio.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VeiculoObjetoFipeDTO> listaMarcas(String tipoVeiculo) {
        try {
            VeiculoObjetoFipeDTO[] listaMarcas = ApiService.apiFipe().getForObject("/" + tipoVeiculo + "/marcas", VeiculoObjetoFipeDTO[].class);
            return Arrays.asList(listaMarcas);
        } catch (HttpClientErrorException e) {
            throw new NaoEncontradoException("Não encontrado");
        }
    }

    public VeiculoModeloFipeDTO listaMarcaModelos(String tipoVeiculo, Long idmarca) {
        try {
            return ApiService.apiFipe().getForObject("/" + tipoVeiculo + "/marcas/" + idmarca + "/modelos", VeiculoModeloFipeDTO.class);
        } catch (HttpClientErrorException e) {
            throw new NaoEncontradoException("Não encontrado");
        }
    }

    public List<VeiculoObjetoFipeDTO> listaModeloAnos(String tipoVeiculo, Long idmarca, String idmodelo) {
        try {
            VeiculoObjetoFipeDTO[] listaModeloAnos = ApiService.apiFipe().getForObject("/" + tipoVeiculo + "/marcas/" + idmarca + "/modelos/" + idmodelo + "/anos", VeiculoObjetoFipeDTO[].class);
            return Arrays.asList(listaModeloAnos);
        } catch (HttpClientErrorException e) {
            throw new NaoEncontradoException("Não encontrado");
        }
    }

    public VeiculoFipeDTO getModelo(String tipoVeiculo, Long idmarca, String idmodelo, String idano) {
        try {
            final String url = "/" + tipoVeiculo + "/marcas/" + idmarca + "/modelos/" + idmodelo + "/anos/" + idano;
            Object veiculo = ApiService.apiFipe().getForObject(url, Object.class);
            return new ObjectMapper()
                    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                    .convertValue(veiculo, VeiculoFipeDTO.class);
        } catch (HttpClientErrorException e) {
            throw new NaoEncontradoException("Não encontrado");
        }
    }

    public void insert(VeiculoNovoDTO veiculoNovoDTO) {
        Usuario usuario = LoginService.autenticado();
        Veiculo veiculo = veiculoNovoDTO.toVeiculo();
        veiculo.setUsuario(usuario);
        veiculoRepository.save(veiculo);
    }

    public Page<VeiculoUsuarioDTO> listaVeiculoUsuario(Pageable pageable) {
        Usuario usuario = LoginService.autenticado();
        Page<Veiculo> veiculos = veiculoRepository.findByUsuarioId(usuario.getId(), pageable);
        return veiculos.map(VeiculoUsuarioDTO::new);
    }

    public Page<VeiculoUsuarioDTO> listaVeiculoRodizioAtivo(Pageable pageable) {
        Usuario usuario = LoginService.autenticado();
        Page<Veiculo> veiculos = veiculoRepository.findByRodizioVeiculo(usuario.getId(), RodizioService.digitosFinaisDia(), pageable);
        return veiculos.map(VeiculoUsuarioDTO::new);
    }

}
