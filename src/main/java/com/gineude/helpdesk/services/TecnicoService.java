package com.gineude.helpdesk.services;

import com.gineude.helpdesk.domains.Tecnico;
import com.gineude.helpdesk.services.exceptions.ObjectNotFoundException;
import com.gineude.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }
}
