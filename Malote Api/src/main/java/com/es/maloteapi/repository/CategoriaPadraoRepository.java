package com.es.maloteapi.repository;

import com.es.maloteapi.entity.CategoriaPadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaPadraoRepository extends JpaRepository<CategoriaPadrao, Long> {

}
