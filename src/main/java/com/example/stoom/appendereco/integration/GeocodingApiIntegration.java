package com.example.stoom.appendereco.integration;

import com.example.stoom.appendereco.model.Endereco;
import com.example.stoom.appendereco.model.localizacao.Localizacao;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;

@Component
public class GeocodingApiIntegration {
    public static final String URL = "https://maps.googleapis.com/maps/api/geocode/json?";

    public Localizacao recuperarGeolocalizacao(Endereco endereco) throws ClassNotFoundException {
        Localizacao localizacao = new Localizacao();
        try {
            String cidade = removerAcentos(endereco.getCity()).replaceAll(" ", "+");
            String estado = removerAcentos(endereco.getState()).replaceAll(" ", "+");
            String pais = removerAcentos(endereco.getCountry()).replaceAll(" ", "+");
            String zipcode = endereco.getZipcode();

            String url = URL + "address=" + zipcode + cidade + estado + pais + "&key=AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output += line;
            }

            conn.disconnect();

            Gson gson = new Gson();
            localizacao = gson.fromJson(new String(output.getBytes()), Localizacao.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return localizacao;
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
