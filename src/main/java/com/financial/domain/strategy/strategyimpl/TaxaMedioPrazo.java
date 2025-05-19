package com.financial.domain.strategy.strategyimpl;

import com.financial.domain.strategy.TaxaStrategy;

import java.math.BigDecimal;

public class TaxaMedioPrazo implements TaxaStrategy {

    @Override
    public Boolean aplica(Long dias, BigDecimal valor) {
        return dias >= 11 && dias <= 20;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor, Long dias) {
        return valor.multiply(new BigDecimal("0.082"));
    }
}
