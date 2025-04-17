package com.engcode.agendador_tarefas.controller;

import com.engcode.agendador_tarefas.business.TarefasService;
import com.engcode.agendador_tarefas.business.dto.TarefasDTO;
import com.engcode.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    //Get para buscar Lista de tarefas num determinado tempo.
    @GetMapping ("/eventos")
    public  ResponseEntity <List<TarefasDTO>> buscarListaDeTarefasPorPeriodo (
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));

    }

    //Get para buscar eventos por email.
    @GetMapping
    public  ResponseEntity <List<TarefasDTO>> buscaTarefasPorEmail (@RequestHeader ("Authorization") String token)  {
        /*
        * Tb pode ser feito assim
        *List<TarefasDTO> tarefasDTOS = tarefasService.buscaTarefasPorEmail(token);
        * return ResponseEntity.ok(tarefasDTOS);
        * */
        //Não está usando exceções por está passando o token e dificilmente terá erro de exceções.

        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    //Metodo para deletar as tarefas por Id
    @DeleteMapping
    public ResponseEntity <Void> DeletaTarefaPorId (@RequestParam ("id") String id) {

        tarefasService.DeletaTarefaPorId(id);

        return ResponseEntity.ok().build();
    }

    //Metodo para alteração de status das tarefas.
    @PatchMapping
    private  ResponseEntity<TarefasDTO> alteraStatusDeNotificacao (@RequestParam ("status")StatusNotificacaoEnum statusNotificacaoEnum, @RequestParam ("id") String id) {
        return ResponseEntity.ok(tarefasService.alteraStatus(statusNotificacaoEnum, id));
    }

    //Metodo para atualizar os dados das tarefas.
    @PutMapping
    public ResponseEntity<TarefasDTO> atualizaTarefas (@RequestBody TarefasDTO tarefasDTO,  @RequestParam ("id") String id) {
        return ResponseEntity.ok(tarefasService.atualizaTarefas(tarefasDTO, id));
    }


}
