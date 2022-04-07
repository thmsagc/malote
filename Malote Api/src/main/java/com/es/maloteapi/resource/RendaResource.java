package com.es.maloteapi.resource;

import com.es.maloteapi.entity.request.CriarRendaRequest;
import com.es.maloteapi.entity.response.StringUtils;
import com.es.maloteapi.service.CategoriaService;
import com.es.maloteapi.service.ContaService;
import com.es.maloteapi.service.RendaService;
import com.es.maloteapi.service.RendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Renda")
@RequestMapping("api")
public class RendaResource {

    private final RendaService rendaService;
    private final ContaService contaService;
    private final CategoriaService categoriaService;

    public RendaResource(RendaService rendaService, ContaService contaService, CategoriaService categoriaService) {
        this.rendaService = rendaService;
        this.contaService = contaService;
        this.categoriaService = categoriaService;
    }


    @ApiOperation(value = "This method is used to get all incomes by account of current user.")
    @GetMapping(value = "/rendas-por-conta")
    public @ResponseBody
    ResponseEntity<?> findRendasByConta(@RequestParam Long idConta) {
        return ResponseEntity.ok(rendaService.findAllByConta(contaService.getConta(idConta)));
    }

    @ApiOperation(value = "This method is used to get all incomes by category of current user.")
    @GetMapping(value = "/rendas-por-categoria")
    public @ResponseBody
    ResponseEntity<?> findRendasByContaCategoria(@RequestParam Long idConta, @RequestParam Long idCategoria) {
        return ResponseEntity.ok(rendaService.findAllByContaAndCategoria(contaService.getConta(idConta), categoriaService.getCategoria(idCategoria)));
    }

    @ApiOperation(value = "This method is used to get all incomes by a period.")
    @GetMapping(value = "/rendas-por-periodo")
    public @ResponseBody
    ResponseEntity<?> findRendasByPeriodo(@RequestParam Long idConta, @RequestParam String inicio, @RequestParam String fim) {
        return ResponseEntity.ok(rendaService.findAllByContaAndPeriodo(contaService.getConta(idConta),
                StringUtils.stringToLocalDateDdMmYyyy(inicio), StringUtils.stringToLocalDateDdMmYyyy(fim)));
    }

    @ApiOperation(value = "This method is used to create a new income for the current user.")
    @PostMapping(value = "/renda")
    public @ResponseBody
    ResponseEntity<?> createRenda(@RequestBody CriarRendaRequest request) {
        return ResponseEntity.ok(rendaService.createRenda(request));
    }

    @ApiOperation(value = "This method is used to delete a income for the current user.")
    @DeleteMapping(value = "/renda")
    public @ResponseBody
    void deleteRenda(@RequestParam Long idRenda) {
        rendaService.deleteRenda(idRenda);
    }
}
