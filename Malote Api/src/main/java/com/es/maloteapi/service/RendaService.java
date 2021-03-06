package com.es.maloteapi.service;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Renda;
import com.es.maloteapi.entity.Usuario;
import com.es.maloteapi.entity.request.CriarRendaRequest;
import com.es.maloteapi.entity.response.CriarRendaResponse;
import com.es.maloteapi.entity.response.RendaResponse;
import com.es.maloteapi.entity.response.StringUtils;
import com.es.maloteapi.exception.NotFoundAlertException;
import com.es.maloteapi.exception.ProblemKey;
import com.es.maloteapi.repository.RendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RendaService {

    private final RendaRepository rendaRepository;
    private final CategoriaService categoriaService;
    private final ContaService contaService;

    public RendaService(RendaRepository rendaRepository, CategoriaService categoriaService, ContaService contaService) {
        this.rendaRepository = rendaRepository;
        this.categoriaService = categoriaService;
        this.contaService = contaService;
    }

    public List<RendaResponse> findAllByContaAndCategoria(Conta conta, Categoria categoria) {
        return RendaResponse.from(rendaRepository.findAllByContaAndCategoria(conta, categoria));
    }

    public List<RendaResponse> findAllByConta(Conta conta) {
        return RendaResponse.from(rendaRepository.findAllByConta(conta));
    }

    public List<RendaResponse> findAllByContaAndPeriodo(Conta conta, LocalDate inicio, LocalDate fim) {
        return RendaResponse.from(rendaRepository.findAllByContaAndDataBetween(conta, inicio, fim));
    }

    public List<RendaResponse> findAllByContaAndRecorrencia(Conta conta, String recorrencia) {
        return RendaResponse.from(rendaRepository.findAllByContaAndRecorrencia(conta, recorrencia));
    }

    public CriarRendaResponse createRenda(CriarRendaRequest criarRendaRequest) {
        Renda renda = new Renda();
        Conta conta = contaService.getConta(criarRendaRequest.getConta());
        renda.setConta(conta);
        renda.setNome(criarRendaRequest.getNome());
        renda.setDescricao(criarRendaRequest.getDescricao());
        renda.setValor(criarRendaRequest.getValor());
        renda.setCategoria(categoriaService.getCategoria(criarRendaRequest.getCategoria()));
        renda.setData(StringUtils.stringToLocalDateDdMmYyyy(criarRendaRequest.getData()));
        conta.setSaldoAtual(conta.getSaldoAtual().add(criarRendaRequest.getValor()));
        if(!criarRendaRequest.getRecorrencia().isEmpty())
            renda.setRecorrencia(criarRendaRequest.getRecorrencia());
        else
            renda.setRecorrencia(Renda.SEM_RECORRENCIA);
        contaService.save(conta);
        return CriarRendaResponse.from(rendaRepository.save(renda));
    }

    public void deleteRenda(Long idRenda) {
        Renda renda = rendaRepository.findById(idRenda).orElseThrow(
                () -> new NotFoundAlertException(ProblemKey.DESPESA_INEXISTENTE));

        Conta conta = renda.getConta();
        conta.setSaldoAtual(conta.getSaldoAtual().subtract(renda.getValor()));
        contaService.save(conta);

        rendaRepository.delete(renda);
    }
}
