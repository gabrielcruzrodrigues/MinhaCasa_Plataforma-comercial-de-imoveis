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
@Profile("prod")
@Slf4j
public class Prod_FirstDataConfig implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImmobileRepository immobileRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = User.builder()
                .id(1L)
                .name("admin")
                .phone("00000000000")
                .whatsapp("00000000000")
                .email("admin@gmail.com")
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

        User paula = User.builder()
                .id(2L)
                .name("Paula Dos Santos")
                .phone("73999991122")
                .whatsapp("73999991234")
                .email("paula@gmail.com")
                .password(this.passwordEncoder.encode("12345678!!"))
                .dateOfBirth(LocalDate.of(1995, 1, 1))
                .role(Set.of("ADMIN"))
                .createdAt(LocalDateTime.now())
                .contractQuantities(5L)
                .favorites(List.of())
                .properties(List.of())
                .facebook("facebook.com/paula")
                .instagram("instagram.com/paula")
                .imageProfile("a1.jpg")
                .active(true)
                .build();

        User pedro = User.builder()
                .id(3L)
                .name("Pedro Rodrigues")
                .phone("73999998841")
                .whatsapp("73999993321")
                .email("pedro@gmail.com")
                .password(this.passwordEncoder.encode("12345678!!"))
                .dateOfBirth(LocalDate.of(1995, 1, 1))
                .role(Set.of("ADMIN"))
                .createdAt(LocalDateTime.now())
                .contractQuantities(5L)
                .favorites(List.of())
                .properties(List.of())
                .facebook("facebook.com/pedro")
                .instagram("instagram.com/pedro")
                .imageProfile("a2.jpg")
                .active(true)
                .build();

        if (userRepository.count() == 0) {
            log.info("=======================================================");
            log.info("Nenhum usuário encontrado, cadastrando usuários padrão.");
            log.info("=======================================================");

            userRepository.save(admin);
            userRepository.save(paula);
            userRepository.save(pedro);
        }

        //Creating immobilies
        List<User> users = this.userRepository.findAll();
        List<String> names = List.of(
                "Apartamento 2 Quartos com Varanda na Zona Sul",
                "Casa Térrea com Quintal Amplo em Bairro Tranquilo",
                "Cobertura com Vista Panorâmica e Piscina Privativa",
                "Studio Mobiliado Próximo ao Centro",
                "Chácara com Área Gourmet e Piscina",
                "Apartamento Alto Padrão com 3 Suítes",
                "Casa de Praia Pé na Areia em Condomínio Fechado",
                "Apartamento Compacto Ideal para Investidores",
                "Sobrado Moderno com Espaço Gourmet",
                "Terreno Plano em Loteamento Fechado",
                "Apartamento Reformado Próximo ao Metrô",
                "Casa com Edícula e Garagem para 4 Carros",
                "Apartamento Garden com Área Privativa",
                "Loft Decorado em Região Nobre",
                "Casa com Piscina e Área Verde",
                "Apartamento com Lazer Completo e Portaria 24h",
                "Imóvel Comercial em Avenida Movimentada",
                "Casa Duplex com Suíte Master e Closet",
                "Apartamento com Vaga de Garagem e Elevador",
                "Residência com Escritório e Jardim Interno"
        );

        List<String> descriptions = List.of(
                "Localizado em área nobre, este apartamento oferece 2 dormitórios, sala ampla com varanda, " +
                        "cozinha planejada e 1 vaga de garagem. Condomínio com piscina, salão de festas e segurança 24h.",
                "Imóvel arejado com 3 quartos, suíte, sala de estar, cozinha espaçosa e quintal com árvores frutíferas. " +
                        "Ideal para famílias que buscam sossego e espaço ao ar livre.",
                "Cobertura duplex com 4 suítes, terraço com piscina e vista para o mar. Acabamento de alto padrão, " +
                        "elevador privativo e 3 vagas de garagem. Condomínio com lazer completo.",
                "Studio moderno, mobiliado e pronto para morar. Cozinha americana, banheiro com box de vidro e " +
                        "ar-condicionado. Excelente localização, próximo a faculdades e transporte público.",
                "Terreno de 1.200m² com casa principal, varanda, churrasqueira, piscina e campo de futebol. Ideal para " +
                        "lazer ou eventos. Fácil acesso pela rodovia e documentação regularizada.",
                "Apartamento de 180m² com 3 suítes, sala integrada, varanda gourmet e cozinha planejada. Condomínio com " +
                        "academia, spa, brinquedoteca e portaria 24h.",
                "Casa térrea com 4 quartos, varanda com rede, jardim tropical e acesso direto à praia. Condomínio com " +
                        "segurança, quadra de tênis e restaurante.",
                "1 dormitório, sala com sacada, cozinha americana e banheiro social. Excelente para aluguel por " +
                        "temporada ou moradia estudantil. Próximo a comércios e metrô.",
                "Sobrado com 3 dormitórios, sendo 1 suíte, sala com pé-direito duplo, cozinha planejada e área gourmet " +
                        "com churrasqueira. Garagem para 2 carros e portão eletrônico.",
                "Lote de 360m² em condomínio com infraestrutura completa: ruas asfaltadas, iluminação, segurança e área " +
                        "de lazer. Pronto para construir.",
                "2 quartos, sala com iluminação natural, cozinha com armários embutidos e banheiro com acabamento em " +
                        "porcelanato. A 5 minutos da estação.",
                "Casa principal com 3 dormitórios e edícula com suíte independente. Quintal espaçoso, ideal para " +
                        "famílias grandes ou renda extra com aluguel.",
                "Apartamento térreo com jardim exclusivo, 2 quartos, sala integrada e cozinha americana. Condomínio " +
                        "com piscina, playground e salão de festas.",
                "Loft com pé-direito alto, projeto de iluminação, móveis planejados e vista para a cidade. Ideal para " +
                        "jovens profissionais ou casais.",
                "Casa térrea com 4 quartos, sala de jantar, cozinha ampla e área externa com piscina, jardim e espaço " +
                        "gourmet. Bairro residencial e tranquilo.",
                "Condomínio clube com piscina, academia, quadra, salão de festas e brinquedoteca. Unidade com 2 " +
                        "quartos, varanda e cozinha planejada.",
                "Salão com 120m², vitrine ampla, banheiro e depósito. Ideal para loja, clínica ou escritório. Grande " +
                        "fluxo de pedestres e veículos.",
                "Casa com 3 quartos, sendo uma suíte master com closet e banheira. Sala com lareira, cozinha planejada " +
                        "e garagem coberta. Bairro arborizado.",
                "2 dormitórios, sala com sacada, cozinha com armários e banheiro social. Prédio com elevador, vaga " +
                        "coberta e portaria eletrônica.",
                "Casa com 3 quartos, escritório com entrada independente, jardim interno com iluminação zenital e " +
                        "cozinha gourmet. Ideal para home office."
        );

        List<String> addresses = List.of(
                "Rua das Palmeiras, 120 - Jardim Europa, São Paulo - SP",
                "Av. Atlântica, 3050 - Copacabana, Rio de Janeiro - RJ",
                "Rua do Sol, 45 - Centro, Salvador - BA",
                "Rua das Acácias, 88 - Lagoa Nova, Natal - RN",
                "Rua Pedro Álvares Cabral, 210 - Aldeota, Fortaleza - CE",
                "Rua das Hortênsias, 12 - Gramado, Gramado - RS",
                "Av. Brasil, 1500 - Setor Oeste, Goiânia - GO",
                "Rua das Laranjeiras, 77 - Cambuí, Campinas - SP",
                "Rua Marquês de Pombal, 300 - Boa Viagem, Recife - PE",
                "Rua das Oliveiras, 101 - Centro, Florianópolis - SC",
                "Av. das Nações, 999 - Asa Sul, Brasília - DF",
                "Rua do Comércio, 56 - Centro, Belo Horizonte - MG",
                "Rua das Gaivotas, 200 - Jurerê Internacional, Florianópolis - SC",
                "Rua São João, 134 - Centro, Porto Alegre - RS",
                "Av. Independência, 890 - Centro, Curitiba - PR",
                "Rua das Mangueiras, 45 - Pituba, Salvador - BA",
                "Rua do Cedro, 78 - Jardim das Américas, Cuiabá - MT",
                "Av. Beira Mar, 400 - Meireles, Fortaleza - CE",
                "Rua das Rosas, 22 - Jardim Botânico, Rio de Janeiro - RJ",
                "Rua do Cajueiro, 310 - Jequiezinho, Jequié - BA"
        );

        List<String> states = List.of(
                "SP",
                "RJ",
                "BA",
                "RN",
                "CE",
                "RS",
                "GO",
                "SP",
                "PE",
                "SC",
                "DF",
                "MG",
                "SC",
                "RS",
                "PR",
                "BA",
                "MT",
                "CE",
                "RJ",
                "BA"
        );

        List<String> cities = List.of(
                "São Paulo",
                "Rio de Janeiro",
                "Salvador",
                "Natal",
                "Fortaleza",
                "Gramado",
                "Goiânia",
                "Campinas",
                "Recife",
                "Florianópolis",
                "Brasília",
                "Belo Horizonte",
                "Florianópolis",
                "Porto Alegre",
                "Curitiba",
                "Salvador",
                "Cuiabá",
                "Fortaleza",
                "Rio de Janeiro",
                "Jequié"
        );

        List<String> neighborhoods = List.of(
                "Jardim Europa",
                "Copacabana",
                "Centro",
                "Lagoa Nova",
                "Aldeota",
                "Gramado",
                "Setor Oeste",
                "Cambuí",
                "Boa Viagem",
                "Centro",
                "Asa Sul",
                "Centro",
                "Jurerê Internacional",
                "Centro",
                "Centro",
                "Pituba",
                "Jardim das Américas",
                "Meireles",
                "Jardim Botânico",
                "Jequiezinho"
        );

        for(int i = 0; i < 20; i++) {
            immobileRepository.save(
                    Immobile.builder()
                            .name(names.get(i))
                            .description(descriptions.get(i))
                            .address(addresses.get(i))
                            .city(cities.get(i))
                            .neighborhood(neighborhoods.get(i))
                            .state(states.get(i))
                            .garage(true)
                            .quantityBedrooms(1)
                            .quantityRooms(6)
                            .IPTU(new BigDecimal("1200.00"))
                            .price(new BigDecimal("850000.00"))
                            .suite(true)
                            .usefulArea(200.0)
                            .totalArea(300.0)
                            .quantityBathrooms(2)
                            .integrity(IntegrityEnum.NEW)
                            .sellerType(SellerTypeEnum.OWNER)
                            .age(AgeEnum.UP_TO_1_YEARS)
                            .category(CategoryEnum.SELL)
                            .createdAt(LocalDateTime.now())
                            .type(TypeEnum.HOUSE)
                            .garden(true)
                            .virtualTour(false)
                            .videos(false)
                            .beach(true)
                            .disabledAccess(false)
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
                            .bathroomInTheRoom(false)
                            .internet(false)
                            .partyRoom(true)
                            .airConditioning(false)
                            .americanKitchen(true)
                            .hydromassage(true)
                            .fireplace(true)
                            .privatePool(false)
                            .electronicGate(false)
                            .serviceArea(true)
                            .pub(true)
                            .closet(true)
                            .office(true)
                            .yard(true)
                            .alarmSystem(true)
                            .balcony(true)
                            .concierge24Hour(true)
                            .walledArea(true)
                            .dogAllowed(true)
                            .catAllowed(true)
                            .cameras(false)
                            .furnished(true)
                            .seaView(true)
                            .gatedCommunity(true)
                            .active(true)
                            .user(paula)
                            .favoriteUser(List.of())
                            .files(List.of("a1.jpg", "a2.jpg", "a3.webp", "a4.jpg"))
                            .build()
            );
        }

