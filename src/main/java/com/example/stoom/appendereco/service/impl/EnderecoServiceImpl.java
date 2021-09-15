package com.example.stoom.appendereco.service.impl;

import com.example.stoom.appendereco.integration.GeocodingApiIntegration;
import com.example.stoom.appendereco.model.Endereco;
import com.example.stoom.appendereco.model.localizacao.Localizacao;
import com.example.stoom.appendereco.repository.EnderecoRepository;
import com.example.stoom.appendereco.service.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private EnderecoRepository enderecoRepository;

    private GeocodingApiIntegration geocodingApiIntegration;

    public EnderecoServiceImpl(final EnderecoRepository enderecoRepository, GeocodingApiIntegration geocodingApiIntegration){
        this.enderecoRepository = enderecoRepository;
        this.geocodingApiIntegration = geocodingApiIntegration;
    }

    @Override
    public Endereco inserir(Endereco endereco) throws Exception {
//        Testei as duas variáveis pois assumi que se for informado só uma, tenho que fazer a integração da mesma forma.
        if(isNullOrEmpty(endereco.getLatitude()) || isNullOrEmpty(endereco.getLongitude())){
            Localizacao localizacao = this.recuperarLatLng(endereco);
            if(localizacao == null){
                endereco.setLatitude("NOT FOUND");
                endereco.setLongitude("NOT FOUND");
            }else{
                endereco.setLatitude(localizacao.getResults().get(0).getGeometry().getLocation().getLat());
                endereco.setLongitude(localizacao.getResults().get(0).getGeometry().getLocation().getLng());
            }
        }
        return this.enderecoRepository.save(endereco);
    }

    @Override
    public List<Endereco> consultar() throws Exception {
        return this.enderecoRepository.findAll();
    }

    @Override
    public Optional<Endereco> detalhar(Long id) throws Exception {
        return this.enderecoRepository.findById(id);
    }

    @Override
    public Endereco atualizar(Endereco endereco) throws Exception {
        return this.enderecoRepository.save(endereco);
    }

    @Override
    public void deletar(Long id) throws Exception {
        this.enderecoRepository.deleteById(id);
    }

    public Boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    private Localizacao recuperarLatLng(Endereco endereco) throws ClassNotFoundException {
        return this.geocodingApiIntegration.recuperarGeolocalizacao(endereco);
    }

}
