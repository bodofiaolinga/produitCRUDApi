package com.api.produitAPI.integration;

import com.api.produitAPI.DTO.ProduitDTO;
import com.api.produitAPI.model.Produit;
import com.api.produitAPI.repository.ProduitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProduitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProduitRepository produitRepository;

    @BeforeEach
    void setup() {
        produitRepository.deleteAll(); // Nettoyer la BDD
    }

    @Test
    void testCreationProduit() throws Exception {
        ProduitDTO dto = new ProduitDTO();
        dto.setNom("chaussure");
        dto.setPrix(5000);

        mockMvc.perform(post("/produit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("chaussure"))
                .andExpect(jsonPath("$.prix").value(5000));
    }

    @Test
    void testAfficherToutProduit() throws Exception {

        Produit produit = new Produit();
        produit.setId(null);
        produit.setNom("livre");
        produit.setPrix(2000);

        produit = produitRepository.save(produit);


        mockMvc.perform(get("/produit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("livre"))
                .andExpect(jsonPath("$[0].prix").value(2000));
    }

    @Test
    void testMiseAJourProduit() throws Exception {
        Produit produit = new Produit();
        produit.setId(null);          // généralement null pour nouvel objet
        produit.setNom("livre");
        produit.setPrix(2000);

        produit = produitRepository.save(produit);


        ProduitDTO dto = new ProduitDTO();
        dto.setNom("livre");
        dto.setPrix(3500);

        mockMvc.perform(put("/produit/" + produit.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prix").value(3500));
    }

    @Test
    void testSupprimerProduit() throws Exception {
        Produit produit = new Produit();
        produit.setId(null);
        produit.setNom("livre");
        produit.setPrix(2000);

        produit = produitRepository.save(produit);


        mockMvc.perform(delete("/produit/" + produit.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("produit supprimer avec success!"));



        Assertions.assertTrue(produitRepository.findById(produit.getId()).isEmpty());
    }

    @Test
    void testAfficherProduitParId() throws Exception {
        Produit produit = new Produit();
        produit.setId(null);
        produit.setNom("livre");
        produit.setPrix(2000);

        produit = produitRepository.save(produit);


        mockMvc.perform(get("/produit/" + produit.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("livre"))
                .andExpect(jsonPath("$.prix").value(2000));
    }
}

