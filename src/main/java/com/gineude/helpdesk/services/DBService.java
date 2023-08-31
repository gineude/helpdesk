package com.gineude.helpdesk.services;

import com.gineude.helpdesk.domains.Chamado;
import com.gineude.helpdesk.domains.Cliente;
import com.gineude.helpdesk.domains.Tecnico;
import com.gineude.helpdesk.domains.enums.Perfil;
import com.gineude.helpdesk.domains.enums.Prioridade;
import com.gineude.helpdesk.domains.enums.Status;
import com.gineude.helpdesk.repositories.ChamadoRepository;
import com.gineude.helpdesk.repositories.ClienteRepository;
import com.gineude.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "08123713720", "valdir@mail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "11421775301", "torvalds@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
