package com.example.menuservice.adapters.out;

import com.example.menuservice.dto.PlatResumeDTO;
import com.example.menuservice.usecase.CatalogueGateway;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.interceptor.Interceptor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@ApplicationScoped
public class StubCatalogueClient implements CatalogueGateway {

    private static final Map<Long, PlatResumeDTO> PLATS = Map.of(
            1L, new PlatResumeDTO(1L, "Salade niçoise",     new BigDecimal("8.50")),
            2L, new PlatResumeDTO(2L, "Aïoli provençal",    new BigDecimal("12.00")),
            3L, new PlatResumeDTO(3L, "Gratin dauphinois",  new BigDecimal("9.00")),
            4L, new PlatResumeDTO(4L, "Bouillabaisse",      new BigDecimal("15.50")),
            5L, new PlatResumeDTO(5L, "Tian de légumes",    new BigDecimal("7.50")),
            6L, new PlatResumeDTO(6L, "Poulet rôti",        new BigDecimal("11.00")),
            7L, new PlatResumeDTO(7L, "Mousse au chocolat", new BigDecimal("5.00")),
            8L, new PlatResumeDTO(8L, "Tarte tropézienne",  new BigDecimal("5.50"))
    );

    @Override
    public Optional<PlatResumeDTO> findPlatById(Long platId) {
        return Optional.ofNullable(PLATS.get(platId));
    }
}
