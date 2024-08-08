package com.gabriel.minhacasa.config;

import com.gabriel.minhacasa.domain.Immobile;
import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.domain.enums.*;
import com.gabriel.minhacasa.repository.ImmobileRepository;
import com.gabriel.minhacasa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Profile("dev")
@Slf4j
public class Dev_FirstDataConfig implements ApplicationRunner {
    private final UserRepository userRepository;
    private final ImmobileRepository immobileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = User.builder()
            .id(1L)
            .name("admin")
            .phone("00000000000")
            .whatsapp("00000000000")
            .email("adm@gmail.com")
            .password(this.passwordEncoder.encode("12345678a!"))
            .dateOfBirth(LocalDate.of(1990, 1, 1))
            .role(Set.of("ADMIN"))
            .createdAt(LocalDateTime.now())
            .contractQuantities(5L)
            .favorites(List.of())
            .properties(List.of())
            .facebook("admin")
            .instagram("admin")
            .active(true)
            .build();

        if (userRepository.count() == 0) {
            log.info("=======================================================");
            log.info("Nenhum usuário encontrado, cadastrando usuários padrão.");
            log.info("=======================================================");
            userRepository.save(user);
        }

        if (userRepository.findById(1L).isPresent()) {
            for (int i = 0; i <= 20; i++) {
                log.info("=======================================================");
                log.info("Nenhum usuário encontrado, cadastrando imóveis padrão.");
                log.info("=======================================================");

                immobileRepository.save(
                    Immobile.builder()
                        .name("Casa de Praia")
                        .description("Uma bela casa de praia com vista para o mar.")
                        .address("Rua das Flores, 123")
                        .city("Florianópolis")
                        .neighborhood("Jurerê Internacional")
                        .state("Santa Catarina")
                        .garage(true)
                        .quantityBedrooms(4)
                        .quantityRooms(6)
                        .IPTU(new BigDecimal("1200.00"))
                        .price(new BigDecimal("850000.00"))
                        .suite(true)
                        .usefulArea(200.0)
                        .totalArea(300.0)
                        .quantityBathrooms(3)
                        .integrity(IntegrityEnum.NEW)
                        .sellerType(SellerTypeEnum.OWNER)
                        .age(AgeEnum.UP_TO_1_YEARS)
                        .category(CategoryEnum.SELL)
                        .createdAt(LocalDateTime.now())
                        .type(TypeEnum.HOUSE)
                        .garden(true)
                        .virtualTour(true)
                        .videos(false)
                        .beach(true)
                        .disabledAccess(true)
                        .playground(true)
                        .grill(true)
                        .energyGenerator(false)
                        .closeToTheCenter(true)
                        .elevator(false)
                        .pool(true)
                        .frontDesk(true)
                        .multiSportsCourt(false)
                        .gym(true)
                        .steamRoom(false)
                        .cableTV(true)
                        .heating(true)
                        .cabinetsInTheKitchen(true)
                        .bathroomInTheRoom(true)
                        .internet(true)
                        .partyRoom(true)
                        .airConditioning(true)
                        .americanKitchen(true)
                        .hydromassage(true)
                        .fireplace(true)
                        .privatePool(false)
                        .electronicGate(true)
                        .serviceArea(true)
                        .pub(false)
                        .closet(true)
                        .office(true)
                        .yard(true)
                        .alarmSystem(true)
                        .balcony(true)
                        .concierge24Hour(true)
                        .walledArea(true)
                        .dogAllowed(true)
                        .catAllowed(true)
                        .cameras(true)
                        .furnished(true)
                        .seaView(true)
                        .gatedCommunity(true)
                        .active(true)
                        .user(user)
                        .favoriteUser(List.of())
                        .files(List.of("teste.jpg"))
                        .build()
                );
            }
        }
    }
}
