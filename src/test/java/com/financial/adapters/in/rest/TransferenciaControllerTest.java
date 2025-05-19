package com.financial.adapters.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.domain.entities.Transferencia;
import com.financial.domain.ports.TransferenciaServicePort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransferenciaController.class)
public class TransferenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferenciaServicePort servicePort;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveAgendarTransferenciaComSucesso() throws Exception {
        Transferencia mock = new  Transferencia("123", "456", new BigDecimal("1000.00"), new BigDecimal("82.00"),
                LocalDate.of(2025, 5, 16), LocalDate.of(2025, 5, 31));

        Mockito.when(servicePort.agendar(any())).thenReturn(mock);

        String json = "{"
                + "\"contaOrigem\": \"123\","
                + "\"contaDestino\": \"456\","
                + "\"valor\": 1000.00,"
                + "\"dataAgendamento\": \"2025-05-16\","
                + "\"dataTransferencia\": \"2025-05-31\""
                + "}";

        mockMvc.perform(post("/transferencias")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taxa").value(82.00));
    }

    @Test
    void deveListarTransferencias() throws Exception {
        List<Transferencia> lista = List.of(new Transferencia("123", "456", new BigDecimal("1000.00"), new BigDecimal("82.00"),
                LocalDate.of(2025, 5, 16), LocalDate.of(2025, 5, 31)));

        Mockito.when(servicePort.listarTodas()).thenReturn(lista);

        mockMvc.perform(get("/transferencias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contaOrigem").value("123"));
    }
}
