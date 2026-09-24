package com.jobtrack.service;

import com.jobtrack.entity.*;
import com.jobtrack.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ApplicationService {
    private final ApplicationRepository repo;
    private final ApplicationStatusHistoryRepository historyRepo;
    private final JobRepository jobRepo;
    private final ResumeRepository resumeRepo;

    public ApplicationService(ApplicationRepository repo,
                               ApplicationStatusHistoryRepository historyRepo,
                               JobRepository jobRepo,
                               ResumeRepository resumeRepo) {
        this.repo = repo;
        this.historyRepo = historyRepo;
        this.jobRepo = jobRepo;
        this.resumeRepo = resumeRepo;
    }

    @Transactional(readOnly = true)
    public List<Application> all() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Application get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Application not found: " + id));
    }

    public Application save(Application a) {
        a.setJob(resolveJob(a.getJob()));
        a.setResume(resolveResume(a.getResume()));
        if (a.getStatus() == null) {
            a.setStatus(ApplicationStatus.SAVED);
        }
        Application saved = repo.save(a);
        // Record the starting status so the history is complete from creation.
        recordHistory(saved, null, saved.getStatus());
        return saved;
    }

    public Application update(Long id, Application a) {
        Application old = get(id);
        ApplicationStatus previous = old.getStatus();
        old.setJob(resolveJob(a.getJob()));
        old.setApplicationDate(a.getApplicationDate());
        old.setNotes(a.getNotes());
        old.setResume(resolveResume(a.getResume()));
        old.setStatus(a.getStatus() == null ? previous : a.getStatus());
        Application saved = repo.save(old);
        if (previous != saved.getStatus()) {
            recordHistory(saved, previous, saved.getStatus());
        }
        return saved;
    }

    public Application updateStatus(Long id, ApplicationStatus status) {
        Application old = get(id);
        ApplicationStatus previous = old.getStatus();
        old.setStatus(status);
        Application saved = repo.save(old);
        if (previous != status) {
            recordHistory(saved, previous, status);
        }
        return saved;
    }

    @Transactional(readOnly = true)
    public List<ApplicationStatusHistory> history(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Application not found: " + id);
        }
        return historyRepo.findByApplicationIdOrderByChangedAtAsc(id);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Application not found: " + id);
        }
        repo.deleteById(id);
    }

    private void recordHistory(Application application, ApplicationStatus oldStatus, ApplicationStatus newStatus) {
        ApplicationStatusHistory entry = new ApplicationStatusHistory();
        entry.setApplication(application);
        entry.setOldStatus(oldStatus);
        entry.setNewStatus(newStatus);
        historyRepo.save(entry);
    }

    private Job resolveJob(Job candidate) {
        if (candidate == null || candidate.getId() == null) {
            throw new IllegalArgumentException("A valid job id is required");
        }
        return jobRepo.findById(candidate.getId())
                .orElseThrow(() -> new NoSuchElementException("Job not found: " + candidate.getId()));
    }

    private Resume resolveResume(Resume candidate) {
        if (candidate == null || candidate.getId() == null) {
            return null;
        }
        return resumeRepo.findById(candidate.getId())
                .orElseThrow(() -> new NoSuchElementException("Resume not found: " + candidate.getId()));
    }
}
