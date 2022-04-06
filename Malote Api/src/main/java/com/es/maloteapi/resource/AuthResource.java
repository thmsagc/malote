package com.es.maloteapi.resource;

import com.es.maloteapi.auth.JwtUtil;
import com.es.maloteapi.entity.Usuario;
import com.es.maloteapi.entity.request.CriarUsuarioRequest;
import com.es.maloteapi.entity.response.CriarUsuarioResponse;
import com.es.maloteapi.exception.IncorrectCredentialsAlertException;
import com.es.maloteapi.exception.ProblemKey;
import com.es.maloteapi.service.CategoriaService;
import com.es.maloteapi.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Auth")
@RequestMapping("api")
public class AuthResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation(value = "This method is used to get an authorization token for a user.")
    @PostMapping(value = "/auth")
    public @ResponseBody
    String getAuthToken(@RequestParam String username, @RequestParam String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (Exception exception) {
            throw new IncorrectCredentialsAlertException(ProblemKey.CREDENCIAIS_INVALIDAS);
        }
        return jwtUtil.generateToken(username);
    }

    @ApiOperation(value = "This method is used to create a new user.")
    @PostMapping(value = "/user")
    public @ResponseBody
    ResponseEntity<?> createUser(@RequestBody CriarUsuarioRequest criarUsuarioRequest) {
        CriarUsuarioResponse usuario = usuarioService.createUser(criarUsuarioRequest);
        if(criarUsuarioRequest.getCriarCategoriasPadrao())
            categoriaService.createCategoriasPadrao(usuarioService.findById(usuario.getId()));
        return ResponseEntity.ok(usuario);
    }

    @ApiOperation(value = "This method is used to get the current username.")
    @GetMapping(value = "/current-username")
    public @ResponseBody
    ResponseEntity<?> getCurrentUsername() {
        return ResponseEntity.ok(usuarioService.getUser().getUsername());
    }

    @ApiOperation(value = "This method is used to get a list of all users.")
    @GetMapping(value = "/users")
    public @ResponseBody
    List<Usuario> getUsers() {
        return usuarioService.findAll();
    }
}
