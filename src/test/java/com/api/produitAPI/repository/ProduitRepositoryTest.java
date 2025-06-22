package com.api.produitAPI.repository;

import com.api.produitAPI.model.Produit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProduitRepositoryTest {

    @Autowired
    public ProduitRepository produitRepository;

    @Test
    void EnregistrementProduitTest(){

        //Arrange
        Produit produit=new Produit();
        produit.setNom("chaussure");
        produit.setPrix(10000);


        //Act
        Produit prduitEnregistrement=produitRepository.save(produit);

        //Assert
        assertThat(prduitEnregistrement.getId()).isNotNull();
        assertThat(prduitEnregistrement.getNom()).isEqualTo("chaussure");




    }



}