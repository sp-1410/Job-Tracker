package com.jobtrack.repository;
import com.jobtrack.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CompanyRepository extends JpaRepository<Company, Long> {}
