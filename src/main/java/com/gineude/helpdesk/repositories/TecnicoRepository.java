package com.gineude.helpdesk.repositories;

import com.gineude.helpdesk.domains.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
