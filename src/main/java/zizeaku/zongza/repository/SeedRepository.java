package zizeaku.zongza.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import zizeaku.zongza.domain.Seed;

@Repository
public interface SeedRepository extends CrudRepository<Seed, Long>{
}
