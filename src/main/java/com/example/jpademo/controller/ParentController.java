package com.example.jpademo.controller;

import com.example.jpademo.model.Child;
import com.example.jpademo.model.Parent;
import com.example.jpademo.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping("/parents")
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
        if (parent.getChildren() != null) {
            parent.getChildren().forEach(c -> c.setParent(parent));
        }
        Parent saved = parentService.saveParent(parent);
        return ResponseEntity.created(URI.create("/api/parents/" + saved.getId())).body(saved);
    }

    @GetMapping("/parents/{id}")
    public ResponseEntity<Parent> getParent(@PathVariable Long id) {
        Optional<Parent> p = parentService.getParent(id);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/children/{id}")
    public ResponseEntity<Child> getChild(@PathVariable Long id) {
        Optional<Child> c = parentService.getChild(id);
        return c.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/children/{id}")
    public ResponseEntity<Void> deleteChild(@PathVariable Long id) {
        parentService.deleteChild(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/parents/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
