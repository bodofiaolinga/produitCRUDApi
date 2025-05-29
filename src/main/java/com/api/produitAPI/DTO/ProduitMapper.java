package com.api.produitAPI.DTO;

import com.api.produitAPI.model.Produit;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper {


    public Produit toEntity(ProduitDTO dto){

        Produit produit=new Produit();
        produit.setId(dto.getId());
       produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setPrix((int) dto.getPrix());
        return produit;
    }

    public ProduitDTO toDTO(Produit produit){
        ProduitDTO dto=new ProduitDTO();
        dto.setId(produit.getId());
        dto.setNom(produit.getNom());
        dto.setDescription(produit.getDescription());
        dto.setPrix(produit.getPrix());
     return dto;

    }
}
