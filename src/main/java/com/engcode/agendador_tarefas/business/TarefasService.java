package com.engcode.agendador_tarefas.business;

import com.engcode.agendador_tarefas.business.dto.TarefasDTO;
import com.engcode.agendador_tarefas.business.mapper.TarefasConverter;
import com.engcode.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.engcode.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.engcode.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.engcode.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefasService {

    //Injeta a dependencia para usar a interface que extende o MongoRepository
    private final TarefasRepository tarefasRepository;
    //injeta a dependencia do conversor
    private final TarefasConverter tarefaConverter;
    //injeta a dependencia do JwtUtil
    private final JwtUtil jwtUtil;

    //Cria os métodos de salvar os dados fazendo a converção.
    public TarefasDTO gravarTarefa ( String token, TarefasDTO tarefasDTO) {

        //Vai pegar o email do token repassado no login.
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        //set o email pegado do token
        tarefasDTO.setEmailUsuario(email);

        //Esses dois primeiros dados não vão ser repasados pelo usuario.
        tarefasDTO.setDataCriacao(LocalDateTime.now()); //set a data e hora de criação atual
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);//set inicialmente como pendente importando lá do enum.
        //faz aconverção dto para entity
        TarefasEntity tarefasEntity =  tarefaConverter.paraTarefaEntity(tarefasDTO);

        //Retorna convertendo de entity para dto e salva.
        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefasEntity));
    }



}