//        if (userRepository.findById(paula.getId()).isPresent()) {
//            immobileRepository.save(
//                    Immobile.builder()
//                            .name("Casa no campo")
//                            .description("Uma bela casa de campo com um belo jardim e garagem.")
//                            .address("Rua das Flores, 123")
//                            .city("Florianópolis")
//                            .neighborhood("Jurerê Internacional")
//                            .state("Santa Catarina")
//                            .garage(true)
//                            .quantityBedrooms(1)
//                            .quantityRooms(6)
//                            .IPTU(new BigDecimal("1200.00"))
//                            .price(new BigDecimal("850000.00"))
//                            .suite(true)
//                            .usefulArea(200.0)
//                            .totalArea(300.0)
//                            .quantityBathrooms(2)
//                            .integrity(IntegrityEnum.NEW)
//                            .sellerType(SellerTypeEnum.OWNER)
//                            .age(AgeEnum.UP_TO_1_YEARS)
//                            .category(CategoryEnum.SELL)
//                            .createdAt(LocalDateTime.now())
//                            .type(TypeEnum.HOUSE)
//                            .garden(true)
//                            .virtualTour(false)
//                            .videos(false)
//                            .beach(true)
//                            .disabledAccess(false)
//                            .playground(true)
//                            .grill(true)
//                            .energyGenerator(false)
//                            .closeToTheCenter(true)
//                            .elevator(false)
//                            .pool(true)
//                            .frontDesk(true)
//                            .multiSportsCourt(false)
//                            .gym(true)
//                            .steamRoom(false)
//                            .cableTV(true)
//                            .heating(true)
//                            .cabinetsInTheKitchen(true)
//                            .bathroomInTheRoom(false)
//                            .internet(false)
//                            .partyRoom(true)
//                            .airConditioning(false)
//                            .americanKitchen(true)
//                            .hydromassage(true)
//                            .fireplace(true)
//                            .privatePool(false)
//                            .electronicGate(false)
//                            .serviceArea(true)
//                            .pub(true)
//                            .closet(true)
//                            .office(true)
//                            .yard(true)
//                            .alarmSystem(true)
//                            .balcony(true)
//                            .concierge24Hour(true)
//                            .walledArea(true)
//                            .dogAllowed(true)
//                            .catAllowed(true)
//                            .cameras(false)
//                            .furnished(true)
//                            .seaView(true)
//                            .gatedCommunity(true)
//                            .active(true)
//                            .user(paula)
//                            .favoriteUser(List.of())
//                            .files(List.of("a1.jpg", "a2.jpg", "a3.webp", "a4.jpg"))
//                            .build()
//            );
//        }

