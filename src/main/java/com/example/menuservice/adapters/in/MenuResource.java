package com.example.menuservice.adapters.in;

import com.example.menuservice.dto.MenuRequest;
import com.example.menuservice.dto.MenuResponse;
import com.example.menuservice.usecase.AjoutPlatResultat;
import com.example.menuservice.usecase.RetraitPlatResultat;
import com.example.menuservice.usecase.port.in.ConsulterMenusPort;
import com.example.menuservice.usecase.port.in.CreerMenuPort;
import com.example.menuservice.usecase.port.in.ModifierMenuPort;
import com.example.menuservice.usecase.port.in.SupprimerMenuPort;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/menus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuResource {

    @Inject
    private ConsulterMenusPort consulterMenusPort;

    @Inject
    private CreerMenuPort creerMenuPort;

    @Inject
    private ModifierMenuPort modifierMenuPort;

    @Inject
    private SupprimerMenuPort supprimerMenuPort;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getAllMenus() {
        List<MenuResponse> menus = consulterMenusPort.tousLesMenus();
        return Response.ok(menus).build();
    }

    @POST
    public Response createMenu(MenuRequest request) {
        try {
            return creerMenuPort.creer(request)
                    .map(created -> {
                        URI location = uriInfo.getAbsolutePathBuilder()
                                .path(String.valueOf(created.getId()))
                                .build();
                        return Response.created(location).entity(created).build();
                    })
                    .orElse(Response.status(Response.Status.NOT_FOUND)
                            .entity("Créateur introuvable : " + request.getCreateurId())
                            .build());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getMenuById(@PathParam("id") Long id) {
        return consulterMenusPort.menuParId(id)
                .map(m -> Response.ok(m).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Menu introuvable : " + id)
                        .build());
    }

    @PUT
    @Path("/{id}")
    public Response updateMenu(@PathParam("id") Long id, MenuRequest request) {
        return modifierMenuPort.renommer(id, request)
                .map(m -> Response.ok(m).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Menu introuvable : " + id)
                        .build());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMenu(@PathParam("id") Long id) {
        if (supprimerMenuPort.supprimer(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Menu introuvable : " + id)
                .build();
    }

    @PUT
    @Path("/{id}/plats/{platId}")
    public Response addPlatToMenu(@PathParam("id") Long id, @PathParam("platId") Long platId) {
        AjoutPlatResultat resultat = modifierMenuPort.ajouterPlat(id, platId);
        return switch (resultat.getStatut()) {
            case SUCCES          -> Response.ok(resultat.getResponse()).build();
            case MENU_INTROUVABLE -> Response.status(Response.Status.NOT_FOUND)
                    .entity("Menu introuvable : " + id).build();
            case PLAT_INTROUVABLE -> Response.status(Response.Status.NOT_FOUND)
                    .entity("Plat introuvable : " + platId).build();
            case DEJA_PRESENT    -> Response.status(Response.Status.CONFLICT)
                    .entity("Le plat " + platId + " est déjà présent dans le menu " + id).build();
        };
    }

    @DELETE
    @Path("/{id}/plats/{platId}")
    public Response removePlatFromMenu(@PathParam("id") Long id, @PathParam("platId") Long platId) {
        RetraitPlatResultat resultat = modifierMenuPort.retirerPlat(id, platId);
        return switch (resultat.getStatut()) {
            case SUCCES           -> Response.ok(resultat.getResponse()).build();
            case MENU_INTROUVABLE -> Response.status(Response.Status.NOT_FOUND)
                    .entity("Menu introuvable : " + id).build();
            case PLAT_ABSENT      -> Response.status(Response.Status.NOT_FOUND)
                    .entity("Plat " + platId + " absent du menu " + id).build();
        };
    }
}
