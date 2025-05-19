package com.financial.config;

import com.financial.adapters.out.mapper.TransferenciaMapper;
import com.financial.adapters.out.persistence.TransferenciaRepositoryImpl;
import com.financial.adapters.out.persistence.TransferenciaRepositoryJpa;
import com.financial.application.services.TransferenciaServiceImpl;
import com.financial.domain.ports.TransferenciaRepositoryPort;
import com.financial.domain.ports.TransferenciaServicePort;
import com.financial.domain.strategy.TaxaService;
import com.financial.domain.strategy.TaxaServiceImpl;
import com.financial.domain.strategy.TaxaStrategy;
import com.financial.domain.strategy.strategyimpl.TaxaCurtoPrazo;
import com.financial.domain.strategy.strategyimpl.TaxaLongoPrazo;
import com.financial.domain.strategy.strategyimpl.TaxaMedioPrazo;
import com.financial.domain.strategy.strategyimpl.TaxaZeroOuPequenoValor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public TransferenciaRepositoryPort transferenciaRepositoryPort(
            TransferenciaRepositoryJpa repositoryJpa,
            TransferenciaMapper mapper) {
        return new TransferenciaRepositoryImpl(repositoryJpa, mapper);
    }

    @Bean
    public TransferenciaServicePort transferenciaServicePort(
            TransferenciaRepositoryPort repositoryPort,
            TaxaService taxaService) {
        return new TransferenciaServiceImpl(repositoryPort, taxaService);
    }

    @Bean
    public TaxaService taxaService() {
        List<TaxaStrategy> estrategias = List.of(
                new TaxaZeroOuPequenoValor(),
                new TaxaCurtoPrazo(),
                new TaxaMedioPrazo(),
                new TaxaLongoPrazo()
        );
        return new TaxaServiceImpl(estrategias);
    }
}
