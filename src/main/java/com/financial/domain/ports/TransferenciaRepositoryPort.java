package com.financial.domain.ports;

import com.financial.domain.entities.Transferencia;

import java.util.List;

public interface TransferenciaRepositoryPort {

    Transferencia salvar(Transferencia transferencia);

    List<Transferencia> ListarTodas();
}
