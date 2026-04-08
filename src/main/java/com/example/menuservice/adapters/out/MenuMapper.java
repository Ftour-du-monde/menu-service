package com.example.menuservice.adapters.out;

import com.example.menuservice.domain.LigneMenu;
import com.example.menuservice.domain.Menu;

import java.util.stream.Collectors;

/**
 * Convertit entre l'entité de domaine {@link Menu} et l'entité JPA {@link MenuJpaEntity}.
 */
public final class MenuMapper {

    private MenuMapper() {
    }

    public static Menu toDomain(MenuJpaEntity entity) {
        Menu menu = new Menu();
        menu.setId(entity.getId());
        menu.setNom(entity.getNom());
        menu.setCreateurId(entity.getCreateurId());
        menu.setDateCreation(entity.getDateCreation());
        menu.setDateMiseAJour(entity.getDateMiseAJour());
        menu.setPrixTotal(entity.getPrixTotal());
        menu.setLignes(entity.getLignes().stream()
                .map(l -> new LigneMenu(l.getPlatId(), l.getQuantite()))
                .collect(Collectors.toList()));
        return menu;
    }

    public static MenuJpaEntity toEntity(Menu menu) {
        MenuJpaEntity entity = new MenuJpaEntity();
        entity.setId(menu.getId());
        entity.setNom(menu.getNom());
        entity.setCreateurId(menu.getCreateurId());
        entity.setDateCreation(menu.getDateCreation());
        entity.setDateMiseAJour(menu.getDateMiseAJour());
        entity.setPrixTotal(menu.getPrixTotal());
        entity.setLignes(menu.getLignes().stream()
                .map(l -> new LigneMenuJpaEntity(l.getPlatId(), l.getQuantite()))
                .collect(Collectors.toList()));
        return entity;
    }
}
