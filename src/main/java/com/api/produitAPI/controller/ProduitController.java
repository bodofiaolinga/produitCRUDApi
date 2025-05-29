package com.api.produitAPI.controller;


import com.api.produitAPI.DTO.ProduitDTO;
import com.api.produitAPI.DTO.ProduitMapper;

import com.api.produitAPI.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    public ProduitService produitservice;

    @Autowired
    public ProduitMapper ProduitMapper ;


    @PostMapping
    public ResponseEntity<ProduitDTO> creationProduit(@RequestBody @Valid ProduitDTO produitDTO) {
        ProduitDTO creer = produitservice.creation(produitDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creer);
    }

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> afficherToutProduit() {
        return ResponseEntity.ok(produitservice.afficherToutProduitDTO());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> miseAJourProduit(@Valid @PathVariable Long id, @RequestBody ProduitDTO produitDTO) {

        ProduitDTO updateDTO =produitservice.miseAJour(id,produitDTO);
        return ResponseEntity.ok(updateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimer(@Valid @PathVariable  Long id ){
        String deleteDTO = produitservice.supprimerProduit(id);
        return ResponseEntity.ok(deleteDTO);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProduitDTO>afficherproduit(Long id){
        return ResponseEntity.ok(produitservice.informationProduit(id));
    }

}
