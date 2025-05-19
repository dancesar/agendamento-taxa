package com.financial.domain.strategy;

import com.financial.domain.exceptions.TaxaInvalidaException;
import com.financial.domain.strategy.strategyimpl.TaxaCurtoPrazo;
import com.financial.domain.strategy.strategyimpl.TaxaLongoPrazo;
import com.financial.domain.strategy.strategyimpl.TaxaMedioPrazo;
import com.financial.domain.strategy.strategyimpl.TaxaZeroOuPequenoValor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TaxaServiceImplTest {

    private TaxaService taxaService;

    @BeforeEach
    void setUp() {
        taxaService = new TaxaServiceImpl(List.of(
                new TaxaZeroOuPequenoValor(),
                new TaxaCurtoPrazo(),
                new TaxaMedioPrazo(),
                new TaxaLongoPrazo()
        ));
    }

    @Test
    void deveAplicarTaxaDe25PorCentoParaHojeComValorAte3() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("3.00"), 0L);
        assertThat(taxa).isEqualByComparingTo("0.075");
    }

    @Test
    void deveAplicarTaxaZeroParaAte10DiasEValorAte12() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("12.00"), 5L);
        assertThat(taxa).isEqualByComparingTo("0.00");
    }

    @Test
    void deveAplicarTaxaDe82PorCentoPara11A20Dias() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), 15L);
        assertThat(taxa).isEqualByComparingTo("82.00");
    }

    @Test
    void deveAplicarTaxaDe69PorCentoPara21A30Dias() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), 25L);
        assertThat(taxa).isEqualByComparingTo("69.00");
    }

    @Test
    void deveAplicarTaxaDe47PorCentoPara31A40Dias() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), 35L);
        assertThat(taxa).isEqualByComparingTo("47.00");
    }

    @Test
    void deveAplicarTaxaDe17PorCentoPara41A50Dias() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), 45L);
        assertThat(taxa).isEqualByComparingTo("17.00");
    }

    @Test
    void deveLancarExcecaoParaMaisDe50Dias() {
        assertThatThrownBy(() ->
                taxaService.calcular(new BigDecimal("1000.00"), 60L)
        ).isInstanceOf(TaxaInvalidaException.class)
                .hasMessage("Nenhuma taxa aplicável para a transferência informada.");
    }
}
