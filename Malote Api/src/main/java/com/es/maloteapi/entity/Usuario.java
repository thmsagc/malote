package com.es.maloteapi.entity;

import com.es.maloteapi.entity.Conta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean categoriasPadrao;
    private LocalDate dataVerificacao;

    @JsonIgnoreProperties({"usuario"})
    @OneToMany(mappedBy = "usuario")
    private List<Conta> contas;

    @JsonIgnoreProperties({"usuario"})
    @OneToMany(mappedBy = "usuario")
    private List<Categoria> categorias;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        this.dataVerificacao = LocalDate.now();
    }
}
