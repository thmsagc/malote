package com.es.maloteapi.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriarUsuarioRequest {
    private String username;
    private String password;
    private Boolean criarCategoriasPadrao;
}
