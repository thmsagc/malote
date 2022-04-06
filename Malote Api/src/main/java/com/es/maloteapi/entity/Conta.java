package com.es.maloteapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.web.servlet.View;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @JsonIgnoreProperties({"conta"})
    @OneToMany(mappedBy = "conta")
    private List<Renda> rendas;

    @JsonIgnoreProperties({"conta"})
    @OneToMany(mappedBy = "conta")
    private List<Despesa> despesas;

    private String nome;
    private BigDecimal saldoInicial;
    private BigDecimal saldoAtual;
}
