package com.api.produitAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDTO {
    private Long id;
    private String nom;
    private String description;
    private double prix;

}

