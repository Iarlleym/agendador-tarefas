package com.engcode.agendador_tarefas.business.mapper;

//a pasta converter e a pasta mapper tem a mesma função converter de entity para dto e vice-versa.
//A conversão de entity para dto e de dto para entity vai ser usado mapStruct
//em vex de usar uma classe uma uma interface.

import com.engcode.agendador_tarefas.business.dto.TarefasDTO;
import com.engcode.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.engcode.agendador_tarefas.business.mapper.TarefasConverter;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper (componentModel = "spring" )


public interface TarefasConverter {

    // usamos @Mapping para deixar claro e explícito quais atributos estão sendo mapeados.
    // Isso ajuda na manutenção do código e evita confusão, especialmente em projetos maiores.
    /*@Mapping (source = "id" , target = "id")
    @Mapping (source = "dataEvento" , target = "dataEvento")
    @Mapping (source = "dataCriacao" , target = "dataCriacao")
    @Mapping(source = "dataAlteracao", target = "dataAlteracao")*/

    @Mapping(source = "id", target = "id")
    TarefasEntity paraTarefaEntity (TarefasDTO tarefasDTO);
    @Mapping(source = "dataAlteracao", target = "dataAlteracao")
    TarefasDTO paraTarefaDTO (TarefasEntity tarefasEntity);
    //converter as List entity para dto.
    List<TarefasEntity> paraListaTarfeEntity (List<TarefasDTO> tarefasDTOS);
    List<TarefasDTO> paraListaTarefaDTO (List<TarefasEntity> tarefasEntities);

}
