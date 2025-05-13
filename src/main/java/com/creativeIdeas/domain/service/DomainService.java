package com.creativeIdeas.domain.service;

import com.creativeIdeas.domain.dto.DomainDTO;
import com.creativeIdeas.domain.entity.Domain;
import com.creativeIdeas.domain.repository.DomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DomainService {
    private final DomainRepository domainRepository;

    public List<DomainDTO> getAllDomains(){
        return domainRepository.findAll().stream().map(row -> new DomainDTO(row.getName(), row.getDescription()))
                .collect(Collectors.toList());
    }

    public void createDomain(DomainDTO domainDTO){
        if(!domainRepository.existsByName(domainDTO.getName())) {
            domainRepository.save(new Domain(null, domainDTO.getName(), domainDTO.getDescription()));
        }
    }

    public boolean isValidDomain(String name){
        return domainRepository.existsByName(name);
    }

    public void validateDomain(String name){
        if(!isValidDomain(name)){
            throw new IllegalArgumentException("Invalid Domain : " + name);
        }
    }
}
