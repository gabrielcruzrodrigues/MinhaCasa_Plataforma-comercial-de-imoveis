package com.gabriel.minhacasa.config;

import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.GenderEnum;
import com.gabriel.minhacasa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Profile("prod")
@Slf4j
public class Prod_FirstDataConfig implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=======================================================");
        log.info("Nenhum usuário encontrado, cadastrando usuários padrão.");
        log.info("=======================================================");

        User user = User.builder()
                .id(1L)
                .name("admin")
                .phone("00000000000")
                .whatsapp("00000000000")
                .email("adm@gmail.com")
                .password(this.passwordEncoder.encode("12345678a!"))
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .state("São Paulo")
                .gender(GenderEnum.MALE)
                .city("São Paulo")
                .role(Set.of("ROLE_USER", "ROLE_ADMIN"))
                .createdAt(LocalDateTime.now())
                .contractQuantities(5L)
                .imageProfile("Gabriel Cruz Rodrigues_c23bb95b-e.jpg")
                .favorites(List.of())
                .properties(List.of())
                .facebook("admin")
                .instagram("admin")
                .active(true)
                .build();

        if (userRepository.count() == 0) {
            userRepository.save(user);
        }
    }
}
