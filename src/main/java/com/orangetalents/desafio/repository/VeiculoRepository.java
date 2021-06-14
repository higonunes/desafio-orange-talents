package com.orangetalents.desafio.repository;

import com.orangetalents.desafio.domain.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Page<Veiculo> findByUsuarioId(Long id, Pageable pageable);

    @Query(value = "SELECT * FROM VEICULO V WHERE V.USUARIO_ID = ?1 and SUBSTR(V.ANO_MODELO, 4) in ?2", nativeQuery = true)
    Page<Veiculo> findByRodizioVeiculo(Long id, List<String> ultimoDigitoAno, Pageable pageable);

}
