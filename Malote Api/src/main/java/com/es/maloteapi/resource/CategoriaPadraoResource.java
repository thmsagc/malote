package com.es.maloteapi.resource;

import com.es.maloteapi.entity.request.CategoriaPadraoRequest;
import com.es.maloteapi.service.CategoriaPadraoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Categoria Padrao")
@RequestMapping("api")
public class CategoriaPadraoResource {

    private final CategoriaPadraoService categoriaPadraoService;

    public CategoriaPadraoResource(CategoriaPadraoService categoriaPadraoService) {
        this.categoriaPadraoService = categoriaPadraoService;
    }

    @ApiOperation(value = "This method is used to get all standard categories.")
    @GetMapping(value = "/categorias-padrao")
    public @ResponseBody
    ResponseEntity<?> getCategoriasPadrao() {
        return ResponseEntity.ok(categoriaPadraoService.findAll());
    }

    @ApiOperation(value = "This method is used to create a standard category.")
    @PostMapping(value = "/categoria-padrao")
    public @ResponseBody
    ResponseEntity<?> createCategoriaPadrao(@RequestBody CategoriaPadraoRequest categoriaPadraoRequest) {
        return ResponseEntity.ok(categoriaPadraoService.createCategoriaPadrao(categoriaPadraoRequest));
    }

    @ApiOperation(value = "This method is used to delete a standard category.")
    @DeleteMapping(value = "/categoria-padrao")
    public @ResponseBody
    void deleteCategoriaPadrao(@RequestParam Long idCategoriaPadrao) {
        categoriaPadraoService.deleteCategoriaPadrao(idCategoriaPadrao);
    }
}