package com.gineude.helpdesk.repositories;

import com.gineude.helpdesk.domains.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
