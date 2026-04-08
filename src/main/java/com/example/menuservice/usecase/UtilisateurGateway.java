package com.example.menuservice.usecase;

import com.example.menuservice.dto.CreateurDTO;

import java.util.Optional;

/**
 * Port secondaire vers le registre des abonnés de l'API Plats et Utilisateurs.
 * <p>
 * Permet aux cas d'utilisation de vérifier l'existence d'un créateur de menu
 * et de récupérer son nom sans dépendre directement d'un client HTTP concret.
 * L'implémentation concrète est {@link com.example.menuservice.adapters.out.RestUtilisateurClient}.
 * </p>
 */
public interface UtilisateurGateway {

    /**
     * Récupère les informations d'un abonné depuis l'API Plats et Utilisateurs.
     *
     * @param utilisateurId identifiant de l'abonné
     * @return un {@link Optional} contenant le DTO du créateur si trouvé, vide sinon
     */
    Optional<CreateurDTO> findUtilisateurById(Long utilisateurId);
}
