package com.engcode.agendador_tarefas.business.mapper;

import com.engcode.agendador_tarefas.business.dto.TarefasDTO;
import com.engcode.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

//esse nullValuePropertyMappingStrategy faz a mesma coisa da aplicação do ternário, se o dado estiver nulo na dto ele pega o dado da entity.
@Mapper (componentModel = "spring", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConvert {
    //esse @MappingTarget é para o Mapper saber qual usar caso o dto esteja null.
    void updateDeTarefas (TarefasDTO tarefasDTO, @MappingTarget TarefasEntity tarefasEntity);

}
