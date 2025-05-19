package com.financial.application.services;

import com.financial.domain.entities.Transferencia;
import com.financial.domain.exceptions.TaxaInvalidaException;
import com.financial.domain.ports.TransferenciaRepositoryPort;
import com.financial.domain.strategy.TaxaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransferenciaServiceImplTest {

    private TransferenciaRepositoryPort repository;
    private TaxaService taxaService;
    private TransferenciaServiceImpl service;

    @BeforeEach
    void setup() {
        repository = mock(TransferenciaRepositoryPort.class);
        taxaService = mock(TaxaService.class);
        service = new TransferenciaServiceImpl(repository, taxaService);
    }

    @Test
    void deveAgendarTransferenciaComTaxaCalculada() {
        Transferencia entrada = new Transferencia("123", "456", new BigDecimal("1000.00"), null,
                LocalDate.of(2025, 5, 16), LocalDate.of(2025, 5, 31));

        when(taxaService.calcular(any(), anyLong())).thenReturn(new BigDecimal("82.00"));
        when(repository.salvar(any())).thenAnswer(i -> i.getArgument(0));

        Transferencia result = service.agendar(entrada);

        assertThat(result.getTaxa()).isEqualByComparingTo("82.00");
        verify(repository).salvar(result);
    }

    @Test
    void deveLancarExcecaoQuandoTaxaInvalida() {
        Transferencia entrada = new Transferencia("123", "456", new BigDecimal("1000.00"), null,
                LocalDate.of(2025, 5, 16), LocalDate.of(2025, 7, 31));

        when(taxaService.calcular(any(), anyLong())).thenThrow(new TaxaInvalidaException("Nenhuma taxa aplicável"));

        assertThatThrownBy(() -> service.agendar(entrada))
                .isInstanceOf(TaxaInvalidaException.class)
                .hasMessage("Nenhuma taxa aplicável");
    }

    @Test
    void deveListarTodasAsTransferencias() {
        List<Transferencia> lista = List.of(
                new Transferencia("1", "2", new BigDecimal("100"), new BigDecimal("5"),
                        LocalDate.now(), LocalDate.now().plusDays(1))
        );

        when(repository.ListarTodas()).thenReturn(lista);

        List<Transferencia> resultado = service.listarTodas();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getContaOrigem()).isEqualTo("1");
    }
}
