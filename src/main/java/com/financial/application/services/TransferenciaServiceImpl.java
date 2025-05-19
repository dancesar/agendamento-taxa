package com.financial.application.services;

import com.financial.domain.entities.Transferencia;
import com.financial.domain.ports.TransferenciaRepositoryPort;
import com.financial.domain.ports.TransferenciaServicePort;
import com.financial.domain.strategy.TaxaService;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TransferenciaServiceImpl implements TransferenciaServicePort {

    private final TransferenciaRepositoryPort repositoryPort;
    private final TaxaService taxaService;

    public TransferenciaServiceImpl(TransferenciaRepositoryPort repositoryPort, TaxaService taxaService) {
        this.repositoryPort = repositoryPort;
        this.taxaService = taxaService;
    }

    @Override
    public Transferencia agendar(Transferencia transferencia) {
        long dias = ChronoUnit.DAYS.between(
                transferencia.getDataAgendamento(),
                transferencia.getDataTransferencia()
        );

        BigDecimal taxa = taxaService.calcular(transferencia.getValor(), dias);
        transferencia.setTaxa(taxa);

        return repositoryPort.salvar(transferencia);
    }

    @Override
    public List<Transferencia> listarTodas() {
        return repositoryPort.ListarTodas();
    }
}
