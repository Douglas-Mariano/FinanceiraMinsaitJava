package com.minsait.financeira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minsait.financeira.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
