package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfac.FacultyService;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity addFaculty(@RequestBody Faculty faculty) {
        Faculty f = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(f);
    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return facultyService.getHogwarts();
    }

    @GetMapping("/toColor")
    public List<Faculty> toColor(@RequestParam(value = "color") String color) {
        return facultyService.toColor(color);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Faculty>> getFaculty(@PathVariable long id) {
        Optional<Faculty> faculty = facultyService.getFaculty(id);
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty f = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(f);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
