package com.example.menuservice.dto;

import java.math.BigDecimal;

/**
 * DTO représentant le résumé d'un plat utilisé dans les réponses de menu.
 * <p>
 * Contient les informations enrichies récupérées depuis l'API Plats et Utilisateurs
 * (nom et prix). Seul l'identifiant est stocké en interne dans {@link com.example.menuservice.domain.LigneMenu}.
 * </p>
 */
public class PlatResumeDTO {

    /** Identifiant du plat. */
    private Long id;

    /** Nom du plat récupéré depuis l'API Plats et Utilisateurs. */
    private String nom;

    /** Prix unitaire du plat récupéré depuis l'API Plats et Utilisateurs. */
    private BigDecimal prix;

    /**
     * Constructeur sans argument requis par JSON-B.
     */
    public PlatResumeDTO() {
    }

    /**
     * Crée un résumé de plat avec toutes ses informations.
     *
     * @param id   identifiant du plat
     * @param nom  nom du plat
     * @param prix prix unitaire du plat
     */
    public PlatResumeDTO(Long id, String nom, BigDecimal prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    /**
     * Retourne l'identifiant du plat.
     *
     * @return identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du plat.
     *
     * @param id identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom du plat.
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du plat.
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prix unitaire du plat.
     *
     * @return prix
     */
    public BigDecimal getPrix() {
        return prix;
    }

    /**
     * Définit le prix unitaire du plat.
     *
     * @param prix prix
     */
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
}
