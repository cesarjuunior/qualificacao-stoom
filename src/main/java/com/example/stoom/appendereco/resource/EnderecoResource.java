package com.example.stoom.appendereco.resource;

import com.example.stoom.appendereco.model.Endereco;
import com.example.stoom.appendereco.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("endereco")
public class EnderecoResource {

    private EnderecoService enderecoService;

    public EnderecoResource(final EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> inserir(@RequestBody Endereco endereco) throws Exception {
        Endereco enderecoRetorno = this.enderecoService.inserir(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRetorno);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> consultar() throws Exception {
        List<Endereco> enderecoList = this.enderecoService.consultar();
        return ResponseEntity.status(HttpStatus.OK).body(enderecoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Endereco>> detalhar(@PathVariable Long id) throws Exception {
        Optional<Endereco> enderecoRetorno = this.enderecoService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRetorno);
    }

    @PutMapping
    public ResponseEntity<Endereco> atualizar(@RequestBody Endereco endereco) throws Exception {
        Endereco enderecoRetorno = this.enderecoService.atualizar(endereco);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRetorno);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) throws Exception {
        this.enderecoService.deletar(id);
    }
}
