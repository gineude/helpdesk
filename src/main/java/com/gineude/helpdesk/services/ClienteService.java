package com.gineude.helpdesk.services;

import com.gineude.helpdesk.domains.Pessoa;
import com.gineude.helpdesk.domains.Cliente;
import com.gineude.helpdesk.dtos.ClienteDTO;
import com.gineude.helpdesk.repositories.PessoaRepository;
import com.gineude.helpdesk.repositories.ClienteRepository;
import com.gineude.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.gineude.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        validacaoDuplicidade(clienteDTO);
        Cliente newObj = new Cliente(clienteDTO);
        return repository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO dto) {
        dto.setId(id);
        Cliente oldObj = findById(id);
        validacaoDuplicidade(dto);
        oldObj = new Cliente(dto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);

        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviços e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validacaoDuplicidade(ClienteDTO clienteDTO) {
        Optional<Pessoa> objDB = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if (objDB.isPresent() && objDB.get().getId() != clienteDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        objDB = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if (objDB.isPresent() && objDB.get().getId() != clienteDTO.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }
}
