package com.financial.domain.strategy;

import java.math.BigDecimal;

public interface TaxaStrategy {

    Boolean aplica(Long dias, BigDecimal valor);
    BigDecimal calcular(BigDecimal valor, Long dias);
}
