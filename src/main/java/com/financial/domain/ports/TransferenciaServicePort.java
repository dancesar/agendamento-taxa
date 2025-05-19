package com.financial.domain.ports;

import com.financial.domain.entities.Transferencia;

import java.util.List;

public interface TransferenciaServicePort {

    Transferencia agendar(Transferencia transferencia);

    List<Transferencia> listarTodas();
}