//        if (userRepository.findById(paula.getId()).isPresent()) {
//            immobileRepository.save(
//                    Immobile.builder()
//                            .name("Casa na floresta")
//                            .description("Uma bela casa na floresta cercada pela natureza.")
//                            .address("Rua E, 135")
//                            .city("Ilhéus")
//                            .neighborhood("Jurerê Internacional")
//                            .state("Bahia")
//                            .garage(true)
//                            .quantityBedrooms(2)
//                            .quantityRooms(5)
//                            .IPTU(new BigDecimal("1200.00"))
//                            .price(new BigDecimal("950000.00"))
//                            .suite(false)
//                            .usefulArea(200.0)
//                            .totalArea(300.0)
//                            .quantityBathrooms(3)
//                            .integrity(IntegrityEnum.NEW)
//                            .sellerType(SellerTypeEnum.OWNER)
//                            .age(AgeEnum.UP_TO_1_YEARS)
//                            .category(CategoryEnum.SELL)
//                            .createdAt(LocalDateTime.now())
//                            .type(TypeEnum.HOUSE)
//                            .garden(true)
//                            .virtualTour(false)
//                            .videos(false)
//                            .beach(true)
//                            .disabledAccess(true)
//                            .playground(true)
//                            .grill(true)
//                            .energyGenerator(false)
//                            .closeToTheCenter(true)
//                            .elevator(false)
//                            .pool(true)
//                            .frontDesk(true)
//                            .multiSportsCourt(false)
//                            .gym(true)
//                            .steamRoom(false)
//                            .cableTV(true)
//                            .heating(true)
//                            .cabinetsInTheKitchen(true)
//                            .bathroomInTheRoom(true)
//                            .internet(false)
//                            .partyRoom(true)
//                            .airConditioning(true)
//                            .americanKitchen(true)
//                            .hydromassage(true)
//                            .fireplace(false)
//                            .privatePool(false)
//                            .electronicGate(true)
//                            .serviceArea(true)
//                            .pub(false)
//                            .closet(true)
//                            .office(false)
//                            .yard(true)
//                            .alarmSystem(true)
//                            .balcony(true)
//                            .concierge24Hour(true)
//                            .walledArea(true)
//                            .dogAllowed(false)
//                            .catAllowed(false)
//                            .cameras(true)
//                            .furnished(true)
//                            .seaView(false)
//                            .gatedCommunity(false)
//                            .active(true)
//                            .user(paula)
//                            .favoriteUser(List.of())
//                            .files(List.of("b1.jpg", "b2.jpg", "b3.webp", "b4.jpg"))
//                            .build()
//            );
//        }

