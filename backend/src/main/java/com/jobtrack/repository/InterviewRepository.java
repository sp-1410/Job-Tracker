package com.jobtrack.repository;
import com.jobtrack.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByScheduledAtAfterOrderByScheduledAtAsc(LocalDateTime from);
    List<Interview> findByApplicationIdOrderByScheduledAtAsc(Long applicationId);
    long countByStatus(String status);
}
