package com.example.menuservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO de réponse représentant un menu enrichi renvoyé par les endpoints REST.
 * <p>
 * Contient toutes les informations d'un menu, y compris le nom du créateur
 * et les résumés des plats, enrichis depuis l'API Plats et Utilisateurs.
 * </p>
 */
public class MenuResponse {

    /** Identifiant du menu. */
    private Long id;

    /** Nom du menu. */
    private String nom;

    /** Identifiant de l'abonné créateur. */
    private Long createurId;

    /** Nom du créateur récupéré depuis l'API Plats et Utilisateurs. */
    private String createurNom;

    /** Date de création du menu. */
    private LocalDate dateCreation;

    /** Date de la dernière mise à jour du menu. */
    private LocalDate dateMiseAJour;

    /** Liste des plats composant le menu avec nom et prix. */
    private List<PlatResumeDTO> plats;

    /** Prix total du menu, somme des prix unitaires des plats. */
    private BigDecimal prixTotal;

    /**
     * Constructeur sans argument requis par JSON-B.
     */
    public MenuResponse() {
    }

    /**
     * Retourne l'identifiant du menu.
     *
     * @return identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du menu.
     *
     * @param id identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom du menu.
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du menu.
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'identifiant du créateur.
     *
     * @return identifiant du créateur
     */
    public Long getCreateurId() {
        return createurId;
    }

    /**
     * Définit l'identifiant du créateur.
     *
     * @param createurId identifiant du créateur
     */
    public void setCreateurId(Long createurId) {
        this.createurId = createurId;
    }

    /**
     * Retourne le nom du créateur.
     *
     * @return nom du créateur
     */
    public String getCreateurNom() {
        return createurNom;
    }

    /**
     * Définit le nom du créateur.
     *
     * @param createurNom nom du créateur
     */
    public void setCreateurNom(String createurNom) {
        this.createurNom = createurNom;
    }

    /**
     * Retourne la date de création du menu.
     *
     * @return date de création
     */
    public LocalDate getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la date de création du menu.
     *
     * @param dateCreation date de création
     */
    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Retourne la date de dernière mise à jour.
     *
     * @return date de mise à jour
     */
    public LocalDate getDateMiseAJour() {
        return dateMiseAJour;
    }

    /**
     * Définit la date de dernière mise à jour.
     *
     * @param dateMiseAJour date de mise à jour
     */
    public void setDateMiseAJour(LocalDate dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    /**
     * Retourne la liste des résumés de plats.
     *
     * @return liste des plats
     */
    public List<PlatResumeDTO> getPlats() {
        return plats;
    }

    /**
     * Définit la liste des résumés de plats.
     *
     * @param plats liste des plats
     */
    public void setPlats(List<PlatResumeDTO> plats) {
        this.plats = plats;
    }

    /**
     * Retourne le prix total du menu.
     *
     * @return prix total
     */
    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    /**
     * Définit le prix total du menu.
     *
     * @param prixTotal prix total
     */
    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }
}
