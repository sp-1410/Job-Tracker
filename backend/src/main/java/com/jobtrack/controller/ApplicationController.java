package com.jobtrack.controller;

import com.jobtrack.entity.*;
import com.jobtrack.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService service;
    public ApplicationController(ApplicationService service) { this.service = service; }
    @GetMapping public List<Application> all() { return service.all(); }
    @GetMapping("/{id}") public Application get(@PathVariable Long id) { return service.get(id); }
    @GetMapping("/{id}/history") public List<ApplicationStatusHistory> history(@PathVariable Long id) { return service.history(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Application save(@Valid @RequestBody Application a) { return service.save(a); }
    @PutMapping("/{id}") public Application update(@PathVariable Long id, @Valid @RequestBody Application a) { return service.update(id, a); }
    @PatchMapping("/{id}/status") public Application status(@PathVariable Long id, @RequestParam ApplicationStatus value) { return service.updateStatus(id, value); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
