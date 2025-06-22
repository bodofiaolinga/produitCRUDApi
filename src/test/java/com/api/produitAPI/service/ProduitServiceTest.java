package com.api.produitAPI.service;

import com.api.produitAPI.DTO.ProduitDTO;
import com.api.produitAPI.DTO.ProduitMapper;
import com.api.produitAPI.model.Produit;
import com.api.produitAPI.repository.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private ProduitMapper produitMapper;

    @InjectMocks
    private ProduitService produitService;
    @Test
    void CreationProduitTest(){

        //Arrange
        ProduitDTO dto=new ProduitDTO();
        dto.setNom("ordinateur");
        dto.setPrix(215000);

        Produit produit=new Produit();
        produit.setNom("ordinateur");
        produit.setPrix(215000);

        Produit produitEnregistrer=new Produit();
        produitEnregistrer.setId(1L);
        produitEnregistrer.setNom("ordinateur");
        produitEnregistrer.setPrix(215000);

        ProduitDTO dtoAvecId = new ProduitDTO();
        dtoAvecId.setId(1L);
        dtoAvecId.setNom("ordinateur");
        dtoAvecId.setPrix(215000);


        when(produitMapper.toEntity(dto)).thenReturn(produit);

        when(produitRepository.save(any(Produit.class))).thenReturn(produitEnregistrer);


        when(produitMapper.toDTO(produitEnregistrer)).thenReturn(dtoAvecId);
        //Act

         ProduitDTO creation=produitService.creation(dto);
        //Assert
        assertThat(creation).isNotNull();
        assertThat(creation.getId()).isNotNull();
        assertThat(creation.getNom()).isEqualTo("ordinateur");
        assertThat(creation.getPrix()).isEqualTo(215000);

    }

    @Test
    void AfficherProduitTest() {

        // Arrange
        List<Produit> produitsList = new ArrayList<>();

        Produit p1 = new Produit();
        p1.setNom("chaussure");
        p1.setPrix(10000);
        produitsList.add(p1);

        Produit p2 = new Produit();
        p2.setNom("voiture");
        p2.setPrix(10000000);
        produitsList.add(p2);

        ProduitDTO dto1 = new ProduitDTO();
        dto1.setNom("chaussure");
        dto1.setPrix(10000);

        ProduitDTO dto2 = new ProduitDTO();
        dto2.setNom("voiture");
        dto2.setPrix(10000000);

        when(produitRepository.findAll()).thenReturn(produitsList);
        when(produitMapper.toDTO(p1)).thenReturn(dto1);
        when(produitMapper.toDTO(p2)).thenReturn(dto2);

        // Act
        List<ProduitDTO> afficher = produitService.afficherToutProduitDTO();

        // Assert
        assertThat(afficher).isNotNull();
        assertThat(afficher).hasSize(2);
        assertThat(afficher).extracting(ProduitDTO::getNom).containsExactly("chaussure", "voiture");
        verify(produitRepository, times(1)).findAll();
    }

    @Test
    void MiseJourProduitTest(){
        // Arrange
         Produit AncienProduit=new Produit();
         AncienProduit.setId(1L);
         AncienProduit.setNom("chaussure");
         AncienProduit.setPrix(1000);


       ProduitDTO ajourner=new ProduitDTO();
       ajourner.setId(1L);
       ajourner.setNom("chaussure");
       ajourner.setPrix(5000);

       when(produitRepository.findById(1L)).thenReturn(Optional.of(AncienProduit));
       when(produitRepository.save(any(Produit.class))).thenAnswer(invocation -> invocation.getArgument(0) );
       when(produitMapper.toDTO(any(Produit.class))).thenAnswer(invocation ->{
               Produit p=invocation.getArgument(0);
           ProduitDTO dto = new ProduitDTO();
           dto.setId(p.getId());
           dto.setNom(p.getNom());
           dto.setPrix(p.getPrix());
              return dto;
       });

        // Act
             ProduitDTO result=produitService.miseAJour(1L,ajourner);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getNom()).isEqualTo("chaussure");
        assertThat(result.getPrix()).isEqualTo(5000);


    }
    @Test
    void SupprimerProduitTest(){
        // Arrange
      Long id=1L;
      doNothing().when(produitRepository).deleteById(id);
        // Act
       produitService.supprimerProduit(id);
        // Assert
        verify(produitRepository,times(1)).deleteById(id);


    }
    @Test
    void InformationProduitTest(){
        // Arrange
        Produit p=new Produit();
        p.setId(1L);
        p.setNom("babouche");
        p.setPrix(2000);

        when(produitRepository.findById(1L)).thenReturn(Optional.of(p));
        when(produitMapper.toDTO(any(Produit.class))).thenAnswer(invocation ->{
            Produit pro=invocation.getArgument(0);
            ProduitDTO dto=new ProduitDTO();
            dto.setId(pro.getId());
            dto.setNom(pro.getNom());
            dto.setPrix(pro.getPrix());
            return dto;

        });

        // Act
        ProduitDTO result=produitService.informationProduit(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getNom()).isEqualTo("babouche");
        assertThat(result.getPrix()).isEqualTo(2000);


    }





    }