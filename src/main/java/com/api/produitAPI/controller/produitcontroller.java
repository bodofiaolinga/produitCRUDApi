package com.api.produitAPI.controller;


import com.api.produitAPI.modele.produit;
import com.api.produitAPI.service.produitservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit")
public class produitcontroller {
    public final produitservice produitservice;

    public produitcontroller(com.api.produitAPI.service.produitservice produitservice) {
        this.produitservice = produitservice;
    }

@PostMapping("/create")
    public produit create(@RequestBody produit produit){
        return produitservice.creer(produit);
    }
    @GetMapping("/read")
    public List<produit> read(){
        return produitservice.lire();
    }
    @PutMapping("/update/{id}")
    public produit update(@PathVariable Long id,@RequestBody produit produit){
        return produitservice.modifier(id,produit);
    }
 @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable  Long id ){
        return produitservice.supprimer(id);
    }
}
