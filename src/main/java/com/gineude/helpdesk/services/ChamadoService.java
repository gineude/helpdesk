package com.gineude.helpdesk.services;

import com.gineude.helpdesk.domains.Chamado;
import com.gineude.helpdesk.domains.Cliente;
import com.gineude.helpdesk.domains.Tecnico;
import com.gineude.helpdesk.dtos.ChamadoDTO;
import com.gineude.helpdesk.repositories.ChamadoRepository;
import com.gineude.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        return repository.save(newChamado(chamadoDTO));
    }

    public Chamado update(Integer id, ChamadoDTO dto) {
        dto.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(dto);
        return repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO dto) {
        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
        Cliente cliente = clienteService.findById(dto.getCliente());

        Chamado chamado = new Chamado(dto);
        if (dto.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }
        chamado.setCliente(cliente);
        chamado.setTecnico(tecnico);
        return chamado;
    }
}
