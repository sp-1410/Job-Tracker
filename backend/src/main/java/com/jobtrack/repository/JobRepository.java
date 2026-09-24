package com.jobtrack.repository;
import com.jobtrack.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("select j from Job j where lower(j.title) like lower(concat('%', :q, '%')) or lower(j.company.name) like lower(concat('%', :q, '%'))")
    List<Job> search(@Param("q") String q);
    List<Job> findByDeadlineGreaterThanEqualOrderByDeadlineAsc(LocalDate from);
}
