package com.example.menuservice.adapters.out;

import com.example.menuservice.domain.Menu;
import com.example.menuservice.usecase.MenuRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaMenuRepository implements MenuRepository {

    @PersistenceContext(unitName = "menuPU")
    private EntityManager em;

    @Override
    public List<Menu> findAll() {
        return em.createQuery("SELECT m FROM MenuJpaEntity m", MenuJpaEntity.class)
                .getResultList().stream()
                .map(MenuMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return Optional.ofNullable(em.find(MenuJpaEntity.class, id))
                .map(MenuMapper::toDomain);
    }

    @Override
    @Transactional
    public Menu save(Menu menu) {
        MenuJpaEntity entity = MenuMapper.toEntity(menu);
        if (entity.getId() == null) {
            em.persist(entity);
            return MenuMapper.toDomain(entity);
        }
        return MenuMapper.toDomain(em.merge(entity));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        MenuJpaEntity entity = em.find(MenuJpaEntity.class, id);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }
}
