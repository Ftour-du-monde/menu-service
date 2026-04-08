package com.example.menuservice.usecase.port.in;

import com.example.menuservice.dto.MenuRequest;
import com.example.menuservice.dto.MenuResponse;
import com.example.menuservice.usecase.AjoutPlatResultat;
import com.example.menuservice.usecase.RetraitPlatResultat;

import java.util.Optional;

/**
 * Port entrant : modification d'un menu (renommage, ajout/retrait de plats).
 */
public interface ModifierMenuPort {

    Optional<MenuResponse> renommer(Long id, MenuRequest request);

    AjoutPlatResultat ajouterPlat(Long menuId, Long platId);

    RetraitPlatResultat retirerPlat(Long menuId, Long platId);
}
