package com.jobtrack.repository;

import com.jobtrack.entity.OnlineAssessment;
import com.jobtrack.entity.OaResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OnlineAssessmentRepository extends JpaRepository<OnlineAssessment, Long> {
    Optional<OnlineAssessment> findByApplicationId(Long applicationId);
    long countByResult(OaResult result);
}
