package com.jobtrack.controller;

import com.jobtrack.entity.Interview;
import com.jobtrack.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {
    private final InterviewService service;

    public InterviewController(InterviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Interview> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Interview get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/application/{applicationId}")
    public List<Interview> byApplication(@PathVariable Long applicationId) {
        return service.byApplication(applicationId);
    }

    @GetMapping("/upcoming")
    public List<Interview> upcoming() {
        return service.upcoming();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Interview save(@Valid @RequestBody Interview i) {
        return service.save(i);
    }

    @PutMapping("/{id}")
    public Interview update(@PathVariable Long id, @Valid @RequestBody Interview i) {
        return service.update(id, i);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
