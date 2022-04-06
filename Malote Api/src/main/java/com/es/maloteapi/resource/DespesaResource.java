package com.es.maloteapi.resource;

import com.es.maloteapi.entity.request.CriarDespesaRequest;
import com.es.maloteapi.service.CategoriaService;
import com.es.maloteapi.service.ContaService;
import com.es.maloteapi.service.DespesaService;
import com.es.maloteapi.service.DespesaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Despesa")
@RequestMapping("api")
public class DespesaResource {
    
    private final DespesaService despesaService;
    private final ContaService contaService;
    private final CategoriaService categoriaService;

    public DespesaResource(DespesaService despesaService, ContaService contaService, CategoriaService categoriaService) {
        this.despesaService = despesaService;
        this.contaService = contaService;
        this.categoriaService = categoriaService;
    }
    

    @ApiOperation(value = "This method is used to get all expenses by account of current user.")
    @GetMapping(value = "/despesas-por-conta")
    public @ResponseBody
    ResponseEntity<?> findDespesasByConta(@RequestParam Long idConta) {
        return ResponseEntity.ok(despesaService.findAllByConta(contaService.getConta(idConta)));
    }

    @ApiOperation(value = "This method is used to get all expense by category of current user.")
    @GetMapping(value = "/despesas-por-categoria")
    public @ResponseBody
    ResponseEntity<?> findDespesasByCategoria(@RequestParam Long idCategoria) {
        return ResponseEntity.ok(despesaService.findAllByCategoria(categoriaService.getCategoria(idCategoria)));
    }

    @ApiOperation(value = "This method is used to create a new expense for the current user.")
    @PostMapping(value = "/despesa")
    public @ResponseBody
    ResponseEntity<?> createDespesa(@RequestBody CriarDespesaRequest request) {
        return ResponseEntity.ok(despesaService.createDespesa(request));
    }

    @ApiOperation(value = "This method is used to delete a expense for the current user.")
    @DeleteMapping(value = "/despesa")
    public @ResponseBody
    void deleteDespesa(@RequestParam Long idDespesa) {
        despesaService.deleteDespesa(idDespesa);
    }
}
