package com.financial.domain.strategy;

import com.financial.domain.exceptions.TaxaInvalidaException;

import java.math.BigDecimal;
import java.util.List;

public class TaxaStrategyFactory {

    private final List<TaxaStrategy> estrategias;

    public TaxaStrategyFactory(List<TaxaStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    public TaxaStrategy obterEstrategias(BigDecimal valor, Long dias) {
        return estrategias.stream()
                .filter(e -> e.aplica(dias, valor))
                .findFirst()
                .orElseThrow(TaxaInvalidaException::new);
    }
}