//        if (userRepository.findById(pedro.getId()).isPresent()) {
//            immobileRepository.save(
//                    Immobile.builder()
//                            .name("Chalé de inverno")
//                            .description("Um belo chalé de inverno, todo feito em madeira.")
//                            .address("Rua das Flores, 123")
//                            .city("Florianópolis")
//                            .neighborhood("Jurerê Internacional")
//                            .state("Santa Catarina")
//                            .garage(true)
//                            .quantityBedrooms(3)
//                            .quantityRooms(4)
//                            .IPTU(new BigDecimal("1200.00"))
//                            .price(new BigDecimal("650000.00"))
//                            .suite(true)
//                            .usefulArea(200.0)
//                            .totalArea(300.0)
//                            .quantityBathrooms(3)
//                            .integrity(IntegrityEnum.NEW)
//                            .sellerType(SellerTypeEnum.OWNER)
//                            .age(AgeEnum.UP_TO_1_YEARS)
//                            .category(CategoryEnum.SELL)
//                            .createdAt(LocalDateTime.now())
//                            .type(TypeEnum.HOUSE)
//                            .garden(true)
//                            .virtualTour(true)
//                            .videos(false)
//                            .beach(true)
//                            .disabledAccess(true)
//                            .playground(true)
//                            .grill(true)
//                            .energyGenerator(false)
//                            .closeToTheCenter(true)
//                            .elevator(false)
//                            .pool(true)
//                            .frontDesk(true)
//                            .multiSportsCourt(false)
//                            .gym(true)
//                            .steamRoom(false)
//                            .cableTV(true)
//                            .heating(true)
//                            .cabinetsInTheKitchen(true)
//                            .bathroomInTheRoom(true)
//                            .internet(true)
//                            .partyRoom(true)
//                            .airConditioning(true)
//                            .americanKitchen(true)
//                            .hydromassage(true)
//                            .fireplace(true)
//                            .privatePool(false)
//                            .electronicGate(true)
//                            .serviceArea(true)
//                            .pub(false)
//                            .closet(true)
//                            .office(true)
//                            .yard(true)
//                            .alarmSystem(true)
//                            .balcony(true)
//                            .concierge24Hour(true)
//                            .walledArea(true)
//                            .dogAllowed(true)
//                            .catAllowed(true)
//                            .cameras(true)
//                            .furnished(true)
//                            .seaView(true)
//                            .gatedCommunity(true)
//                            .active(true)
//                            .user(pedro)
//                            .favoriteUser(List.of())
//                            .files(List.of("c1.webp", "c2.webp", "c3.webp", "c4.jpg"))
//                            .build()
//            );
//        }

