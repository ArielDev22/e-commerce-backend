package com.ariel.dev22.e_commerce_backend.infra.config;

import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import com.ariel.dev22.e_commerce_backend.domains.product.repository.ProductRepository;
import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.ariel.dev22.e_commerce_backend.domains.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DBConfig {
    private final JdbcTemplate jdbcTemplate;

    public DBConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    CommandLineRunner initDB(UserService userService, ProductRepository productRepository) {
        return args -> {
            if (!isTablePopulated("users")){
                System.out.println("Preenchendo o banco de dados...");

                User user = new User("teste", "teste@gmail.com", "teste123", "user");

                userService.register(user);

                Product p1 = new Product("Lipstick", BigDecimal.valueOf(39.99), "beauty",
                        "Enhance your lips with this long-lasting, full-coverage lipstick. Available in a range of vibrant shades, it moisturizes while providing a smooth, flawless finish.");

                Product p2 = new Product("Mascara", BigDecimal.valueOf(89.99), "beauty",
                        "Get voluminous, lengthening lashes with this waterproof mascara. Its lightweight, clump-free formula ensures a striking look all day long.");

                Product p3 = new Product("Pallet", BigDecimal.valueOf(84.99), "beauty",
                        "Explore endless makeup possibilities with this eyeshadow palette. With intense, highly pigmented colors, it's perfect for creating looks for both day and night.");

                Product p4 = new Product("Blush", BigDecimal.valueOf(79.99), "beauty",
                        "Add a touch of color and freshness to your cheeks with this velvety blush. Easy to apply and blendable, it offers a natural and radiant appearance.");

                Product p5 = new Product("Eyeliner", BigDecimal.valueOf(69.99), "beauty",
                        "Define your eyes with precision using this liquid eyeliner. Its fine tip allows for perfect lines, and the long-lasting formula resists heat and humidity.");

                Product p6 = new Product("Compact Powder", BigDecimal.valueOf(109.99), "beauty",
                        "Control oiliness and finish your makeup with this compact powder. It offers a soft matte finish and a lightweight texture to keep your skin flawless throughout the day.");

                Product p7 = new Product("Gloss", BigDecimal.valueOf(36.99), "beauty",
                        "Add shine and volume to your lips with this moisturizing gloss. With a light texture and non-sticky effect, it provides an elegant and comfortable touch.");

                Product p8 = new Product("Micellar Water", BigDecimal.valueOf(59.99), "beauty",
                        "Cleanse and revitalize your skin with this micellar water. Its gentle formula removes makeup and impurities without damaging them, leaving your skin feeling fresh and hydrated.");

                Product p9 = new Product("Lip Moisturizer", BigDecimal.valueOf(29.99), "beauty",
                        "Keep your lips soft and protected with this lip balm. Ideal for daily use, it prevents dryness and provides immediate comfort.");

                Product pc1 = new Product("Floral Dress", BigDecimal.valueOf(299.99), "female_fashion", "Light and elegant, perfect for sunny days.");
                Product pc2 = new Product("Formal Heels", BigDecimal.valueOf(109.99), "female_fashion", "Sophisticated style for special events.");
                Product pc3 = new Product("Silver Necklace", BigDecimal.valueOf(89.99), "female_fashion", "Classic beauty in a delicate accessory.");
                Product pc4 = new Product("Tailored Pants", BigDecimal.valueOf(99.99), "female_fashion", "Comfort and elegance for an impeccable look.");
                Product pc5 = new Product("Women's Glasses", BigDecimal.valueOf(108.99), "female_fashion", "Modern design for protection and style.");
                Product pc6 = new Product("Off White Skirt", BigDecimal.valueOf(89.99), "female_fashion", "Versatile and charming for different occasions.");
                Product pc7 = new Product("Blue Hat", BigDecimal.valueOf(39.99), "female_fashion", "A relaxed touch to complement your look.");
                Product pc8 = new Product("Blouse With Sleeves", BigDecimal.valueOf(59.99), "female_fashion", "Comfortable and perfect for creating different looks.");
                Product pc9 = new Product("White Sandal", BigDecimal.valueOf(75.99), "female_fashion", "Style and practicality with a touch of shine.");

                Product pm1 = new Product("Men's Shirt", BigDecimal.valueOf(299.99), "male_fashion", "Style and comfort for any event.");
                Product pm2 = new Product("Men's Short", BigDecimal.valueOf(109.99), "male_fashion", "Comfortable and ideal for casual activities.");
                Product pm3 = new Product("Watch", BigDecimal.valueOf(89.99), "male_fashion", "Modern and functional design for all occasions.");
                Product pm4 = new Product("Moccasin Shoes", BigDecimal.valueOf(99.99), "male_fashion", "Sophistication and comfort for everyday life.");
                Product pm5 = new Product("Tie", BigDecimal.valueOf(108.99), "male_fashion", "A classic touch to your formal look.");
                Product pm6 = new Product("Formal Pants", BigDecimal.valueOf(89.99), "male_fashion", "Classic cut to create elegant looks.");
                Product pm7 = new Product("Brown Belt", BigDecimal.valueOf(39.99), "male_fashion", "Elegance and practicality to complete your look.");
                Product pm8 = new Product("Long Sleeve Shirt", BigDecimal.valueOf(59.99), "male_fashion", "Versatility and style for cool days.");
                Product pm9 = new Product("Flip Flops", BigDecimal.valueOf(75.99), "male_fashion", "Lightness and practicality for moments of relaxation.");

                Product ps1 = new Product("Adidas Sneakers", BigDecimal.valueOf(299.99), "sport", "Comfort and performance for any activity.");
                Product ps2 = new Product("Nike Top", BigDecimal.valueOf(109.99), "sport", "Support and comfort during training.");
                Product ps3 = new Product("Adidas Cap", BigDecimal.valueOf(89.99), "sport", "Style and comfort for everyday life.");
                Product ps4 = new Product("Mikasa Ball", BigDecimal.valueOf(99.99), "sport", "Durable and accurate for your game.");
                Product ps5 = new Product("Adidas Pants", BigDecimal.valueOf(108.99), "sport", "Modern and comfortable for training or leisure.");
                Product ps6 = new Product("Sports Tops", BigDecimal.valueOf(89.99), "sport", "Casual style with a sporty touch.");
                Product ps7 = new Product("White Socks", BigDecimal.valueOf(39.99), "sport", "Light and soft for everyday use.");
                Product ps8 = new Product("Pink Weight", BigDecimal.valueOf(59.99), "sport", "Compact and ideal for strength exercises.");
                Product ps9 = new Product("Black Short", BigDecimal.valueOf(75.99), "sport", "Versatile and lightweight for any moment.");

                List<Product> beauties = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9);
                List<Product> female = Arrays.asList(pc1, pc2, pc3, pc4, pc5, pc6, pc7, pc8, pc9);
                List<Product> male = Arrays.asList(pm1, pm2, pm3, pm4, pm5, pm6, pm7, pm8, pm9);
                List<Product> sports = Arrays.asList(ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9);

                List<List<Product>> lists = new ArrayList<>();

                lists.add(beauties);
                lists.add(female);
                lists.add(male);
                lists.add(sports);

                String imageDir = "images/";

                for (List<Product> list : lists) {
                    for (Product product : list) {
                        product.setImageUrl(imageDir + product.getCategory().getValue()
                                + "/" + product.getName().replace(" ", "_").toLowerCase() + ".jpg");
                    }
                }
                lists.forEach(productRepository::saveAll);

                System.out.println("Preenchimento do banco de dados concluído.");
            } else {
                System.out.println("O banco de dados já contém dados. Preenchimento inicial ignorado.");
            }
        };
    }

    private boolean isTablePopulated(String tableName) {
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + tableName.toLowerCase(), Integer.class);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            System.out.println("Tabela '" + tableName.toLowerCase() + "' não encontrada.");
            return false;
        }
    }
}
