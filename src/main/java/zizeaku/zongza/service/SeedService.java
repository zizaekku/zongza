package zizeaku.zongza.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zizeaku.zongza.domain.Seed;
import zizeaku.zongza.repository.SeedRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class SeedService {
    private final SeedRepository seedRepository;

    // 새로운 종자 등록
    public Long join(Seed seed) {
        seedRepository.save(seed);
        return seed.getId();
    }

    // 종자 전체 조회
    public List<Seed> findAllSeeds() {
        return seedRepository.findAll();
    }

    // 종자 조회
    public Seed findSeed(Long seedId) {
        return seedRepository.findById(seedId);
    }
}
