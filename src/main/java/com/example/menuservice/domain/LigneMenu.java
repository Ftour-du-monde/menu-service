package com.example.menuservice.domain;

/**
 * Valeur représentant une ligne dans la composition d'un menu.
 * Classe pure : aucune dépendance vers un framework.
 */
public class LigneMenu {

    private Long platId;
    private int quantite;

    public LigneMenu() {
    }

    public LigneMenu(Long platId, int quantite) {
        this.platId = platId;
        this.quantite = quantite;
    }

    public Long getPlatId() { return platId; }
    public void setPlatId(Long platId) { this.platId = platId; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
