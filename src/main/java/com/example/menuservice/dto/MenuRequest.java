package com.example.menuservice.dto;

/**
 * DTO de requête pour la création ou la mise à jour d'un menu.
 * <p>
 * Correspond au corps JSON attendu par les endpoints {@code POST /menus}
 * et {@code PUT /menus/{id}}.
 * </p>
 */
public class MenuRequest {

    /** Nom du menu à créer ou à mettre à jour. */
    private String nom;

    /** Identifiant de l'abonné créateur du menu. */
    private Long createurId;

    /**
     * Constructeur sans argument requis par JSON-B.
     */
    public MenuRequest() {
    }

    /**
     * Retourne le nom du menu.
     *
     * @return nom du menu
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du menu.
     *
     * @param nom nom du menu
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
}
