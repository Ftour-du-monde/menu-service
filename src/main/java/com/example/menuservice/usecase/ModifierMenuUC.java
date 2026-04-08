package com.example.menuservice.usecase;

import com.example.menuservice.domain.LigneMenu;
import com.example.menuservice.domain.Menu;
import com.example.menuservice.dto.MenuRequest;
import com.example.menuservice.dto.MenuResponse;
import com.example.menuservice.dto.PlatResumeDTO;
import com.example.menuservice.usecase.port.in.ModifierMenuPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ModifierMenuUC implements ModifierMenuPort {

    @Inject
    private MenuRepository menuRepository;

    @Inject
    private CatalogueGateway catalogueGateway;

    @Inject
    private UtilisateurGateway utilisateurGateway;

    @Override
    public Optional<MenuResponse> renommer(Long id, MenuRequest request) {
        Optional<Menu> optMenu = menuRepository.findById(id);
        if (optMenu.isEmpty()) {
            return Optional.empty();
        }

        Menu menu = optMenu.get();
        menu.setNom(request.getNom());
        menu.setDateMiseAJour(LocalDate.now());
        menuRepository.save(menu);

        return Optional.of(construireResponse(menu));
    }

    @Override
    public AjoutPlatResultat ajouterPlat(Long menuId, Long platId) {
        Optional<Menu> optMenu = menuRepository.findById(menuId);
        if (optMenu.isEmpty()) {
            return AjoutPlatResultat.menuIntrouvable();
        }

        Menu menu = optMenu.get();
        boolean dejaPresent = menu.getLignes().stream()
                .anyMatch(l -> l.getPlatId().equals(platId));
        if (dejaPresent) {
            return AjoutPlatResultat.dejaPresent();
        }

        Optional<PlatResumeDTO> optPlat = catalogueGateway.findPlatById(platId);
        if (optPlat.isEmpty()) {
            return AjoutPlatResultat.platIntrouvable();
        }

        menu.ajouterPlat(platId);
        menu.setDateMiseAJour(LocalDate.now());
        recalculerPrix(menu);
        menuRepository.save(menu);

        return AjoutPlatResultat.succes(construireResponse(menu));
    }

    @Override
    public RetraitPlatResultat retirerPlat(Long menuId, Long platId) {
        Optional<Menu> optMenu = menuRepository.findById(menuId);
        if (optMenu.isEmpty()) {
            return RetraitPlatResultat.menuIntrouvable();
        }

        Menu menu = optMenu.get();
        boolean present = menu.getLignes().stream()
                .anyMatch(l -> l.getPlatId().equals(platId));
        if (!present) {
            return RetraitPlatResultat.platAbsent();
        }

        menu.retirerPlat(platId);
        menu.setDateMiseAJour(LocalDate.now());
        recalculerPrix(menu);
        menuRepository.save(menu);

        return RetraitPlatResultat.succes(construireResponse(menu));
    }

    private void recalculerPrix(Menu menu) {
        Map<Long, BigDecimal> prix = menu.getLignes().stream()
                .map(LigneMenu::getPlatId)
                .distinct()
                .map(catalogueGateway::findPlatById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(PlatResumeDTO::getId, PlatResumeDTO::getPrix));
        menu.recalculerPrix(prix);
    }

    private MenuResponse construireResponse(Menu menu) {
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
