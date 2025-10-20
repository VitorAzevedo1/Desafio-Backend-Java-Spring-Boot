package com.rocketseat.minicrm.repository;

import com.rocketseat.minicrm.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
