package com.example.menuservice.adapters.out;

import com.example.menuservice.dto.CreateurDTO;
import com.example.menuservice.usecase.UtilisateurGateway;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.interceptor.Interceptor;

import java.util.Map;
import java.util.Optional;

@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@ApplicationScoped
public class StubUtilisateurClient implements UtilisateurGateway {

    private static final Map<Long, CreateurDTO> UTILISATEURS = Map.of(
            1L, new CreateurDTO(1L, "Dupont",  ""),
            2L, new CreateurDTO(2L, "Martin",  ""),
            3L, new CreateurDTO(3L, "Bernard", "")
    );

    @Override
    public Optional<CreateurDTO> findUtilisateurById(Long utilisateurId) {
        return Optional.ofNullable(UTILISATEURS.get(utilisateurId));
    }
}
