package com.es.maloteapi.service;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.request.CriarContaRequest;
import com.es.maloteapi.entity.response.ContaResponse;
import com.es.maloteapi.entity.response.CriarContaResponse;
import com.es.maloteapi.exception.NotFoundAlertException;
import com.es.maloteapi.exception.ProblemKey;
import com.es.maloteapi.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {
    private final UsuarioService userService;
    private final ContaRepository contaRepository;

    public ContaService(UsuarioService userService, ContaRepository contaRepository) {
        this.userService = userService;
        this.contaRepository = contaRepository;
    }

    public List<ContaResponse> getContas() {
        return ContaResponse.from(this.contaRepository.findAllByUsuario(userService.getUser()));
    }

    public CriarContaResponse createConta(CriarContaRequest contaRequest) {
        Conta conta = new Conta();
        conta.setUsuario(userService.getUser());
        conta.setNome(contaRequest.getNome());
        conta.setSaldoInicial(contaRequest.getSaldoInicial());
        conta.setSaldoAtual(contaRequest.getSaldoInicial());
        conta = this.contaRepository.save(conta);
        return CriarContaResponse.from(conta);
    }

    public void deleteConta(Long idConta) {
        Conta conta = contaRepository.findById(idConta).orElseThrow(
                () -> new NotFoundAlertException(ProblemKey.CONTA_INEXISTENTE));
        contaRepository.delete(conta);
    }

    public Conta getConta(Long idConta) {
        return contaRepository.findById(idConta).orElseThrow(
                () -> new NotFoundAlertException(ProblemKey.CONTA_INEXISTENTE));
    }

    public Conta save(Conta conta) {
        return contaRepository.save(conta);
    }
}
