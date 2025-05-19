package com.financial.domain.strategy;

import java.math.BigDecimal;

public interface TaxaService {

    BigDecimal calcular(BigDecimal valor, Long dias);
}
