package com.example.menuservice.usecase.port.in;

/**
 * Port entrant : suppression d'un menu.
 */
public interface SupprimerMenuPort {

    /**
     * @return {@code true} si le menu existait et a été supprimé, {@code false} sinon
     */
    boolean supprimer(Long id);
}
