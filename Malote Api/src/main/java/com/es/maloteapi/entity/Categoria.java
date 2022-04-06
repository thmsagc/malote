package com.es.maloteapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @JsonIgnoreProperties({"categoria"})
    @OneToMany(mappedBy = "categoria")
    private List<Renda> rendas;

    @JsonIgnoreProperties({"categoria"})
    @OneToMany(mappedBy = "categoria")
    private List<Renda> despesas;

    private String nome;

    public Categoria (Usuario usuario, String nome){
        this.usuario = usuario;
        this.nome = nome;
    }
}
