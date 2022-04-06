package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriarUsuarioResponse {
    private Long id;
    private String username;
    private String password;
    private Boolean criarCategoriasPadrao;

    public static CriarUsuarioResponse from(Usuario usuario) {
        CriarUsuarioResponse usuarioResponse = new CriarUsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setUsername(usuario.getUsername());
        return usuarioResponse;
    }
}
