package com.gineude.helpdesk.services;

import com.gineude.helpdesk.domains.Pessoa;
import com.gineude.helpdesk.repositories.PessoaRepository;
import com.gineude.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = repository.findByEmail(email);

        if (user.isPresent()) {
            Pessoa pessoa = user.get();
            return new UserSS(pessoa.getId(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getPerfis());
        }
        throw new UsernameNotFoundException(email);
    }
}
