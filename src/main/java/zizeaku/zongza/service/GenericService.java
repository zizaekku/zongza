package zizeaku.zongza.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zizeaku.zongza.domain.Generic;
import zizeaku.zongza.repository.GenericRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class GenericService {
    private final GenericRepository genericRepository;

    public Long join(Generic generic) {
        genericRepository.save(generic);
        return generic.getId();
    }

    public Iterable<Generic> findAllGenerics() {
        return genericRepository.findAll();
    }

    public Optional<Generic> findGeneric(Long genericId) {
        return genericRepository.findById(genericId);
    }
    
    public void deleteGeneric(Long genericId) {
        genericRepository.deleteById(genericId);
    }
}
