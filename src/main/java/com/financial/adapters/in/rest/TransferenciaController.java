package com.financial.adapters.in.rest;

import com.financial.adapters.in.dto.TransferenciaDTO;
import com.financial.domain.entities.Transferencia;
import com.financial.domain.ports.TransferenciaServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
@Tag(name = "Transferências", description = "API para agendamento de transferências")
@CrossOrigin("http://localhost:4200")
public class TransferenciaController {

    private final TransferenciaServicePort transferenciaServicePort;

    public TransferenciaController(TransferenciaServicePort transferenciaServicePort) {
        this.transferenciaServicePort = transferenciaServicePort;
    }

    @PostMapping
    @Operation(summary = "Agendar nova transferência")
    public ResponseEntity<Transferencia> agendar(@Valid @RequestBody TransferenciaDTO dto) {
        Transferencia transferencia = new Transferencia(
                dto.getContaOrigem(),
                dto.getContaDestino(),
                dto.getValor(),
                null,
                dto.getDataAgendamento(),
                dto.getDataTransferencia()
        );

        Transferencia result = transferenciaServicePort.agendar(transferencia);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Operation(summary = "Listar transferências agendadas")
    public ResponseEntity<List<Transferencia>> listarTodas() {
        return ResponseEntity.ok(transferenciaServicePort.listarTodas());
    }
}
