package zizeaku.zongza.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import zizeaku.zongza.domain.Seed;

@Repository
@RequiredArgsConstructor
public class SeedRepository {
    private final EntityManager em;

    public void save(Seed seed) {
        em.persist((seed));
    }

    public Seed findById(Long id) {
        return em.find(Seed.class, id);
    }

    public List<Seed> findAll() {
        return em.createQuery("select s from Seed s", Seed.class).getResultList();
    }
}