//        if (userRepository.findById(pedro.getId()).isPresent()) {
//            immobileRepository.save(
//                    Immobile.builder()
//                            .name("Apartamento Mobilhado")
//                            .description("Apartamento mobilhado no centro de SP.")
//                            .address("Guarulhos, 123")
//                            .city("Florianópolis")
//                            .neighborhood("Jurerê Internacional")
//                            .state("São Paulo")
//                            .garage(true)
//                            .quantityBedrooms(1)
//                            .quantityRooms(3)
//                            .IPTU(new BigDecimal("1200.00"))
//                            .price(new BigDecimal("550000.00"))
//                            .suite(true)
//                            .usefulArea(200.0)
//                            .totalArea(300.0)
//                            .quantityBathrooms(3)
//                            .integrity(IntegrityEnum.NEW)
//                            .sellerType(SellerTypeEnum.OWNER)
//                            .age(AgeEnum.UP_TO_1_YEARS)
//                            .category(CategoryEnum.SELL)
//                            .createdAt(LocalDateTime.now())
//                            .type(TypeEnum.HOUSE)
//                            .garden(true)
//                            .virtualTour(true)
//                            .videos(false)
//                            .beach(true)
//                            .disabledAccess(true)
//                            .playground(true)
//                            .grill(true)
//                            .energyGenerator(false)
//                            .closeToTheCenter(true)
//                            .elevator(false)
//                            .pool(true)
//                            .frontDesk(true)
//                            .multiSportsCourt(false)
//                            .gym(true)
//                            .steamRoom(false)
//                            .cableTV(true)
//                            .heating(true)
//                            .cabinetsInTheKitchen(true)
//                            .bathroomInTheRoom(true)
//                            .internet(true)
//                            .partyRoom(true)
//                            .airConditioning(true)
//                            .americanKitchen(true)
//                            .hydromassage(true)
//                            .fireplace(true)
//                            .privatePool(false)
//                            .electronicGate(true)
//                            .serviceArea(true)
//                            .pub(false)
//                            .closet(true)
//                            .office(true)
//                            .yard(true)
//                            .alarmSystem(true)
//                            .balcony(true)
//                            .concierge24Hour(true)
//                            .walledArea(true)
//                            .dogAllowed(true)
//                            .catAllowed(true)
//                            .cameras(true)
//                            .furnished(true)
//                            .seaView(true)
//                            .gatedCommunity(true)
//                            .active(true)
//                            .user(pedro)
//                            .favoriteUser(List.of())
//                            .files(List.of("d1.png", "d2.png", "d3.jpg", "d4.png"))
//                            .build()
//            );
//        }
    }
}
