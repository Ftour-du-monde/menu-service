package com.example.menuservice.usecase;

import com.example.menuservice.dto.MenuResponse;

/**
 * Résultat de l'opération d'ajout d'un plat à un menu.
 */
public final class AjoutPlatResultat {

    public enum Statut {
        SUCCES,
        MENU_INTROUVABLE,
        PLAT_INTROUVABLE,
        DEJA_PRESENT
    }

    private final Statut statut;
    private final MenuResponse response;

    private AjoutPlatResultat(Statut statut, MenuResponse response) {
        this.statut = statut;
        this.response = response;
    }

    public static AjoutPlatResultat succes(MenuResponse response) {
        return new AjoutPlatResultat(Statut.SUCCES, response);
    }

    public static AjoutPlatResultat menuIntrouvable() {
        return new AjoutPlatResultat(Statut.MENU_INTROUVABLE, null);
    }

    public static AjoutPlatResultat platIntrouvable() {
        return new AjoutPlatResultat(Statut.PLAT_INTROUVABLE, null);
    }

    public static AjoutPlatResultat dejaPresent() {
        return new AjoutPlatResultat(Statut.DEJA_PRESENT, null);
    }

    public Statut getStatut() { return statut; }
    public MenuResponse getResponse() { return response; }
}
