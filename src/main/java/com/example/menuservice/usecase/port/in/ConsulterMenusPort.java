package com.example.menuservice.usecase.port.in;

import com.example.menuservice.dto.MenuResponse;

import java.util.List;
import java.util.Optional;

/**
 * Port entrant : consultation des menus.
 */
public interface ConsulterMenusPort {

    List<MenuResponse> tousLesMenus();

    Optional<MenuResponse> menuParId(Long id);
}
