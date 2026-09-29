package com.posto.controller;

import com.posto.dto.ConsultaGeralResponse;
import com.posto.service.ConsultaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    /** Retorna tipos de combustível, bombas e abastecimentos em uma única resposta. */
    @GetMapping
    public ConsultaGeralResponse consultarTudo() {
        return consultaService.consultarTudo();
    }
}
