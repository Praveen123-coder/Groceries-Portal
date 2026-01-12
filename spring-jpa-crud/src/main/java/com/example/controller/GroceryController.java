package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Grocery;
import com.example.service.GroceryService;

@RestController
@RequestMapping("/api/groceries")
@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowCredentials = "true"
)
public class GroceryController {

    private final GroceryService service;

    public GroceryController(GroceryService service) {
        this.service = service;
    }

    @GetMapping("/get-all")
    public List<Grocery> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grocery> getById(@PathVariable Long id) {
        Grocery g = service.getById(id);
        if (g == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(g);
    }

    // ---------------- CREATE ----------------
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Grocery> create(@RequestBody Grocery grocery) {
        Grocery saved = service.create(grocery);
        return ResponseEntity.status(201).body(saved);
    }

    // ---------------- UPDATE ----------------
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Grocery> update(@PathVariable Long id, @RequestBody Grocery grocery) {
        Grocery updated = service.update(id, grocery);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // ---------------- DELETE ----------------
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
