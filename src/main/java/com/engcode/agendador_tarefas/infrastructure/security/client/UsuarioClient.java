package com.engcode.agendador_tarefas.infrastructure.security.client;

import com.engcode.agendador_tarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//Coloca o nome e a url que no caso vai ser uma variavél no aplicationProperties
@FeignClient (name = "usuario",  url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping ("/usuario")
    UsuarioDTO buscaUsuarioPorEmail (@RequestParam("email") String email, @RequestHeader("Authorization") String token);

}
