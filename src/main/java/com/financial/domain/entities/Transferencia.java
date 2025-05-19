package com.financial.domain.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transferencia implements Serializable {

    private static final long serialVersionUID = 1L;

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private BigDecimal taxa;
    private LocalDate dataAgendamento;
    private LocalDate dataTransferencia;

    public Transferencia() {}

    public Transferencia(String contaOrigem, String contaDestino, BigDecimal valor, BigDecimal taxa, LocalDate dataAgendamento, LocalDate dataTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.taxa = taxa;
        this.dataAgendamento = dataAgendamento;
        this.dataTransferencia = dataTransferencia;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transferencia that = (Transferencia) o;
        return Objects.equals(contaOrigem, that.contaOrigem) && Objects.equals(contaDestino, that.contaDestino) && Objects.equals(valor, that.valor) && Objects.equals(taxa, that.taxa) && Objects.equals(dataAgendamento, that.dataAgendamento) && Objects.equals(dataTransferencia, that.dataTransferencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contaOrigem, contaDestino, valor, taxa, dataAgendamento, dataTransferencia);
    }
}
