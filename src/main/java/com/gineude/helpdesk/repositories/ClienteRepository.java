package com.gineude.helpdesk.repositories;

import com.gineude.helpdesk.domains.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
