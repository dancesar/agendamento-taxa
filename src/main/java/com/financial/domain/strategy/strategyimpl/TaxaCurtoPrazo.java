package com.financial.domain.strategy.strategyimpl;

import com.financial.domain.strategy.TaxaStrategy;

import java.math.BigDecimal;

public class TaxaCurtoPrazo implements TaxaStrategy {

    @Override
    public Boolean aplica(Long dias, BigDecimal valor) {
        return dias >= 1 && dias <= 10 && valor.compareTo(new BigDecimal("12.00")) <= 0;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor, Long dias) {
        return BigDecimal.ZERO;
    }
}
