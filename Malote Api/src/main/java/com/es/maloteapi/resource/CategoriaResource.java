package com.es.maloteapi.resource;

import com.es.maloteapi.entity.request.CriarCategoriaRequest;
import com.es.maloteapi.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Categoria")
@CrossOrigin(origins = "*")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @ApiOperation(value = "This method is used to get all categories of current user.")
    @GetMapping(value = "/categorias")
    public @ResponseBody
    ResponseEntity<?> getCategorias() {
        return ResponseEntity.ok(categoriaService.getCategorias());
    }

    @ApiOperation(value = "This method is used to create a category for current user.")
    @PostMapping(value = "/categoria")
    public @ResponseBody
    ResponseEntity<?> createCategoria(@RequestBody CriarCategoriaRequest criarCategoriaRequest) {
        return ResponseEntity.ok(categoriaService.createCategoria(criarCategoriaRequest));
    }

    @ApiOperation(value = "This method is used to delete a category for current user.")
    @DeleteMapping(value = "/categoria")
    public void deleteCategoria(@RequestParam Long idCategoria) {
        categoriaService.deleteCategoria(idCategoria);
    }
}