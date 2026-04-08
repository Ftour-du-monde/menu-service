package com.example.menuservice;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Point d'entrée de l'application JAX-RS du service Menus.
 * <p>
 * Expose toutes les ressources REST sous le chemin de base {@code /api}.
 * Le contexte de déploiement de la WAR définit la racine {@code /menus},
 * ce qui donne l'URL de base : {@code http://localhost:8081/menus/api}.
 * </p>
 */
@ApplicationPath("/api")
public class MenuApplication extends Application {
}
