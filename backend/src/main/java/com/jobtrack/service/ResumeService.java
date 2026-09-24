package com.jobtrack.service;

import com.jobtrack.entity.Resume;
import com.jobtrack.repository.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ResumeService {
    private final ResumeRepository repo;

    public ResumeService(ResumeRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Resume> all() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Resume get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resume not found: " + id));
    }

    public Resume save(Resume r) {
        return repo.save(r);
    }

    public Resume update(Long id, Resume r) {
        Resume old = get(id);
        old.setName(r.getName());
        old.setVersionLabel(r.getVersionLabel());
        old.setFileUrl(r.getFileUrl());
        old.setTargetRole(r.getTargetRole());
        old.setNotes(r.getNotes());
        return repo.save(old);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Resume not found: " + id);
        }
        repo.deleteById(id);
    }
}
