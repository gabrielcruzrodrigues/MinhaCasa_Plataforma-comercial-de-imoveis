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
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@Profile("dev")
@Slf4j
public class Dev_FirstDataConfig implements ApplicationRunner {
    private final UserRepository userRepository;
    private final ImmobileRepository immobileRepository;
    private final PasswordEncoder passwordEncoder;

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

    List<List<String>> immobiliesImages = List.of(
            List.of("a1.jpg", "a2.jpg", "a3.jpg", "a4.jpg", "a5.jpg", "a6.jpg", "a7.jpg"),
            List.of("b1.jpg", "b2.jpg", "b3.jpg", "b4.jpg", "b5.jpg", "b6.jpg", "b7.jpg"),
            List.of("c1.jpg", "c2.jpg", "c3.jpg", "c4.jpg", "c5.jpg", "c6.jpg", "c7.jpg"),
            List.of("d1.jpg", "d2.jpg", "d3.jpg", "d4.jpg", "d5.jpg", "d6.jpg", "d7.jpg"),
            List.of("e1.jpg", "e2.jpg", "e3.jpg", "e4.jpg", "e5.jpg", "e6.jpg", "e7.jpg"),
            List.of("f1.jpg", "f2.jpg", "f3.jpg", "f4.jpg", "f5.jpg", "f6.jpg", "f7.jpg"),
            List.of("g1.jpg", "g2.jpg", "g3.jpg", "g4.jpg", "g5.jpg", "g6.jpg", "g7.jpg"),
            List.of("h1.jpg", "h2.jpg", "h3.jpg", "h4.jpg", "h5.jpg", "h6.jpg", "h7.jpg"),
            List.of("i1.jpg", "i2.jpg", "i3.jpg", "i4.jpg", "i5.jpg", "i6.jpg", "i7.jpg"),
            List.of("j1.jpg", "j2.jpg", "j3.jpg", "j4.jpg", "j5.jpg", "j6.jpg", "j7.jpg"),
            List.of("k1.jpg", "k2.jpg", "k3.jpg", "k4.jpg", "k5.jpg", "k6.jpg", "k7.jpg"),
            List.of("l1.jpg", "l2.jpg", "l3.jpg", "l4.jpg", "l5.jpg", "l6.jpg", "l7.jpg"),
            List.of("m1.jpg", "m2.jpg", "m3.jpg", "m4.jpg", "m5.jpg", "m6.jpg", "m7.jpg"),
            List.of("n1.jpg", "n2.jpg", "n3.jpg", "n4.jpg", "n5.jpg", "n6.jpg", "n7.jpg"),
            List.of("o1.jpg", "o2.jpg", "o3.jpg", "o4.jpg", "o5.jpg", "o6.jpg", "o7.jpg"),
            List.of("p1.jpg", "p2.jpg", "p3.jpg", "p4.jpg", "p5.jpg", "p6.jpg", "p7.jpg"),
            List.of("q1.jpg", "q2.jpg", "q3.jpg", "q4.jpg", "q5.jpg", "q6.jpg", "q7.jpg"),
            List.of("r1.jpg", "r2.jpg", "r3.jpg", "r4.jpg", "r5.jpg", "r6.jpg", "r7.jpg"),
            List.of("s1.jpg", "s2.jpg", "s3.jpg", "s4.jpg", "s5.jpg", "s6.jpg", "s7.jpg"),
            List.of("t1.jpg", "t2.jpg", "t3.jpg", "t4.jpg", "t5.jpg", "t6.jpg", "t7.jpg")
    );

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
                .active(this.generateBooleanValue())
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
                .active(this.generateBooleanValue())
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
                .active(this.generateBooleanValue())
                .build();

        if (userRepository.count() == 0) {
            log.info("=======================================================");
            log.info("Nenhum usuário encontrado, cadastrando usuários padrão.");
            log.info("=======================================================");

            userRepository.save(admin);
            userRepository.save(paula);
            userRepository.save(pedro);
        }

