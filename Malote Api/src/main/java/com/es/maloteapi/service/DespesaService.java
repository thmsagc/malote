package com.es.maloteapi.service;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Despesa;
import com.es.maloteapi.entity.request.CriarDespesaRequest;
import com.es.maloteapi.entity.response.CriarDespesaResponse;
import com.es.maloteapi.entity.response.DespesaResponse;
import com.es.maloteapi.exception.BadRequestAlertException;
import com.es.maloteapi.exception.NotFoundAlertException;
import com.es.maloteapi.exception.ProblemKey;
import com.es.maloteapi.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final CategoriaService categoriaService;
    private final ContaService contaService;

    public DespesaService(DespesaRepository despesaRepository, CategoriaService categoriaService, ContaService contaService) {
        this.despesaRepository = despesaRepository;
        this.categoriaService = categoriaService;
        this.contaService = contaService;
    }

    public List<DespesaResponse> findAllByDataBetween(LocalDate inicio, LocalDate fim) {
        return DespesaResponse.from(despesaRepository.findAllByDataBetween(inicio, fim));
    }

    public List<DespesaResponse> findAllByCategoria(Categoria categoria) {
        return DespesaResponse.from(despesaRepository.findAllByCategoria(categoria));
    }

    public List<DespesaResponse> findAllByConta(Conta conta) {
        return DespesaResponse.from(despesaRepository.findAllByConta(conta));
    }

    public CriarDespesaResponse createDespesa(CriarDespesaRequest criarDespesaRequest) {
        Despesa despesa = new Despesa();

        Conta conta = contaService.getConta(criarDespesaRequest.getConta());
        if(criarDespesaRequest.getValor().compareTo(conta.getSaldoAtual()) == 1)
            throw new BadRequestAlertException(ProblemKey.SALDO_INSUFICIENTE);
        else {
            conta.setSaldoAtual(conta.getSaldoAtual().subtract(criarDespesaRequest.getValor()));
            contaService.save(conta);
        }

        despesa.setConta(contaService.getConta(criarDespesaRequest.getConta()));
        despesa.setNome(criarDespesaRequest.getNome());
        despesa.setDescricao(criarDespesaRequest.getDescricao());
        despesa.setValor(criarDespesaRequest.getValor());
        despesa.setCategoria(categoriaService.getCategoria(criarDespesaRequest.getCategoria()));
        despesa.setData(LocalDate.now());
        return CriarDespesaResponse.from(despesaRepository.save(despesa));
    }

    public void deleteDespesa(Long idDespesa) {
        Despesa despesa = despesaRepository.findById(idDespesa).orElseThrow(
                () -> new NotFoundAlertException(ProblemKey.DESPESA_INEXISTENTE));

        Conta conta = despesa.getConta();
        conta.setSaldoAtual(conta.getSaldoAtual().add(despesa.getValor()));
        contaService.save(conta);

        despesaRepository.delete(despesa);
    }
}
