package com.example.menuservice.usecase.port.in;

import com.example.menuservice.dto.MenuRequest;
import com.example.menuservice.dto.MenuResponse;

import java.util.Optional;

/**
 * Port entrant : création d'un menu.
 */
public interface CreerMenuPort {

    /**
     * @return le menu créé, ou {@link Optional#empty()} si le créateur est introuvable
     * @throws IllegalArgumentException si les données de la requête sont invalides
     */
    Optional<MenuResponse> creer(MenuRequest request);
}
