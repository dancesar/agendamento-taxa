package com.financial.domain.exceptions;

public class TaxaInvalidaException extends RuntimeException {

    public TaxaInvalidaException() {
        super("Nenhuma taxa aplicável para a transferência informada.");
    }

    public TaxaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
