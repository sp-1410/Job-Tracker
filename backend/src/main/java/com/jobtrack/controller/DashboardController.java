package com.jobtrack.controller;

import com.jobtrack.entity.ApplicationStatus;
import com.jobtrack.entity.OaResult;
import com.jobtrack.entity.Job;
import com.jobtrack.entity.Interview;
import com.jobtrack.repository.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final JobRepository jobs;
    private final ApplicationRepository applications;
    private final InterviewRepository interviews;
    private final OnlineAssessmentRepository assessments;

    public DashboardController(JobRepository jobs, ApplicationRepository applications, InterviewRepository interviews, OnlineAssessmentRepository assessments) {
        this.jobs = jobs; this.applications = applications; this.interviews = interviews; this.assessments = assessments;
    }

    @GetMapping("/summary")
    public Map<String,Object> summary() {
        Map<String,Object> m = new LinkedHashMap<>();
        m.put("totalJobs", jobs.count());
        m.put("totalApplications", applications.count());
        Map<String,Long> statuses = new LinkedHashMap<>();
        for (ApplicationStatus s : ApplicationStatus.values()) statuses.put(s.name(), applications.countByStatus(s));
        m.put("applicationsByStatus", statuses);

        List<Interview> upcoming = interviews.findByScheduledAtAfterOrderByScheduledAtAsc(LocalDateTime.now());
        m.put("upcomingInterviews", upcoming.size());
        m.put("upcomingInterviewItems", upcoming.stream().limit(5).map(i -> {
            Map<String,Object> x = new LinkedHashMap<>();
            x.put("id", i.getId()); x.put("roundName", i.getRoundName()); x.put("scheduledAt", i.getScheduledAt()); x.put("status", i.getStatus());
            if (i.getApplication() != null) { x.put("applicationId", i.getApplication().getId()); if (i.getApplication().getJob() != null) x.put("jobTitle", i.getApplication().getJob().getTitle()); }
            return x;
        }).toList());

        Map<String,Long> oa = new LinkedHashMap<>();
        for (OaResult r : OaResult.values()) oa.put(r.name(), assessments.countByResult(r));
        m.put("oaSummary", oa);

        List<Job> deadlineJobs = jobs.findByDeadlineGreaterThanEqualOrderByDeadlineAsc(LocalDate.now());
        m.put("upcomingDeadlines", deadlineJobs.stream().limit(5).map(j -> {
            Map<String,Object> x = new LinkedHashMap<>(); x.put("id", j.getId()); x.put("title", j.getTitle()); x.put("deadline", j.getDeadline());
            if (j.getCompany()!=null) x.put("company", j.getCompany().getName()); return x;
        }).toList());

        Map<String,Long> interviewStatuses = new LinkedHashMap<>();
        interviewStatuses.put("PENDING", interviews.countByStatus("PENDING"));
        interviewStatuses.put("PASSED", interviews.countByStatus("PASSED"));
        interviewStatuses.put("FAILED", interviews.countByStatus("FAILED"));
        m.put("interviewSummary", interviewStatuses);
        return m;
    }
}
