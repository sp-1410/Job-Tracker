package com.jobtrack.controller;

import com.jobtrack.entity.OnlineAssessment;
import com.jobtrack.service.OnlineAssessmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class OnlineAssessmentController {
    private final OnlineAssessmentService service;
    public OnlineAssessmentController(OnlineAssessmentService service) { this.service = service; }

    @GetMapping public List<OnlineAssessment> all() { return service.all(); }
    @GetMapping("/{id}") public OnlineAssessment get(@PathVariable Long id) { return service.get(id); }
    @GetMapping("/application/{applicationId}") public OnlineAssessment byApplication(@PathVariable Long applicationId) { return service.getByApplication(applicationId); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public OnlineAssessment save(@Valid @RequestBody OnlineAssessment oa) { return service.save(oa); }
    @PutMapping("/{id}") public OnlineAssessment update(@PathVariable Long id, @Valid @RequestBody OnlineAssessment oa) { return service.update(id, oa); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
