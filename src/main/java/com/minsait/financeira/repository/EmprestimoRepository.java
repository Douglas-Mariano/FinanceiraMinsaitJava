package com.minsait.financeira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minsait.financeira.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
