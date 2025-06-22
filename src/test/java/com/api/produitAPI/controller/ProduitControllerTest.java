package com.api.produitAPI.controller;


import com.api.produitAPI.DTO.ProduitDTO;
import com.api.produitAPI.DTO.ProduitMapper;
import com.api.produitAPI.service.ProduitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProduitController.class)
public class ProduitControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
private ProduitService produitService;


    @MockBean
    private ProduitMapper produitMapper;



    @Test
    void testCreationProduit() throws Exception {

        ProduitDTO dto2 = new ProduitDTO();
        dto2.setNom("chaussure");
        dto2.setPrix(5000);


        ProduitDTO dto = new ProduitDTO();
        dto.setId(1L);
        dto.setNom("chaussure");
        dto.setPrix(5000);

        when(produitService.creation(any(ProduitDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/produit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect( jsonPath("$.nom").value("chaussure"))
                .andExpect( jsonPath("$.prix").value(5000));
    }

    @Test
    void AfficherProduitTest() throws Exception {
        ProduitDTO p2 = new ProduitDTO();
        p2.setId(1L);
        p2.setNom("mangue");
        p2.setPrix(2000);

        List<ProduitDTO> produits = new ArrayList<>();
        produits.add(p2);

        when(produitService.afficherToutProduitDTO()).thenReturn(produits);

        mockMvc.perform(get("/produit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("mangue"))
                .andExpect(jsonPath("$[0].prix").value(2000));
    }

    @Test
    void MiseaJourTest() throws Exception {

        ProduitDTO p2 = new ProduitDTO();
        p2.setPrix(5000);

        ProduitDTO miseajourProduit = new ProduitDTO();
        miseajourProduit.setPrix(5000);

        when(produitService.miseAJour(eq(1L), any(ProduitDTO.class))).thenReturn(miseajourProduit);

        mockMvc.perform(put("/produit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(p2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prix").value(5000));
    }

    @Test
    void InformationIdTest() throws Exception {
        ProduitDTO p2 = new ProduitDTO();
        p2.setId(1L);
        p2.setNom("jouet");
        p2.setPrix(2000);

        when(produitService.informationProduit(1L)).thenReturn(p2);

        mockMvc.perform(get("/produit/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("jouet"))
                .andExpect(jsonPath("$.prix").value(2000));
    }


    @Test
    void supprimerTest() throws Exception {

        when(produitService.supprimerProduit(1L)).thenReturn("Produit supprimé");



        mockMvc.perform(delete("/produit/1"))
                .andExpect(status().isOk());
    }






}




