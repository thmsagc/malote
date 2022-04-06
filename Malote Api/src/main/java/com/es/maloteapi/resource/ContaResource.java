package com.es.maloteapi.resource;

import com.es.maloteapi.entity.request.CriarContaRequest;
import com.es.maloteapi.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Conta")
@CrossOrigin(origins = "*")
public class ContaResource {

    private final ContaService contaService;

    public ContaResource(ContaService contaService) {
        this.contaService = contaService;
    }

    @ApiOperation(value = "This method is used to get all accounts of current user.")
    @GetMapping(value = "/contas")
    public @ResponseBody
    ResponseEntity<?> getContas() {
        return ResponseEntity.ok(contaService.getContas());
    }

    @ApiOperation(value = "This method is used to create a new account for the current user.")
    @PostMapping(value = "/conta")
    public @ResponseBody
    ResponseEntity<?> createConta(@RequestBody CriarContaRequest request) {
        return ResponseEntity.ok(contaService.createConta(request));
    }
}
