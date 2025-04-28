package com.ariel.dev22.e_commerce_backend.infra.config;

import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import com.ariel.dev22.e_commerce_backend.domains.product.repository.ProductRepository;
import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.ariel.dev22.e_commerce_backend.domains.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DBConfig {
    @Bean
    CommandLineRunner initDB(UserService userService, ProductRepository productRepository) {
        return args -> {
            // CRIAÇÃO DE USUÁRIO
            User user = new User("teste", "teste@gmail.com", "teste123", "user");

            userService.register(user);

            // PRODUTOS
            Product p1 = new Product("Lipstick", BigDecimal.valueOf(39.99), "beauty",
                    "Realce seus lábios com este batom de alta cobertura e longa duração. Disponível em diversas cores vibrantes, ele hidrata enquanto proporciona um acabamento suave e impecável.");

            Product p2 = new Product("Mascara", BigDecimal.valueOf(89.99), "beauty",
                    "Obtenha cílios volumosos e alongados com este rímel à prova d'água. Sua fórmula leve e resistente não empelota e garante um olhar marcante durante todo o dia.");

            Product p3 = new Product("Pallet", BigDecimal.valueOf(84.99), "beauty",
                    "Explore infinitas possibilidades de maquiagem com esta paleta de sombras. Com cores intensas e altamente pigmentadas, é perfeita para criar looks tanto para o dia quanto para a noite.");

            Product p4 = new Product("Blush", BigDecimal.valueOf(79.99), "beauty",
                    "Dê um toque de cor e frescor às suas bochechas com este blush aveludado. Fácil de aplicar e esfumável, oferece uma aparência natural e radiante.");

            Product p5 = new Product("Eyeliner", BigDecimal.valueOf(69.99), "beauty",
                    "Defina o olhar com precisão usando este delineador líquido. Sua ponta fina permite traços perfeitos, e a fórmula de longa duração resiste ao calor e à umidade.");

            Product p6 = new Product("Compact Powder", BigDecimal.valueOf(109.99), "beauty",
                    "Controle a oleosidade e finalize sua maquiagem com este pó compacto. Ele oferece um acabamento matte suave e uma textura leve para manter a pele impecável ao longo do dia.");

            Product p7 = new Product("Gloss", BigDecimal.valueOf(36.99), "beauty",
                    "Adicione brilho e volume aos lábios com este gloss hidratante. Com textura leve e efeito não pegajoso, proporciona um toque elegante e confortável.");

            Product p8 = new Product("Micellar Water", BigDecimal.valueOf(59.99), "beauty",
                    "Limpe e revitalize sua pele com esta água micelar. Sua fórmula suave remove maquiagem e impurezas sem agredir, deixando a pele fresca e hidratada.");

            Product p9 = new Product("Lip Moisturizer", BigDecimal.valueOf(29.99), "beauty",
                    "Mantenha seus lábios macios e protegidos com este hidratante labial. Ideal para o uso diário, ele previne o ressecamento e proporciona conforto imediato.");

            Product pc1 = new Product("Floral Dress", BigDecimal.valueOf(299.99), "female_fashion", "Leve e elegante, perfeito para dias ensolarados.");
            Product pc2 = new Product("Formal Heels", BigDecimal.valueOf(109.99), "female_fashion", "Estilo sofisticado para eventos especiais.");
            Product pc3 = new Product("Silver Necklace", BigDecimal.valueOf(89.99), "female_fashion", "Beleza clássica em um acessório delicado.");
            Product pc4 = new Product("Tailored Pants", BigDecimal.valueOf(99.99), "female_fashion", "Conforto e elegância para um look impecável.");
            Product pc5 = new Product("Women's Glasses", BigDecimal.valueOf(108.99), "female_fashion", "Design moderno para proteção e estilo.");
            Product pc6 = new Product("Off White Skirt", BigDecimal.valueOf(89.99), "female_fashion", "Versátil e charmosa para diversas ocasiões.");
            Product pc7 = new Product("Blue Hat", BigDecimal.valueOf(39.99), "female_fashion", "Um toque descontraído para complementar seu visual.");
            Product pc8 = new Product("Blouse With Sleeves", BigDecimal.valueOf(59.99), "female_fashion", "Confortável e perfeita para compor diferentes looks.");
            Product pc9 = new Product("White Sandal", BigDecimal.valueOf(75.99), "female_fashion", "Estilo e praticidade com um toque de brilho.");

            Product pm1 = new Product("Men's Shirt", BigDecimal.valueOf(299.99), "male_fashion", "Estilo e conforto para qualquer evento.");
            Product pm2 = new Product("Men's Short", BigDecimal.valueOf(109.99), "male_fashion", "Confortável e ideal para atividades casuais.");
            Product pm3 = new Product("Watch", BigDecimal.valueOf(89.99), "male_fashion", "Design moderno e funcional para todas as ocasiões.");
            Product pm4 = new Product("Moccasin Shoes", BigDecimal.valueOf(99.99), "male_fashion", "Sofisticação e conforto para o dia a dia.");
            Product pm5 = new Product("Tie", BigDecimal.valueOf(108.99), "male_fashion", "Um toque clássico para seu look formal.");
            Product pm6 = new Product("Formal Pants", BigDecimal.valueOf(89.99), "male_fashion", "Corte clássico para compor looks elegantes.");
            Product pm7 = new Product("Brown Belt", BigDecimal.valueOf(39.99), "male_fashion", "Elegância e praticidade para compor seu visual.");
            Product pm8 = new Product("Long Sleeve Shirt", BigDecimal.valueOf(59.99), "male_fashion", "Versatilidade e estilo para dias frescos.");
            Product pm9 = new Product("Flip Flops", BigDecimal.valueOf(75.99), "male_fashion", "Leveza e praticidade para momentos de relaxamento.");

            Product ps1 = new Product("Adidas Sneakers", BigDecimal.valueOf(299.99), "sport", "Conforto e desempenho para qualquer atividade.");
            Product ps2 = new Product("Nike Top", BigDecimal.valueOf(109.99), "sport", "Sustentação e conforto no treino.");
            Product ps3 = new Product("Adidas Cap", BigDecimal.valueOf(89.99), "sport", "Estilo e conforto para o dia a dia.");
            Product ps4 = new Product("Mikasa Ball", BigDecimal.valueOf(99.99), "sport", "Resistente e precisa para seu jogo.");
            Product ps5 = new Product("Adidas Pants", BigDecimal.valueOf(108.99), "sport", "Moderna e confortável para treino ou lazer.");
            Product ps6 = new Product("Sports Tops", BigDecimal.valueOf(89.99), "sport", "Estilo casual com toque esportivo.");
            Product ps7 = new Product("White Socks", BigDecimal.valueOf(39.99), "sport", "Leve e macia para uso diário.");
            Product ps8 = new Product("Pink Weight", BigDecimal.valueOf(59.99), "sport", "Compacto e ideal para exercícios de força.");
            Product ps9 = new Product("Black Short", BigDecimal.valueOf(75.99), "sport", "Versátil e leve para qualquer momento.");

            List<Product> beauties = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9);
            List<Product> female = Arrays.asList(pc1, pc2, pc3, pc4, pc5, pc6, pc7, pc8, pc9);
            List<Product> male = Arrays.asList(pm1, pm2, pm3, pm4, pm5, pm6, pm7, pm8, pm9);
            List<Product> sports = Arrays.asList(ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9);

            List<List<Product>> lists = new ArrayList<>();

            lists.add(beauties);
            lists.add(female);
            lists.add(male);
            lists.add(sports);

            // ADICIONAR IMAGENS DOS PRODUTOS
            String imageDir = "images/";

            for (List<Product> list : lists) {
                for (Product product : list) {
                    product.setImageUrl(imageDir + product.getCategory().getValue()
                            + "/" + product.getName().replace(" ", "_").toLowerCase() + ".jpg");
                }
            }

            lists.forEach(productRepository::saveAll);
        };
    }
}
