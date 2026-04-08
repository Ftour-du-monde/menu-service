package com.example.menuservice.adapters.out;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Objet embarqué JPA pour une ligne de composition d'un menu.
 * Séparé du domaine afin que {@link com.example.menuservice.domain.LigneMenu}
 * reste sans dépendance infrastructure.
 */
@Embeddable
public class LigneMenuJpaEntity {

    @Column(name = "plat_id", nullable = false)
    private Long platId;

    @Column(name = "quantite", nullable = false)
    private int quantite;

    public LigneMenuJpaEntity() {
    }

    public LigneMenuJpaEntity(Long platId, int quantite) {
        this.platId = platId;
        this.quantite = quantite;
    }

    public Long getPlatId() { return platId; }
    public void setPlatId(Long platId) { this.platId = platId; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
