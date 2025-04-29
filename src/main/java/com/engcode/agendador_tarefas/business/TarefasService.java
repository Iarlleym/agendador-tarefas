package com.engcode.agendador_tarefas.business;

import com.engcode.agendador_tarefas.business.dto.TarefasDTO;
import com.engcode.agendador_tarefas.business.mapper.TarefaUpdateConvert;
import com.engcode.agendador_tarefas.business.mapper.TarefasConverter;
import com.engcode.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.engcode.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.engcode.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.engcode.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.engcode.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {

    //Injeta a dependencia para usar a interface que extende o MongoRepository
    private final TarefasRepository tarefasRepository;
    //injeta a dependencia do conversor
    private final TarefasConverter tarefaConverter;
    //injeta a dependencia do JwtUtil
    private final JwtUtil jwtUtil;
    //injeta a dependencia do TarefaUpdateConvert
    private final TarefaUpdateConvert tarefaUpdateConvert;

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

    //metodo para buscar as tarefas em um deternimado periodo de tempo.
    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo (LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefaDTO(tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
    }

    //metodo para buscar as tarefas por email.
    public List<TarefasDTO> buscaTarefasPorEmail (String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        //pode ser feito assim tb
        //List<TarefasEntity> tarefasEntities = tarefasRepository.findByEmailUsuario(email);
        //return tarefaConverter.paraListaTarefaDTO(tarefasEntities);

        //Ou assim.
        return tarefaConverter.paraListaTarefaDTO(tarefasRepository.findByEmailUsuario(email));
    }

    //metodo para deletar tarefa por id, não precisa criar nada no TarefasRepository pq o Mongo já tras pronto a função de deletar.
    public void DeletaTarefaPorId (String id) {
        //Foi colocado um try cacth para lançar uma exceção caso o id não estejá mais disponível.
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa, Id inexistente: " + id, e.getCause());
        }
    }

    //metodo para atualizar o status da tarefa
    public TarefasDTO alteraStatus (StatusNotificacaoEnum statusNotificacaoEnum, String id) {

        //Criu um try/cacth pq pode ser que seja digitado um id que não exista mais.
        try {
            //Procura as tarefas na entity pelo id.
            TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(
                    () -> new  ResourceNotFoundException ("Tarefa não encontrada." + id));
            //seta o status de notificação que foi recebido.
            tarefasEntity.setStatusNotificacaoEnum(statusNotificacaoEnum);
            //Converte de entity para DTO e salva as tarefas.
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefasEntity));

        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar statys da tarefa, " + e.getCause());
        }
    }

    //O metodo para atualizar dados das tarefas será feito com o mapstruct
    public TarefasDTO atualizaTarefas (TarefasDTO tarefasDTO, String id) {

        try {
            //Procura as tarefas na entity pelo id.
            TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada." + id));

            //Faz a atualização dos dados da tarefa
            tarefaUpdateConvert.updateDeTarefas(tarefasDTO, tarefasEntity);
            //Converte de entity para DTO e salva as tarefas.
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefasEntity));

        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao atualizar tarefa, " + e.getCause());
        }

    }

}
