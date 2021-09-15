package com.example.stoom.appendereco;

import com.example.stoom.appendereco.model.Endereco;
import com.example.stoom.appendereco.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppenderecoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void TesteInserir() throws Exception {
        Endereco endereco = new Endereco();
        endereco.setStreetName("Rua top");
        endereco.setNumber(10);
        endereco.setComplement("Rua da dhora");
        endereco.setNeighbourhood("São francisco");
        endereco.setCity("São Sebastião");
        endereco.setState("Distrito Federal");
        endereco.setCountry("Brasil");
        endereco.setZipcode("71693-010");

        mockMvc.perform(post("/endereco")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(status().isCreated());
    }

    @Test
    void consultar() throws Exception{
        Endereco endereco = new Endereco();
        mockMvc.perform(get("/endereco")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(status().isOk());
    }

    @Test
    void detalhar() throws Exception{
        Long id = 2L;
        mockMvc.perform(get("/endereco/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(id)))
                .andExpect(status().isOk());
    }

    @Test
    void atualizar() throws Exception {
        Endereco endereco = new Endereco();
        endereco.setId(2L);
        endereco.setStreetName("Rua top ALTERADA");
        endereco.setNumber(10);
        endereco.setComplement("Rua da dhora");
        endereco.setNeighbourhood("São francisco");
        endereco.setCity("São Sebastião");
        endereco.setState("Distrito Federal");
        endereco.setCountry("Brasil");
        endereco.setZipcode("71693-010");

        mockMvc.perform(put("/endereco")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(status().isOk());
    }

    @Test
    void deletar() throws Exception{
        Long id = 3L;
        mockMvc.perform(delete("/endereco/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(id)))
                .andExpect(status().isNoContent());
    }
}
