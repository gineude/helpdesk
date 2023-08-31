package com.gineude.helpdesk.repositories;

import com.gineude.helpdesk.domains.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
