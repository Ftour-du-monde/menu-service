package com.example.menuservice.adapters.out;

import com.example.menuservice.dto.CreateurDTO;
import com.example.menuservice.usecase.UtilisateurGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

/**
 * Adaptateur sortant implémentant {@link UtilisateurGateway} via l'API REST Plats et Utilisateurs.
 * <p>
 * Interroge l'endpoint {@code GET /utilisateurs/{id}} de l'API distante
 * ({@code http://localhost:8080/plats-utilisateurs/api}) pour vérifier l'existence
 * d'un abonné et récupérer son nom afin de l'inclure dans les réponses de menu.
 * </p>
 */
@ApplicationScoped
public class RestUtilisateurClient implements UtilisateurGateway {

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
    public Optional<CreateurDTO> findUtilisateurById(Long utilisateurId) {
        try (Response response = client.target(BASE_URL)
                .path("/utilisateurs/{id}")
                .resolveTemplate("id", utilisateurId)
                .request(MediaType.APPLICATION_JSON)
                .get()) {

            if (response.getStatus() != 200) {
                return Optional.empty();
            }

            JsonObject json = response.readEntity(JsonObject.class);
            CreateurDTO dto = new CreateurDTO(
                    (long) json.getInt("id"),
                    json.getString("nom"),
                    json.getString("prenom")
            );
            return Optional.of(dto);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
