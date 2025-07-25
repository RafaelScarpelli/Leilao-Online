package com.github.RafaelScarpelli.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.RafaelScarpelli.backend.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("from Pessoa where email=:email")
    public Page<Pessoa> buscarEmail(@Param("email") String email, Pageable pageable);
}