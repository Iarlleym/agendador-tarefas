package com.engcode.agendador_tarefas.infrastructure.repository;

import com.engcode.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.engcode.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository <TarefasEntity, String> {

    //criar metodo para buscar as tarefas em um determinado periodo de tempo.
    //usa o findBy e coloca de acordo com oq vc quer buscar nesse caso dataEvento e add Between para buscar entre 2 periodos de tempo e pelo status.
    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime datainicial, LocalDateTime dataFinal, StatusNotificacaoEnum statusNotificacaoEnum);
    //Para o get por email
    List<TarefasEntity> findByEmailUsuario (String email);


}
