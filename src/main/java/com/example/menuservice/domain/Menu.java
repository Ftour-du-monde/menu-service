package com.example.menuservice.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Entité de domaine représentant un menu composé par un abonné.
 * Classe pure : aucune dépendance vers un framework (JPA, CDI, etc.).
 */
public class Menu {

    private Long id;
    private String nom;
    private Long createurId;
    private LocalDate dateCreation;
    private LocalDate dateMiseAJour;
    private BigDecimal prixTotal;
    private List<LigneMenu> lignes = new ArrayList<>();

    public Menu() {
    }

    public void ajouterPlat(Long platId) {
        lignes.add(new LigneMenu(platId, 1));
    }

    public void retirerPlat(Long platId) {
        lignes.removeIf(l -> l.getPlatId().equals(platId));
    }

    public void recalculerPrix(Map<Long, BigDecimal> prixParPlatId) {
        prixTotal = lignes.stream()
                .map(l -> prixParPlatId.getOrDefault(l.getPlatId(), BigDecimal.ZERO)
                        .multiply(BigDecimal.valueOf(l.getQuantite())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public List<LigneMenu> getLignes() { return lignes; }
    public void setLignes(List<LigneMenu> lignes) { this.lignes = lignes; }
}
