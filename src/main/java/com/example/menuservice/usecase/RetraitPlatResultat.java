package com.example.menuservice.usecase;

import com.example.menuservice.dto.MenuResponse;

/**
 * Résultat de l'opération de retrait d'un plat d'un menu.
 */
public final class RetraitPlatResultat {

    public enum Statut {
        SUCCES,
        MENU_INTROUVABLE,
        PLAT_ABSENT
    }

    private final Statut statut;
    private final MenuResponse response;

    private RetraitPlatResultat(Statut statut, MenuResponse response) {
        this.statut = statut;
        this.response = response;
    }

    public static RetraitPlatResultat succes(MenuResponse response) {
        return new RetraitPlatResultat(Statut.SUCCES, response);
    }

    public static RetraitPlatResultat menuIntrouvable() {
        return new RetraitPlatResultat(Statut.MENU_INTROUVABLE, null);
    }

    public static RetraitPlatResultat platAbsent() {
        return new RetraitPlatResultat(Statut.PLAT_ABSENT, null);
    }

    public Statut getStatut() { return statut; }
    public MenuResponse getResponse() { return response; }
}
