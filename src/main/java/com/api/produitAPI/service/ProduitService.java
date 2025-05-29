package com.api.produitAPI.service;

import com.api.produitAPI.DTO.ProduitDTO;
import com.api.produitAPI.DTO.ProduitMapper;
import com.api.produitAPI.Exception.RessourceNotFoundException;
import com.api.produitAPI.model.Produit;
import com.api.produitAPI.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class  ProduitService{

    @Autowired
 private ProduitRepository produitrepository;
 @Autowired
    private  ProduitMapper produitMapper;


    public ProduitDTO creation(ProduitDTO produitDTO) {
        Produit produit = produitMapper.toEntity(produitDTO);
        Produit savedProduit = produitrepository.save(produit);
        return produitMapper.toDTO(savedProduit);
    }


    public List<ProduitDTO> afficherToutProduitDTO() {
        return produitrepository.findAll()
                .stream()
                .map(produitMapper::toDTO)
                .toList();
    }

    public ProduitDTO miseAJour(Long id, ProduitDTO produitDTO) {
        return produitrepository.findById(id)
                .map(p -> {
                    p.setNom(produitDTO.getNom());
                    p.setPrix((int) produitDTO.getPrix());
                    Produit updatedProduit = produitrepository.save(p);
                    return produitMapper.toDTO(updatedProduit);
                })
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }


    public String supprimerProduit(Long id) {
        produitrepository.deleteById(id);

        return "produit supprimer avec success!";
    }

    public ProduitDTO informationProduit( Long id){
        Produit produit=produitrepository.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("produit non trouver"));
        return produitMapper.toDTO(produit);
    }

}
