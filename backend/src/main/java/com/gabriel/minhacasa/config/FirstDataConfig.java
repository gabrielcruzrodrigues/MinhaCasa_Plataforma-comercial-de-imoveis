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
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirstDataConfig implements ApplicationRunner {
    private final UserRepository userRepository;
    private final ImmobileRepository immobileRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=======================================================");
        log.info("Nenhum usuário encontrado, cadastrando usuários padrão.");
        log.info("=======================================================");

        User user = User.builder()
                .id(1L)
                .name("João da Silva")
                .phone("11987654321")
                .whatsapp("11987654321")
                .email("adm@gmail.com")
                .password("12345678a!")
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
                .facebook("joao.silva")
                .instagram("joao.silva")
                .active(true)
                .build();

//        userRepository.save(user);

        for (int i = 0; i <= 100; i++) {
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
