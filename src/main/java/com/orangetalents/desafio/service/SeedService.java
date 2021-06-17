package com.orangetalents.desafio.service;

import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.domain.Veiculo;
import com.orangetalents.desafio.enums.Perfil;
import com.orangetalents.desafio.repository.UsuarioRepository;
import com.orangetalents.desafio.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Profile(value = {"test", "dev"})
public class SeedService implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = new Usuario("Higo", "05013762304", "higo.sousaa@gmail.com", new Date(), bCryptPasswordEncoder.encode("12345"));
        usuario.addPerfil(Perfil.USUARIO);
        usuario.addPerfil(Perfil.ADMIN);
        usuarioRepository.save(usuario);

        Veiculo veiculo1 = new Veiculo("BMW", "120iA Sport 2.0/ActiveFlex 16V Aut.", 2011);
        veiculo1.setUsuario(usuario);
        veiculoRepository.save(veiculo1);

        Veiculo veiculo2 = new Veiculo("VW - VolksWagen", "AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut", 2013);
        veiculo2.setUsuario(usuario);
        veiculoRepository.save(veiculo2);

        Veiculo veiculo3 = new Veiculo("Fiat", "Fiorino Pick-Up 1.0", 2015);
        veiculo3.setUsuario(usuario);
        veiculoRepository.save(veiculo3);

        Veiculo veiculo4 = new Veiculo("GM - Chevrolet", "ONIX HATCH LT 1.4 8V FlexPower 5p Aut.", 2017);
        veiculo4.setUsuario(usuario);
        veiculoRepository.save(veiculo4);

        Veiculo veiculo5 = new Veiculo("Hyundai", "HB20 1.0 Flex 12V Mec", 2019);
        veiculo5.setUsuario(usuario);
        veiculoRepository.save(veiculo5);
    }
}
