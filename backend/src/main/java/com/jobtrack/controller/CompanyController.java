package com.jobtrack.controller;

import com.jobtrack.entity.Company;
import com.jobtrack.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService service;
    public CompanyController(CompanyService service) { this.service = service; }
    @GetMapping public List<Company> all() { return service.all(); }
    @GetMapping("/{id}") public Company get(@PathVariable Long id) { return service.get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Company save(@Valid @RequestBody Company c) { return service.save(c); }
    @PutMapping("/{id}") public Company update(@PathVariable Long id, @Valid @RequestBody Company c) { return service.update(id, c); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
