package com.gineude.helpdesk.services;

import com.gineude.helpdesk.domains.Pessoa;
import com.gineude.helpdesk.domains.Tecnico;
import com.gineude.helpdesk.dtos.TecnicoDTO;
import com.gineude.helpdesk.repositories.PessoaRepository;
import com.gineude.helpdesk.services.exceptions.DataIntegrityViolationException;
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

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validacaoDuplicidade(tecnicoDTO);
        Tecnico newObj = new Tecnico(tecnicoDTO);
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, TecnicoDTO dto) {
        dto.setId(id);
        Tecnico oldObj = findById(id);
        validacaoDuplicidade(dto);
        oldObj = new Tecnico(dto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviços e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validacaoDuplicidade(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> objDB = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if (objDB.isPresent() && objDB.get().getId() != tecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        objDB = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if (objDB.isPresent() && objDB.get().getId() != tecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }
}
