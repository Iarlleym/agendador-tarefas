package com.engcode.agendador_tarefas.infrastructure.entity;

import com.engcode.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document ("tarefa")
public class TarefasEntity {

    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    //LocaDate trabalha com data - LocalDateTime trabalha com data e hora.
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    //enunms são uma classe que representa um grupo fixo de constantes. (nesse caso - Pendente, Notificado e Cancelado)
    private StatusNotificacaoEnum statusNotificacaoEnum;

}
