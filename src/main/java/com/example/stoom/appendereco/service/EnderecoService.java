package com.example.stoom.appendereco.service;

import com.example.stoom.appendereco.model.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoService {

    Endereco inserir(Endereco endereco) throws Exception;
    List<Endereco> consultar() throws Exception;
    Optional<Endereco> detalhar(Long id) throws Exception;
    Endereco atualizar(Endereco endereco) throws Exception;
    void deletar(Long id) throws Exception;
}
