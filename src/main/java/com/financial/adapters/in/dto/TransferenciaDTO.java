package com.financial.adapters.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaDTO {

    @NotBlank(message = "A conta de origem é obrigatória")
    private String contaOrigem;

    @NotBlank(message = "A conta destino é obrigatória")
    private String contaDestino;

    @NotNull(message = "O valor da transferência é obrigatória")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "A data de agendamento é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAgendamento;

    @NotNull(message = "A data de transferência é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTransferencia;

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
}
