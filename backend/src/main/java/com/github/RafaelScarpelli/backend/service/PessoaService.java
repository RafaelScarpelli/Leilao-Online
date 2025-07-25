package com.github.RafaelScarpelli.backend.service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.github.RafaelScarpelli.backend.exception.BusinessException;
import com.github.RafaelScarpelli.backend.exception.NotFoundException;
import com.github.RafaelScarpelli.backend.model.Pessoa;
import com.github.RafaelScarpelli.backend.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailService emailService;

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("pessoa.notfound",
                        new Object[] { id }, LocaleContextHolder.getLocale())));
    }

    public Page<Pessoa> buscarTodos(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Pessoa inserir(Pessoa pessoa) {
        Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
        // emailService.enviarEmailSimples(pessoaCadastrada.getEmail(), "Cadastro com
        // Sucesso", "Cadastro no Sistema de Leilão XXX foi feita com sucesos");

        return pessoaRepository.save(pessoa);
    }

    private void enviarEmailSucesso(Pessoa pessoa) {
        Context context = new Context();
        context.setVariable("nome", pessoa.getNome());
        emailService.emailTemplate(pessoa.getEmail(), "Cadastro Sucesso", context, "cadastrosSucesso");

    }

    public Pessoa alterar(Pessoa pessoa) {
        Pessoa pessoaBanco = buscarPorId(pessoa.getId());

        pessoa.setNome(pessoa.getNome());
        pessoa.setEmail(pessoa.getEmail());

        return pessoaRepository.save(pessoaBanco);
    }

    public void deletar(Long id) {
        Pessoa pessoaBanco = buscarPorId(id);

        pessoaRepository.delete(pessoaBanco);
    }

}
