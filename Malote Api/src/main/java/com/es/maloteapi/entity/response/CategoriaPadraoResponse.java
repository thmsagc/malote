package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.CategoriaPadrao;
import com.es.maloteapi.entity.Conta;
import lombok.Data;

@Data
public class CategoriaPadraoResponse {
    private Long id;
    private String nome;

    public static CategoriaPadraoResponse from(CategoriaPadrao categoriaPadrao) {
        CategoriaPadraoResponse categoriaPadraoResponse = new CategoriaPadraoResponse();
        categoriaPadraoResponse.setId(categoriaPadrao.getId());
        categoriaPadraoResponse.setNome(categoriaPadrao.getNome());
        return categoriaPadraoResponse;
    }
}
