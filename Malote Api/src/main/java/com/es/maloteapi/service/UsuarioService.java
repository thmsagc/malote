package com.es.maloteapi.service;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Usuario;
import com.es.maloteapi.entity.request.CriarUsuarioRequest;
import com.es.maloteapi.entity.response.CriarUsuarioResponse;
import com.es.maloteapi.repository.UserRepository;
import com.es.maloteapi.entity.CategoriaPadrao;
import com.es.maloteapi.entity.request.CategoriaPadraoRequest;
import com.es.maloteapi.entity.response.CategoriaPadraoResponse;
import com.es.maloteapi.exception.BadRequestAlertException;
import com.es.maloteapi.exception.InternalErrorAlertException;
import com.es.maloteapi.exception.NotFoundAlertException;
import com.es.maloteapi.exception.ProblemKey;
import com.es.maloteapi.repository.CategoriaPadraoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CategoriaPadraoService categoriaPadraoService;

    public UsuarioService(UserRepository userRepository, CategoriaPadraoService categoriaPadraoService) {
        this.userRepository = userRepository;
        this.categoriaPadraoService = categoriaPadraoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundAlertException(ProblemKey.USUARIO_INEXISTENTE));
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }

    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    public CriarUsuarioResponse createUser(CriarUsuarioRequest criarUsuarioRequest) {
        Usuario usuario = userRepository.findByUsername(criarUsuarioRequest.getUsername()).orElse(null);
        if(usuario == null) {
            usuario = new Usuario();
            usuario.setUsername(criarUsuarioRequest.getUsername());
            usuario.setPassword(criarUsuarioRequest.getPassword());
            usuario = userRepository.save(usuario);
            return CriarUsuarioResponse.from(usuario);
        } throw new BadRequestAlertException(ProblemKey.USUARIO_EXISTENTE);
    }

    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundAlertException(ProblemKey.USUARIO_INEXISTENTE));
    }

    public Usuario findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundAlertException(ProblemKey.USUARIO_INEXISTENTE));
    }

    public Usuario getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return findByUsername(((UserDetails)principal).getUsername());
        } else {
            throw new InternalErrorAlertException(ProblemKey.ERRO_AO_BUSCAR_USUARIO);
        }
    }

    public Usuario save(Usuario usuario) {
        return this.userRepository.save(usuario);
    }
}
