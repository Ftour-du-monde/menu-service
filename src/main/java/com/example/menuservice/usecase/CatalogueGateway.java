package com.example.menuservice.usecase;

import com.example.menuservice.dto.PlatResumeDTO;

import java.util.Optional;

/**
 * Port secondaire vers le catalogue de plats de l'API Plats et Utilisateurs.
 * <p>
 * Permet aux cas d'utilisation de vérifier l'existence d'un plat et de récupérer
 * son résumé (nom et prix) sans dépendre directement d'un client HTTP concret.
 * L'implémentation concrète est {@link com.example.menuservice.adapters.out.RestCatalogueClient}.
 * </p>
 */
public interface CatalogueGateway {

    /**
     * Récupère le résumé d'un plat depuis l'API Plats et Utilisateurs.
     *
     * @param platId identifiant du plat
     * @return un {@link Optional} contenant le résumé du plat si trouvé, vide sinon
     */
    Optional<PlatResumeDTO> findPlatById(Long platId);
}
