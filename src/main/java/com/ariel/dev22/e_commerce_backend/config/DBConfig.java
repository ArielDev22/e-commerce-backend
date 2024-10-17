package com.ariel.dev22.e_commerce_backend.config;

import com.ariel.dev22.e_commerce_backend.product.Product;
import com.ariel.dev22.e_commerce_backend.product.ProductRepository;
import com.ariel.dev22.e_commerce_backend.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DBConfig {
    @Bean
    CommandLineRunner initDB(UserService userService, ProductRepository productRepository) {
        return args -> {
            String imageDir = "images/";
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            userService.createUser("teste", "teste@gmail.com", encoder.encode("teste123"));

            Product p1 = new Product("Batom", BigDecimal.valueOf(39.99), "beauty");
            p1.setDetails("Realce seus lábios com este batom de alta cobertura e longa duração. Disponível em diversas cores vibrantes, ele hidrata enquanto proporciona um acabamento suave e impecável.");

            Product p2 = new Product("Rímel", BigDecimal.valueOf(89.99), "beauty");
            p2.setDetails("Obtenha cílios volumosos e alongados com este rímel à prova d'água. Sua fórmula leve e resistente não empelota e garante um olhar marcante durante todo o dia.");

            Product p3 = new Product("Paleta", BigDecimal.valueOf(84.99), "beauty");
            p3.setDetails("Explore infinitas possibilidades de maquiagem com esta paleta de sombras. Com cores intensas e altamente pigmentadas, é perfeita para criar looks tanto para o dia quanto para a noite.");

            Product p4 = new Product("Blush", BigDecimal.valueOf(79.99), "beauty");
            p4.setDetails("Dê um toque de cor e frescor às suas bochechas com este blush aveludado. Fácil de aplicar e esfumável, oferece uma aparência natural e radiante.");

            Product p5 = new Product("Delineador", BigDecimal.valueOf(69.99), "beauty");
            p5.setDetails("Defina o olhar com precisão usando este delineador líquido. Sua ponta fina permite traços perfeitos, e a fórmula de longa duração resiste ao calor e à umidade.");

            Product p6 = new Product("Pó Compacto", BigDecimal.valueOf(109.99), "beauty");
            p6.setDetails("Controle a oleosidade e finalize sua maquiagem com este pó compacto. Ele oferece um acabamento matte suave e uma textura leve para manter a pele impecável ao longo do dia.");

            Product p7 = new Product("Gloss", BigDecimal.valueOf(36.99), "beauty");
            p7.setDetails("Adicione brilho e volume aos lábios com este gloss hidratante. Com textura leve e efeito não pegajoso, proporciona um toque elegante e confortável.");

            Product p8 = new Product("Água Micelar", BigDecimal.valueOf(59.99), "beauty");
            p8.setDetails("Limpe e revitalize sua pele com esta água micelar. Sua fórmula suave remove maquiagem e impurezas sem agredir, deixando a pele fresca e hidratada.");

            Product p9 = new Product("Hidratante Labial", BigDecimal.valueOf(29.99), "beauty");
            p9.setDetails("Mantenha seus lábios macios e protegidos com este hidratante labial. Ideal para o uso diário, ele previne o ressecamento e proporciona conforto imediato.");

            Product pc1 = new Product("Vestido Floral", BigDecimal.valueOf(299.99), "female_fashion");
            Product pc2 = new Product("Salto Formal", BigDecimal.valueOf(109.99), "female_fashion");
            Product pc3 = new Product("Colar Prata", BigDecimal.valueOf(89.99), "female_fashion");
            Product pc4 = new Product("Calça Alfaiataria", BigDecimal.valueOf(99.99), "female_fashion");
            Product pc5 = new Product("Óculos", BigDecimal.valueOf(108.99), "female_fashion");
            Product pc6 = new Product("Saia Off White", BigDecimal.valueOf(89.99), "female_fashion");
            Product pc7 = new Product("Chapéu Azul", BigDecimal.valueOf(39.99), "female_fashion");
            Product pc8 = new Product("Blusa com Manga", BigDecimal.valueOf(59.99), "female_fashion");
            Product pc9 = new Product("Papete Branca", BigDecimal.valueOf(75.99), "female_fashion");

            Product pm1 = new Product("Camisa Masculina", BigDecimal.valueOf(299.99), "male_fashion");
            Product pm2 = new Product("Short Masculino", BigDecimal.valueOf(109.99), "male_fashion");
            Product pm3 = new Product("Relógio", BigDecimal.valueOf(89.99), "male_fashion");
            Product pm4 = new Product("Sapato Mocassim", BigDecimal.valueOf(99.99), "male_fashion");
            Product pm5 = new Product("Gravata", BigDecimal.valueOf(108.99), "male_fashion");
            Product pm6 = new Product("Calça Formal", BigDecimal.valueOf(89.99), "male_fashion");
            Product pm7 = new Product("Cinto Marrom", BigDecimal.valueOf(39.99), "male_fashion");
            Product pm8 = new Product("Camisa Manga Comprida", BigDecimal.valueOf(59.99), "male_fashion");
            Product pm9 = new Product("Chinelo", BigDecimal.valueOf(75.99), "male_fashion");

            Product ps1 = new Product("Tênis Adidas", BigDecimal.valueOf(299.99), "sport");
            Product ps2 = new Product("Top Nike", BigDecimal.valueOf(109.99), "sport");
            Product ps3 = new Product("Boné Adidas", BigDecimal.valueOf(89.99), "sport");
            Product ps4 = new Product("Bola Mikasa", BigDecimal.valueOf(99.99), "sport");
            Product ps5 = new Product("Calça Adidas", BigDecimal.valueOf(108.99), "sport");
            Product ps6 = new Product("Blusas Esportivas", BigDecimal.valueOf(89.99), "sport");
            Product ps7 = new Product("Meia Branca", BigDecimal.valueOf(39.99), "sport");
            Product ps8 = new Product("Peso Rosa", BigDecimal.valueOf(59.99), "sport");
            Product ps9 = new Product("Short Preto", BigDecimal.valueOf(75.99), "sport");

            List<Product> beauties = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9);
            List<Product> female = Arrays.asList(pc1, pc2, pc3, pc4, pc5, pc6, pc7, pc8, pc9);
            List<Product> male = Arrays.asList(pm1, pm2, pm3, pm4, pm5, pm6, pm7, pm8, pm9);
            List<Product> sports = Arrays.asList(ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9);

            List<List<Product>> lists = new ArrayList<>();

            lists.add(beauties);
            lists.add(female);
            lists.add(male);
            lists.add(sports);

            for (List<Product> list : lists) {
                for (Product product : list) {
                    product.setImageUrl(imageDir + product.getCategory().getCategoryValue()
                            + "/" + product.getName().replace(" ", "_").toLowerCase() + ".jpg");
                }
            }

            lists.forEach(productRepository::saveAll);
        };
    }
}