        if (immobileRepository.count() == 0) {
            log.info("=======================================================");
            log.info("Nenhum imóvel encontrado, cadastrando imóveis padrão.");
            log.info("=======================================================");

            for (int i = 0; i < 18; i++) {
                Immobile immobile = Immobile.builder()
                        .name(names.get(i))
                        .description(descriptions.get(i))
                        .address(addresses.get(i))
                        .city(cities.get(i))
                        .neighborhood(neighborhoods.get(i))
                        .state(states.get(i))
                        .garage(this.generateBooleanValue())
                        .quantityBedrooms(this.generateQtyLocations("bedrooms"))
                        .quantityRooms(this.generateQtyLocations("rooms"))
                        .IPTU(this.generatePriceInBigDecimalType("iptu"))
                        .price(this.generatePriceInBigDecimalType("price"))
                        .suite(this.generateBooleanValue())
                        .usefulArea(200.0)
                        .totalArea(300.0)
                        .quantityBathrooms(this.generateQtyLocations("bathrooms"))
                        .integrity(IntegrityEnum.NEW)
                        .sellerType(SellerTypeEnum.OWNER)
                        .age(AgeEnum.UP_TO_1_YEARS)
                        .category(CategoryEnum.SELL)
                        .createdAt(LocalDateTime.now())
                        .type(TypeEnum.HOUSE)
                        .garden(this.generateBooleanValue())
                        .virtualTour(this.generateBooleanValue())
                        .videos(this.generateBooleanValue())
                        .beach(this.generateBooleanValue())
                        .disabledAccess(this.generateBooleanValue())
                        .playground(this.generateBooleanValue())
                        .grill(this.generateBooleanValue())
                        .energyGenerator(this.generateBooleanValue())
                        .closeToTheCenter(this.generateBooleanValue())
                        .elevator(this.generateBooleanValue())
                        .pool(this.generateBooleanValue())
                        .frontDesk(this.generateBooleanValue())
                        .multiSportsCourt(this.generateBooleanValue())
                        .gym(this.generateBooleanValue())
                        .steamRoom(this.generateBooleanValue())
                        .cableTV(this.generateBooleanValue())
                        .heating(this.generateBooleanValue())
                        .cabinetsInTheKitchen(this.generateBooleanValue())
                        .bathroomInTheRoom(this.generateBooleanValue())
                        .internet(this.generateBooleanValue())
                        .partyRoom(this.generateBooleanValue())
                        .airConditioning(this.generateBooleanValue())
                        .americanKitchen(this.generateBooleanValue())
                        .hydromassage(this.generateBooleanValue())
                        .fireplace(this.generateBooleanValue())
                        .privatePool(this.generateBooleanValue())
                        .electronicGate(this.generateBooleanValue())
                        .serviceArea(this.generateBooleanValue())
                        .pub(this.generateBooleanValue())
                        .closet(this.generateBooleanValue())
                        .office(this.generateBooleanValue())
                        .yard(this.generateBooleanValue())
                        .alarmSystem(this.generateBooleanValue())
                        .balcony(this.generateBooleanValue())
                        .concierge24Hour(this.generateBooleanValue())
                        .walledArea(this.generateBooleanValue())
                        .dogAllowed(this.generateBooleanValue())
                        .catAllowed(this.generateBooleanValue())
                        .cameras(this.generateBooleanValue())
                        .furnished(this.generateBooleanValue())
                        .seaView(this.generateBooleanValue())
                        .gatedCommunity(this.generateBooleanValue())
                        .active(this.generateBooleanValue())
                        .user(null)
                        .favoriteUser(List.of())
                        .files(immobiliesImages.get(i))
                        .build();

                if (i <= 9) {
                    immobile.setUser(paula);
                } else {
                    immobile.setUser(pedro);
                }

                immobileRepository.save(immobile);
            }
        }
    }

    public BigDecimal generatePriceInBigDecimalType (String option) {
        if (option.equals("iptu")) {
            BigDecimal iptuMin = BigDecimal.valueOf(100.00);
            BigDecimal iptuMax = BigDecimal.valueOf(1000.00);

            double randomDouble = Math.random();

            return iptuMin.add(
                    iptuMax.subtract(iptuMin).multiply(BigDecimal.valueOf(randomDouble))
            ).setScale(2, RoundingMode.HALF_UP);
        }

        if (option.equals("price")) {
            BigDecimal iptuMin = BigDecimal.valueOf(100000.00);
            BigDecimal iptuMax = BigDecimal.valueOf(1000000.00);

            double randomDouble = Math.random();

            return iptuMin.add(
                    iptuMax.subtract(iptuMin).multiply(BigDecimal.valueOf(randomDouble))
            ).setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.valueOf(50000.00);
    }

    public int generateQtyLocations(String option) {
        if (option.equals("bedrooms") || option.equals("bathrooms")) {
            int bedroomMin = 1;
            int bedroomMax = 5;

            return ThreadLocalRandom.current().nextInt(bedroomMin, bedroomMax + 1);
        }

        if (option.equals("rooms")) {
            int bedroomMin = 1;
            int bedroomMax = 9;

            return ThreadLocalRandom.current().nextInt(bedroomMin, bedroomMax + 1);
        }

        return 5;
    }

    public boolean generateBooleanValue() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
