package com.api.produitAPI.service;

import com.api.produitAPI.modele.produit;

import java.util.List;

public interface produitservice {

    produit creer(produit produit );

    List<produit> lire();

    produit modifier(Long id,produit produit);

    String supprimer(Long id);

}
