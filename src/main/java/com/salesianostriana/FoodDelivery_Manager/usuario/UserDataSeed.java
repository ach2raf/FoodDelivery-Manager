package com.salesianostriana.FoodDelivery_Manager.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
import com.salesianostriana.FoodDelivery_Manager.repository.RepartidorRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataSeed {

    private final UserRepository userRepository; 
    private final RepartidorRepository repartidorRepo; 
    private final PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            
            User admin = User.builder()
                    .username("admin")
                    .password(encoder.encode("admin"))
                    .rol(UserRole.ADMIN)
                    .build();
            
            userRepository.save(admin);

            Repartidor operador = Repartidor.builder()
                    .username("user")
                    .password(encoder.encode("user"))
                    .rol(UserRole.OPERADOR)
                    .nombre("Mario")
                    .zona("Centro")
                    .build();

            repartidorRepo.save(operador);
            
        }
    }
}