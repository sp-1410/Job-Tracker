package com.jobtrack.service;

import com.jobtrack.entity.Application;
import com.jobtrack.entity.OnlineAssessment;
import com.jobtrack.repository.ApplicationRepository;
import com.jobtrack.repository.OnlineAssessmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OnlineAssessmentService {
    private final OnlineAssessmentRepository repo;
    private final ApplicationRepository applicationRepo;

    public OnlineAssessmentService(OnlineAssessmentRepository repo, ApplicationRepository applicationRepo) {
        this.repo = repo; this.applicationRepo = applicationRepo;
    }

    @Transactional(readOnly = true)
    public List<OnlineAssessment> all() { return repo.findAll(); }

    @Transactional(readOnly = true)
    public OnlineAssessment get(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("OA not found: " + id));
    }

    @Transactional(readOnly = true)
    public OnlineAssessment getByApplication(Long applicationId) {
        return repo.findByApplicationId(applicationId).orElse(null);
    }

    public OnlineAssessment save(OnlineAssessment oa) {
        oa.setApplication(resolveApplication(oa.getApplication()));
        if (oa.getResult() == null) oa.setResult(com.jobtrack.entity.OaResult.PENDING);
        return repo.save(oa);
    }

    public OnlineAssessment update(Long id, OnlineAssessment oa) {
        OnlineAssessment old = get(id);
        old.setApplication(resolveApplication(oa.getApplication()));
        old.setPlatform(oa.getPlatform());
        old.setAssessmentDate(oa.getAssessmentDate());
        old.setDurationMinutes(oa.getDurationMinutes());
        old.setResult(oa.getResult());
        old.setScore(oa.getScore());
        old.setTopics(oa.getTopics());
        old.setNotes(oa.getNotes());
        return repo.save(old);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("OA not found: " + id);
        repo.deleteById(id);
    }

    private Application resolveApplication(Application a) {
        if (a == null || a.getId() == null) throw new IllegalArgumentException("A valid application id is required");
        return applicationRepo.findById(a.getId()).orElseThrow(() -> new NoSuchElementException("Application not found: " + a.getId()));
    }
}
