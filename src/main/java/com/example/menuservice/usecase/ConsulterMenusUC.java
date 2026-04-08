package com.example.menuservice.usecase;

import com.example.menuservice.domain.LigneMenu;
import com.example.menuservice.domain.Menu;
import com.example.menuservice.dto.MenuResponse;
import com.example.menuservice.dto.PlatResumeDTO;
import com.example.menuservice.usecase.port.in.ConsulterMenusPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ConsulterMenusUC implements ConsulterMenusPort {

    @Inject
    private MenuRepository menuRepository;

    @Inject
    private CatalogueGateway catalogueGateway;

    @Inject
    private UtilisateurGateway utilisateurGateway;

    @Override
    public List<MenuResponse> tousLesMenus() {
        return menuRepository.findAll().stream()
                .map(this::enrichir)
                .toList();
    }

    @Override
    public Optional<MenuResponse> menuParId(Long id) {
        return menuRepository.findById(id).map(this::enrichir);
    }

    private MenuResponse enrichir(Menu menu) {
        MenuResponse response = new MenuResponse();
        response.setId(menu.getId());
        response.setNom(menu.getNom());
        response.setCreateurId(menu.getCreateurId());
        response.setDateCreation(menu.getDateCreation());
        response.setDateMiseAJour(menu.getDateMiseAJour());
        response.setPrixTotal(menu.getPrixTotal());

        utilisateurGateway.findUtilisateurById(menu.getCreateurId())
                .ifPresent(c -> response.setCreateurNom(c.getNom()));

        List<PlatResumeDTO> plats = menu.getLignes().stream()
                .map(LigneMenu::getPlatId)
                .map(catalogueGateway::findPlatById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        response.setPlats(plats);

        return response;
    }
}
