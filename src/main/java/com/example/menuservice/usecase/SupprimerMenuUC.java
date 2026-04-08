package com.example.menuservice.usecase;

import com.example.menuservice.usecase.port.in.SupprimerMenuPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SupprimerMenuUC implements SupprimerMenuPort {

    @Inject
    private MenuRepository menuRepository;

    @Override
    public boolean supprimer(Long id) {
        return menuRepository.deleteById(id);
    }
}
