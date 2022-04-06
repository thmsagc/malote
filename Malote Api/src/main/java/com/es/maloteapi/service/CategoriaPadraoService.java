package com.es.maloteapi.service;

import com.es.maloteapi.entity.CategoriaPadrao;
import com.es.maloteapi.entity.request.CategoriaPadraoRequest;
import com.es.maloteapi.entity.response.CategoriaPadraoResponse;
import com.es.maloteapi.exception.BadRequestAlertException;
import com.es.maloteapi.exception.ProblemKey;
import com.es.maloteapi.repository.CategoriaPadraoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaPadraoService {
    private final CategoriaPadraoRepository categoriaPadraoRepository;

    public CategoriaPadraoService(CategoriaPadraoRepository categoriaPadraoRepository) {
        this.categoriaPadraoRepository = categoriaPadraoRepository;
    }

    public List<CategoriaPadrao> findAll() {
        return this.categoriaPadraoRepository.findAll();
    }

    public CategoriaPadraoResponse createCategoriaPadrao(CategoriaPadraoRequest categoriaPadraoRequest) {
        CategoriaPadrao categoriaPadrao = new CategoriaPadrao();
        categoriaPadrao.setNome(categoriaPadraoRequest.getNome());
        categoriaPadrao = categoriaPadraoRepository.save(categoriaPadrao);
        return CategoriaPadraoResponse.from(categoriaPadrao);
    }

    public void deleteCategoriaPadrao(Long idCategoriaPadrao) {
        CategoriaPadrao categoriaPadrao = categoriaPadraoRepository.findById(idCategoriaPadrao).orElseThrow(
                () -> new BadRequestAlertException(ProblemKey.CATEGORIA_PADRAO_INEXISTENTE));
        this.categoriaPadraoRepository.delete(categoriaPadrao);
    }
}
