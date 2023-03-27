package ru.netology.springback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.netology.springback.repository.CardRepository;
import ru.netology.springback.repository.CardRepositoryImpl;
import ru.netology.springback.repository.TransferRepository;
import ru.netology.springback.repository.TransferRepositoryImpl;
import ru.netology.springback.service.TransferService;
import ru.netology.springback.service.TransferServiceImpl;

@Configuration
public class JavaConfig {
    @Bean
    public TransferService transferService() {
        return new TransferServiceImpl(cardRepository(), transferRepository());
    }

    @Bean
    public CardRepository cardRepository() {
        return new CardRepositoryImpl();
    }

    @Bean
    public TransferRepository transferRepository() {
        return new TransferRepositoryImpl();
    }
}
