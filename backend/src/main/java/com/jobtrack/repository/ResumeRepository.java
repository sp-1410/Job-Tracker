package com.jobtrack.repository;
import com.jobtrack.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ResumeRepository extends JpaRepository<Resume, Long> {}
