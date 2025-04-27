package com.api.produitAPI.service;

import com.api.produitAPI.modele.produit;
import com.api.produitAPI.repository.produitrepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class produitserviceImpl implements produitservice{

 private final produitrepository produitrepository;

    public produitserviceImpl(com.api.produitAPI.repository.produitrepository produitrepository) {
        this.produitrepository = produitrepository;
    }


    @Override
      public produit creer(produit produit) {
        return produitrepository.save(produit);
    }

    @Override
    public List<produit> lire() {
        return produitrepository.findAll();
    }

    @Override
    public produit modifier(Long id, produit produit) {
        return produitrepository.findById(id)
                .map(p-> {
                    p.setPrix(produit.getPrix());
        return produitrepository.save(p);
                }).orElseThrow(()-> new RuntimeException("produit non trouver"));
    }

    @Override
    public String supprimer(Long id) {

        produitrepository.deleteById(id);
        return "produit supprimer avec success!";
    }
}
