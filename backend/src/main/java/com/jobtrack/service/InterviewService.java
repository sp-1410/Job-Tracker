package com.jobtrack.service;

import com.jobtrack.entity.Application;
import com.jobtrack.entity.Interview;
import com.jobtrack.repository.ApplicationRepository;
import com.jobtrack.repository.InterviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class InterviewService {
    private final InterviewRepository repo;
    private final ApplicationRepository applicationRepo;

    public InterviewService(InterviewRepository repo, ApplicationRepository applicationRepo) {
        this.repo = repo;
        this.applicationRepo = applicationRepo;
    }

    @Transactional(readOnly = true)
    public List<Interview> all() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Interview get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Interview not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Interview> upcoming() {
        return repo.findByScheduledAtAfterOrderByScheduledAtAsc(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<Interview> byApplication(Long applicationId) {
        if (!applicationRepo.existsById(applicationId)) throw new NoSuchElementException("Application not found: " + applicationId);
        return repo.findByApplicationIdOrderByScheduledAtAsc(applicationId);
    }

    public Interview save(Interview i) {
        i.setApplication(resolveApplication(i.getApplication()));
        return repo.save(i);
    }

    public Interview update(Long id, Interview i) {
        Interview old = get(id);
        old.setApplication(resolveApplication(i.getApplication()));
        old.setRoundName(i.getRoundName());
        old.setInterviewType(i.getInterviewType());
        old.setScheduledAt(i.getScheduledAt());
        old.setMeetingLink(i.getMeetingLink());
        old.setStatus(i.getStatus());
        old.setTopics(i.getTopics());
        old.setNotes(i.getNotes());
        old.setFeedback(i.getFeedback());
        old.setPreparationChecklist(i.getPreparationChecklist());
        return repo.save(old);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Interview not found: " + id);
        }
        repo.deleteById(id);
    }

    private Application resolveApplication(Application candidate) {
        if (candidate == null || candidate.getId() == null) {
            throw new IllegalArgumentException("A valid application id is required");
        }
        return applicationRepo.findById(candidate.getId())
                .orElseThrow(() -> new NoSuchElementException("Application not found: " + candidate.getId()));
    }
}
