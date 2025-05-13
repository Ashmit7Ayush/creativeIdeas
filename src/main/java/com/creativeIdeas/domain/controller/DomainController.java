package com.creativeIdeas.domain.controller;

import com.creativeIdeas.domain.dto.DomainDTO;
import com.creativeIdeas.domain.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DomainController {
    private final DomainService domainService;

    @GetMapping
    public ResponseEntity<List<DomainDTO>> getAllDomains(){
        return ResponseEntity.of(Optional.ofNullable(domainService.getAllDomains()));
    }

    @PostMapping
    public void createDomain(@RequestBody DomainDTO domainDTO){
        domainService.createDomain(domainDTO);
    }
}
