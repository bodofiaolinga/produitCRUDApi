package com.api.produitAPI.repository;

import com.api.produitAPI.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProduitRepository extends JpaRepository<Produit,Long> {

}
