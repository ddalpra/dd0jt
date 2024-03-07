package org.acme.dalpra.ui.view.persone.bean;

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.acme.dalpra.ui.view.persone.entity.Persone;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class PersoneBean implements Serializable {

    private List<Persone> Persone;
    private String apiPath = "http://localhost:8095/api/persone";

    PersoneBean() {

    }

    public void tutteLePersone() {
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new GsonBuilder.create();    

        try{
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        URI uri = new URI(apiPath);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .timeout(Duration.ofSeconds(2))
                .build();
        
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        objectMapper

        System.out.println(response.body());
        }catch(Exception ex){
            System.out.println("****ERRORE************");
            ex.printStackTrace();
        }
    }

}
