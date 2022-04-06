package com.es.maloteapi.service;

import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.request.CriarContaRequest;
import com.es.maloteapi.entity.response.CriarContaResponse;
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

    public List<Conta> getContas() {
        return this.contaRepository.findAllByUsuario(userService.getUser());
    }

    public CriarContaResponse createConta(CriarContaRequest contaRequest) {
        Conta conta = new Conta();
        conta.setUsuario(userService.getUser());
        conta.setNome(contaRequest.getNome());
        conta.setSaldo(contaRequest.getSaldoInicial());
        conta = this.contaRepository.save(conta);
        return CriarContaResponse.from(conta);
    }
}
