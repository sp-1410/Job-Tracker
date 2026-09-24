package com.jobtrack.service;

import com.jobtrack.entity.Company;
import com.jobtrack.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CompanyService {
    private final CompanyRepository repo;

    public CompanyService(CompanyRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Company> all() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Company get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Company not found: " + id));
    }

    public Company save(Company c) {
        return repo.save(c);
    }

    public Company update(Long id, Company c) {
        Company old = get(id);
        old.setName(c.getName());
        old.setWebsite(c.getWebsite());
        old.setIndustry(c.getIndustry());
        old.setLocation(c.getLocation());
        old.setNotes(c.getNotes());
        return repo.save(old);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Company not found: " + id);
        }
        repo.deleteById(id);
    }
}
