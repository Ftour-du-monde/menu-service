package com.example.menuservice.adapters.out;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité JPA pour la persistance des menus.
 * Séparée du domaine afin que {@link com.example.menuservice.domain.Menu}
 * reste sans dépendance infrastructure.
 */
@Entity
@Table(name = "menus")
public class MenuJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "createur_id", nullable = false)
    private Long createurId;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @Column(name = "date_mise_a_jour", nullable = false)
    private LocalDate dateMiseAJour;

    @Column(name = "prix_total", precision = 10, scale = 2)
    private BigDecimal prixTotal;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "menu_lignes", joinColumns = @JoinColumn(name = "menu_id"))
    private List<LigneMenuJpaEntity> lignes = new ArrayList<>();

    public MenuJpaEntity() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Long getCreateurId() { return createurId; }
    public void setCreateurId(Long createurId) { this.createurId = createurId; }

    public LocalDate getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDate dateCreation) { this.dateCreation = dateCreation; }

    public LocalDate getDateMiseAJour() { return dateMiseAJour; }
    public void setDateMiseAJour(LocalDate dateMiseAJour) { this.dateMiseAJour = dateMiseAJour; }

    public BigDecimal getPrixTotal() { return prixTotal; }
    public void setPrixTotal(BigDecimal prixTotal) { this.prixTotal = prixTotal; }

    public List<LigneMenuJpaEntity> getLignes() { return lignes; }
    public void setLignes(List<LigneMenuJpaEntity> lignes) { this.lignes = lignes; }
}
