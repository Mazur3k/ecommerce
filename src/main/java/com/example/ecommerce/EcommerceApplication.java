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
                   new Category(0, "category_1", List.of()),
                   new Category(0, "category_2", List.of()),
                   new Category(0, "category_3", List.of()),
                   new Category(0, "category_4", List.of()),
                   new Category(0, "category_5", List.of()),
                   new Category(0, "category_6", List.of()),
                   new Category(0, "category_7", List.of()),
                   new Category(0, "category_8", List.of()),
                   new Category(0, "category_9", List.of()),
                   new Category(0, "category_10", List.of()),
                   new Category(0, "category_11", List.of()),
                   new Category(0, "category_12", List.of()),
                   new Category(0, "category_13", List.of()),
                   new Category(0, "category_14", List.of()),
                   new Category(0, "category_15", List.of()),
                   new Category(0, "category_16", List.of()),
                   new Category(0, "category_17", List.of()),
                   new Category(0, "category_18", List.of()),
                   new Category(0, "category_19", List.of()),
                   new Category(0, "category_20", List.of()),
                   new Category(0, "category_21", List.of()),
                   new Category(0, "category_22", List.of()),
                   new Category(0, "category_23", List.of()),
                   new Category(0, "category_24", List.of()),
                   new Category(0, "category_25", List.of()),
                   new Category(0, "category_26", List.of()),
                   new Category(0, "category_27", List.of()),
                   new Category(0, "category_28", List.of()),
                   new Category(0, "category_29", List.of()),
                   new Category(0, "category_30", List.of())
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
