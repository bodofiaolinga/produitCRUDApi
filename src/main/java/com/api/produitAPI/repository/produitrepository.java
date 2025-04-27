package com.api.produitAPI.repository;

import com.api.produitAPI.modele.produit;
import org.springframework.data.jpa.repository.JpaRepository;
public interface produitrepository extends JpaRepository<produit,Long>
{

}
