package com.example.menuservice.adapters.out;

import com.example.menuservice.dto.PlatResumeDTO;
import com.example.menuservice.usecase.CatalogueGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Adaptateur sortant implémentant {@link CatalogueGateway} via l'API REST Plats et Utilisateurs.
 * <p>
 * Interroge l'endpoint {@code GET /plats/{id}} de l'API distante
 * ({@code http://localhost:8080/plats-utilisateurs/api}) pour récupérer
 * les informations d'un plat et les mapper vers un {@link PlatResumeDTO}.
 * </p>
 */
@ApplicationScoped
public class RestCatalogueClient implements CatalogueGateway {

    private static final String BASE_URL = "http://localhost:8080/plats-utilisateurs/api";

    private final Client client = ClientBuilder.newClient();

    /**
     * {@inheritDoc}
     * <p>
     * Retourne {@link Optional#empty()} si l'API répond avec un statut non-200
     * ou si une erreur réseau survient.
     * </p>
     */
    @Override
    public Optional<PlatResumeDTO> findPlatById(Long platId) {
        try (Response response = client.target(BASE_URL)
                .path("/plats/{id}")
                .resolveTemplate("id", platId)
                .request(MediaType.APPLICATION_JSON)
                .get()) {

            if (response.getStatus() != 200) {
                return Optional.empty();
            }

            JsonObject json = response.readEntity(JsonObject.class);
            PlatResumeDTO dto = new PlatResumeDTO(
                    (long) json.getInt("id"),
                    json.getString("nom"),
                    BigDecimal.valueOf(json.getJsonNumber("prix").doubleValue())
            );
            return Optional.of(dto);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
