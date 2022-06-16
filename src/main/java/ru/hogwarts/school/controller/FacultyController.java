package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfac.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;


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

    @GetMapping("/toNameOrColor")
    public Collection<Faculty> toNameOrColor(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) String color) {
        return facultyService.toNameOrColor(name, color);
    }

    @GetMapping("/students")
    public Collection<Student> allStudents(@RequestParam long id) {
        return facultyService.getAllFacultyStudents(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
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
