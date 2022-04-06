package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriarCategoriaResponse {
    private Long id;
    private String nome;

    public static CriarCategoriaResponse from(Categoria categoria) {
        CriarCategoriaResponse criarCategoriaResponse = new CriarCategoriaResponse();
        criarCategoriaResponse.setId(categoria.getId());
        criarCategoriaResponse.setNome(categoria.getNome());
        return criarCategoriaResponse;
    }
}
