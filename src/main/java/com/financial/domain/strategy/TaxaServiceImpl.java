package com.financial.domain.strategy;

import com.financial.domain.exceptions.TaxaInvalidaException;

import java.math.BigDecimal;
import java.util.List;

public class TaxaServiceImpl implements TaxaService {

    private final List<TaxaStrategy> estrategias;

    public TaxaServiceImpl(List<TaxaStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor, Long dias) {
        return estrategias.stream()
                .filter(e -> e.aplica(dias, valor))
                .findFirst()
                .map(e -> e.calcular(valor, dias))
                .orElseThrow(() -> new TaxaInvalidaException());
    }
}
