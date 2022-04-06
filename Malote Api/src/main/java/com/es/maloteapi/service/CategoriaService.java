package com.es.maloteapi.service;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Usuario;
import com.es.maloteapi.entity.request.CriarCategoriaRequest;
import com.es.maloteapi.entity.request.CriarUsuarioRequest;
import com.es.maloteapi.entity.response.CriarCategoriaResponse;
import com.es.maloteapi.exception.BadRequestAlertException;
import com.es.maloteapi.exception.NotFoundAlertException;
import com.es.maloteapi.exception.ProblemKey;
import com.es.maloteapi.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private final UsuarioService userService;
    private final CategoriaRepository categoriaRepository;
    private final CategoriaPadraoService categoriaPadraoService;


    public CategoriaService(UsuarioService userService, CategoriaRepository categoriaRepository, CategoriaPadraoService categoriaPadraoService) {
        this.userService = userService;
        this.categoriaRepository = categoriaRepository;
        this.categoriaPadraoService = categoriaPadraoService;
    }

    public Categoria createCategoria(Usuario usuario, String nome) {
        return categoriaRepository.save(new Categoria(usuario, nome));
    }

    public CriarCategoriaResponse createCategoria(CriarCategoriaRequest criarCategoriaRequest) {
        return CriarCategoriaResponse.from(categoriaRepository.save(new Categoria(userService.getUser(), criarCategoriaRequest.getNome())));
    }

    public void createCategoriasPadrao(Usuario usuario) {
        if (!usuario.getCategoriasPadrao()) {
            List<Categoria> categorias = new ArrayList<>();
            categoriaPadraoService.findAll().forEach(categoriaPadrao -> {
                categorias.add(createCategoria(usuario, categoriaPadrao.getNome()));
            });
            usuario.setCategorias(categorias);
            usuario.setCategoriasPadrao(true);
            userService.save(usuario);
        } else {
            throw new BadRequestAlertException(ProblemKey.USUARIO_JA_POSSUI_CATEGORIAS_PADRAO);
        }
    }

    public void deleteCategoria(Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(
                () -> new NotFoundAlertException(ProblemKey.CATEGORIA_INEXISTENTE));
        categoriaRepository.delete(categoria);
    }

    public List<Categoria> getCategorias() {
        return this.categoriaRepository.findAllByUsuario(userService.getUser());
    }

    public Categoria getCategoria(Long idCategoria) {
        return categoriaRepository.findById(idCategoria).orElseThrow(
                () -> new NotFoundAlertException(ProblemKey.CATEGORIA_INEXISTENTE));
    }
}
