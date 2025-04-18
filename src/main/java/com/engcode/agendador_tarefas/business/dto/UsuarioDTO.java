package com.engcode.agendador_tarefas.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    //Só valida do UserDetailsService email e senha por isso excluiu o restante.
    private String email;
    private String senha;

}
