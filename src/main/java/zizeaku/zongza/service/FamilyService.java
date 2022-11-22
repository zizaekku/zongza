package zizeaku.zongza.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zizeaku.zongza.domain.Family;
import zizeaku.zongza.repository.FamilyRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class FamilyService {
    private final FamilyRepository familyRepository;

    public Long join(Family family) {
        familyRepository.save(family);
        return family.getId();
    }

    public Iterable<Family> findAllFamilys() {
        return familyRepository.findAll();
    }

    public Optional<Family> findFamily(Long familyId) {
        return familyRepository.findById(familyId);
    }
    
    public void deleteFamily(Long familyId) {
        familyRepository.deleteById(familyId);
    }
}
