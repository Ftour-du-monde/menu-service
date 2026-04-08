package com.example.menuservice.dto;

/**
 * DTO représentant les informations minimales d'un créateur de menu.
 * <p>
 * Utilisé pour transporter le nom de l'abonné récupéré depuis l'API Plats
 * et Utilisateurs afin de l'inclure dans les réponses de menu.
 * </p>
 */
public class CreateurDTO {

    /** Identifiant de l'abonné. */
    private Long id;

    /** Nom de famille de l'abonné. */
    private String nom;

    /** Prénom de l'abonné. */
    private String prenom;

    /**
     * Constructeur sans argument requis par JSON-B.
     */
    public CreateurDTO() {
    }

    /**
     * Crée un DTO créateur avec son identifiant et son nom.
     *
     * @param id     identifiant de l'abonné
     * @param nom    nom de famille
     * @param prenom prénom
     */
    public CreateurDTO(Long id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Retourne l'identifiant de l'abonné.
     *
     * @return identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'abonné.
     *
     * @param id identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de famille de l'abonné.
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de famille de l'abonné.
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom de l'abonné.
     *
     * @return prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de l'abonné.
     *
     * @param prenom prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
