package com.jobtrack.controller;

import com.jobtrack.entity.Job;
import com.jobtrack.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService service;
    public JobController(JobService service) { this.service = service; }
    @GetMapping public List<Job> all(@RequestParam(required=false) String q) { return service.search(q); }
    @GetMapping("/{id}") public Job get(@PathVariable Long id) { return service.get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Job save(@Valid @RequestBody Job j) { return service.save(j); }
    @PutMapping("/{id}") public Job update(@PathVariable Long id, @Valid @RequestBody Job j) { return service.update(id, j); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
