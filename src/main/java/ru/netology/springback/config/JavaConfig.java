package ru.netology.springback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.netology.springback.repository.CardRepository;
import ru.netology.springback.repository.TransferRepository;
import ru.netology.springback.service.TransferService;

@Configuration
public class JavaConfig {
    @Bean
    public TransferService transferService() {
        return new TransferService(cardRepository(), transferRepository());
    }

    @Bean
    public CardRepository cardRepository() {
        return new CardRepository();
    }

    @Bean
    public TransferRepository transferRepository() {
        return new TransferRepository();
    }
}
