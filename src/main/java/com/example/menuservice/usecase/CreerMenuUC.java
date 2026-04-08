package com.example.menuservice.usecase;

import com.example.menuservice.domain.Menu;
import com.example.menuservice.dto.CreateurDTO;
import com.example.menuservice.dto.MenuRequest;
import com.example.menuservice.dto.MenuResponse;
import com.example.menuservice.usecase.port.in.CreerMenuPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
public class CreerMenuUC implements CreerMenuPort {

    @Inject
    private MenuRepository menuRepository;

    @Inject
    private UtilisateurGateway utilisateurGateway;

    @Override
    public Optional<MenuResponse> creer(MenuRequest request) {
        if (request.getNom() == null || request.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom du menu est obligatoire.");
        }
        if (request.getCreateurId() == null) {
            throw new IllegalArgumentException("L'identifiant du créateur est obligatoire.");
        }

        CreateurDTO createur = utilisateurGateway.findUtilisateurById(request.getCreateurId())
                .orElse(null);
        if (createur == null) {
            return Optional.empty();
        }

        Menu menu = new Menu();
        menu.setNom(request.getNom());
        menu.setCreateurId(request.getCreateurId());
        menu.setDateCreation(LocalDate.now());
        menu.setDateMiseAJour(LocalDate.now());
        menu.setPrixTotal(BigDecimal.ZERO);
        menu.setLignes(new ArrayList<>());

        Menu saved = menuRepository.save(menu);

        MenuResponse response = new MenuResponse();
        response.setId(saved.getId());
        response.setNom(saved.getNom());
        response.setCreateurId(saved.getCreateurId());
        response.setCreateurNom(createur.getNom());
        response.setDateCreation(saved.getDateCreation());
        response.setDateMiseAJour(saved.getDateMiseAJour());
        response.setPrixTotal(saved.getPrixTotal());
        response.setPlats(new ArrayList<>());

        return Optional.of(response);
    }
}
