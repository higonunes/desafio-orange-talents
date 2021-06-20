package com.orangetalents.desafio.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.domain.Veiculo;
import com.orangetalents.desafio.dto.veiculo.*;
import com.orangetalents.desafio.exceptions.NaoEncontradoException;
import com.orangetalents.desafio.repository.VeiculoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private FipeApiService fipeApiService;

    public List<VeiculoObjetoFipeDTO> listaMarcas(String tipoVeiculo) {
        try {
            return fipeApiService.getListaMarcaPorVeiculo(tipoVeiculo);
        } catch (FeignException e) {
            throw new NaoEncontradoException("Não encontrado");
        }
    }

    public VeiculoModeloFipeDTO listaMarcaModelos(String tipoVeiculo, Long idmarca) {
        try {
            return fipeApiService.getListaMarcaModelos(tipoVeiculo, idmarca);
        } catch (FeignException e) {
            throw new NaoEncontradoException("Não encontrado");
        }
    }

    public List<VeiculoObjetoFipeDTO> listaModeloAnos(String tipoVeiculo, Long idmarca, Long idmodelo) {
        try {
            return fipeApiService.getListaModeloAnos(tipoVeiculo, idmarca, idmodelo);
        } catch (FeignException e) {
            throw new NaoEncontradoException("Não encontrado");
        }
    }

    public VeiculoFipeDTO getModelo(String tipoVeiculo, Long idmarca, Long idmodelo, String idano) {
        try {
            Object veiculo = fipeApiService.getModelo(tipoVeiculo, idmarca, idmodelo, idano);
            return new ObjectMapper()
                    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                    .convertValue(veiculo, VeiculoFipeDTO.class);
        } catch (FeignException e) {
            throw new NaoEncontradoException("Não encontrado");
        }
    }

    public VeiculoUsuarioDTO insert(VeiculoNovoDTO veiculoNovoDTO) {
        Usuario usuario = LoginService.autenticado();
        Veiculo veiculo = veiculoNovoDTO.toVeiculo();
        veiculo.setUsuario(usuario);
        veiculoRepository.save(veiculo);
        return new VeiculoUsuarioDTO(veiculo);
    }

    public Page<VeiculoUsuarioDTO> listaVeiculoUsuario(Pageable pageable) {
        Usuario usuario = LoginService.autenticado();
        Page<Veiculo> veiculos = veiculoRepository.findByUsuarioId(usuario.getId(), pageable);
        return veiculos.map(VeiculoUsuarioDTO::new);
    }

    public Page<VeiculoUsuarioDTO> listaVeiculoRodizioAtivo(Pageable pageable) {
        Usuario usuario = LoginService.autenticado();
        Page<Veiculo> veiculos = veiculoRepository.findByRodizioVeiculo(usuario.getId(), RodizioService.digitosFinaisParaHoje(), pageable);
        return veiculos.map(VeiculoUsuarioDTO::new);
    }

    public VeiculoUsuarioDTO getVeiculo(Long idveiculo) {
        Veiculo veiculo = veiculoRepository.findById(idveiculo).orElseThrow(
                () -> new NaoEncontradoException("Veículo não encontrado")
        );
        return new VeiculoUsuarioDTO(veiculo);
    }

}
