package com.example.ecommerce;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(CategoryRepository repository) {
        return args -> {
            List<Category> categories = List.of(
                   new Category(0, "category_1"),
                   new Category(0, "category_2"),
                   new Category(0, "category_3"),
                   new Category(0, "category_4"),
                   new Category(0, "category_5"),
                   new Category(0, "category_6"),
                   new Category(0, "category_7"),
                   new Category(0, "category_8"),
                   new Category(0, "category_9"),
                   new Category(0, "category_10"),
                   new Category(0, "category_11"),
                   new Category(0, "category_12"),
                   new Category(0, "category_13"),
                   new Category(0, "category_14"),
                   new Category(0, "category_15"),
                   new Category(0, "category_16"),
                   new Category(0, "category_17"),
                   new Category(0, "category_18"),
                   new Category(0, "category_19"),
                   new Category(0, "category_20"),
                   new Category(0, "category_21"),
                   new Category(0, "category_22"),
                   new Category(0, "category_23"),
                   new Category(0, "category_24"),
                   new Category(0, "category_25"),
                   new Category(0, "category_26"),
                   new Category(0, "category_27"),
                   new Category(0, "category_28"),
                   new Category(0, "category_29"),
                   new Category(0, "category_30")
            );
            repository.saveAll(categories);
        };
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
