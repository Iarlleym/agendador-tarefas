package com.engcode.agendador_tarefas.controller;

import com.engcode.agendador_tarefas.business.TarefasService;
import com.engcode.agendador_tarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    //Injeta a dependencia do TarefasService
    private final TarefasService tarefasService;


    //Cria os verbos HTTP
    @PostMapping
    public ResponseEntity <TarefasDTO> gravarTarefas (@RequestBody TarefasDTO tarefasDTO, @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

}
