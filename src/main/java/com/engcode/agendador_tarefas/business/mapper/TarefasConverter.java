package com.engcode.agendador_tarefas.business.mapper;

//a pasta converter e a pasta mapper tem a mesma função converter de entity para dto e vice-versa.
//A conversão de entity para dto e de dto para entity vai ser usado mapStruct
//em vex de usar uma classe uma uma interface.

import com.engcode.agendador_tarefas.business.dto.TarefasDTO;
import com.engcode.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.engcode.agendador_tarefas.business.mapper.TarefasConverter;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring" )

public interface TarefasConverter {

    TarefasEntity paraTarefaEntity (TarefasDTO tarefasDTO);
    TarefasDTO paraTarefaDTO (TarefasEntity tarefasEntity);

}
