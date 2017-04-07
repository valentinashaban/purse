package com.endava.service;

import com.endava.model.Domain;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vsaban on 4/7/2017.
 */@Service
public interface DomainService {
    Domain saveDomain(Domain domain);

    void deleteDomain(Domain domain);

    Domain updateDomain(Domain domain);

    Domain getDomainById(Long id);

    List<Domain> getDomains();
}
