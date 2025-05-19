package com.financial.domain.strategy.strategyimpl;

import com.financial.domain.strategy.TaxaStrategy;

import java.math.BigDecimal;

public class TaxaZeroOuPequenoValor implements TaxaStrategy {

    @Override
    public Boolean aplica(Long dias, BigDecimal valor) {
        return dias == 0 && valor.compareTo(new BigDecimal("25.00")) <= 0;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor, Long dias) {
        return valor.multiply(new BigDecimal("0.025"));
    }
}
