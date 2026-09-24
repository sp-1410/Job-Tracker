package com.jobtrack.controller;

import com.jobtrack.entity.Resume;
import com.jobtrack.service.ResumeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    private final ResumeService service;

    public ResumeController(ResumeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Resume> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Resume get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resume save(@Valid @RequestBody Resume r) {
        return service.save(r);
    }

    @PutMapping("/{id}")
    public Resume update(@PathVariable Long id, @Valid @RequestBody Resume r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
