package com.orangetalents.desafio;

import com.orangetalents.desafio.domain.Usuario;
import com.orangetalents.desafio.domain.Veiculo;
import com.orangetalents.desafio.enums.Perfil;
import com.orangetalents.desafio.repository.UsuarioRepository;
import com.orangetalents.desafio.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class DesafioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }


}
