package com.jobtrack.service;

import com.jobtrack.entity.Company;
import com.jobtrack.entity.Job;
import com.jobtrack.repository.CompanyRepository;
import com.jobtrack.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class JobService {
    private final JobRepository repo;
    private final CompanyRepository companyRepo;

    public JobService(JobRepository repo, CompanyRepository companyRepo) {
        this.repo = repo;
        this.companyRepo = companyRepo;
    }

    @Transactional(readOnly = true)
    public List<Job> all() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Job> search(String q) {
        return (q == null || q.isBlank()) ? all() : repo.search(q);
    }

    @Transactional(readOnly = true)
    public Job get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Job not found: " + id));
    }

    public Job save(Job j) {
        j.setCompany(resolveCompany(j.getCompany()));
        return repo.save(j);
    }

    public Job update(Long id, Job j) {
        Job old = get(id);
        old.setTitle(j.getTitle());
        old.setLocation(j.getLocation());
        old.setWorkMode(j.getWorkMode());
        old.setJobType(j.getJobType());
        old.setSource(j.getSource());
        old.setUrl(j.getUrl());
        old.setDescription(j.getDescription());
        old.setSalaryRange(j.getSalaryRange());
        old.setDeadline(j.getDeadline());
        old.setCompany(resolveCompany(j.getCompany()));
        return repo.save(old);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Job not found: " + id);
        }
        repo.deleteById(id);
    }

    /**
     * A job must reference an existing, persisted Company. Rather than trusting
     * whatever nested Company payload the client sent, resolve the managed
     * entity by id so stray/stale fields on the nested object can't leak in
     * and so a bad id fails fast with a clear error instead of a raw FK
     * constraint violation from the database.
     */
    private Company resolveCompany(Company candidate) {
        if (candidate == null || candidate.getId() == null) {
            throw new IllegalArgumentException("A valid company id is required");
        }
        return companyRepo.findById(candidate.getId())
                .orElseThrow(() -> new NoSuchElementException("Company not found: " + candidate.getId()));
    }
}
