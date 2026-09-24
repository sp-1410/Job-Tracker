package com.jobtrack.repository;
import com.jobtrack.entity.Application;
import com.jobtrack.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(ApplicationStatus status);
    long countByStatus(ApplicationStatus status);
}
