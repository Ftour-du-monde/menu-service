package com.example.menuservice.usecase;

import com.example.menuservice.domain.Menu;

import java.util.List;
import java.util.Optional;

/**
 * Port secondaire définissant le contrat de persistance des menus.
 * <p>
 * Les cas d'utilisation dépendent de cette interface ; l'implémentation concrète
 * ({@link com.example.menuservice.adapters.out.JpaMenuRepository}) est fournie
 * par la couche adaptateurs sortants. Ce placement dans le package {@code usecase}
 * matérialise l'inversion de dépendance : c'est le métier qui définit le contrat,
 * pas l'infrastructure.
 * </p>
 */
public interface MenuRepository {

    /**
     * Retourne la liste de tous les menus persistés.
     *
     * @return liste des menus (vide si aucun menu n'existe)
     */
    List<Menu> findAll();

    /**
     * Recherche un menu par son identifiant.
     *
     * @param id identifiant du menu
     * @return un {@link Optional} contenant le menu si trouvé, vide sinon
     */
    Optional<Menu> findById(Long id);

    /**
     * Persiste un nouveau menu ou met à jour un menu existant.
     *
     * @param menu le menu à sauvegarder
     * @return le menu avec son identifiant affecté si création
     */
    Menu save(Menu menu);

    /**
     * Supprime le menu identifié par {@code id}.
     *
     * @param id identifiant du menu à supprimer
     * @return {@code true} si le menu existait et a été supprimé, {@code false} sinon
     */
    boolean deleteById(Long id);
}
