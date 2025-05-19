package com.financial.domain.strategy.strategyimpl;

import com.financial.domain.strategy.TaxaStrategy;

import java.math.BigDecimal;

public class TaxaLongoPrazo implements TaxaStrategy {

    @Override
    public Boolean aplica(Long dias, BigDecimal valor) {
        return dias >= 21 && dias <= 50;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor, Long dias) {
        if (dias <= 30) return valor.multiply((new BigDecimal("0.069")));
        if (dias <= 40) return valor.multiply((new BigDecimal("0.047")));
        return valor.multiply(new BigDecimal("0.017"));
    }
}
