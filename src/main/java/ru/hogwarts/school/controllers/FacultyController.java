package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfaces.FacultyService;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

//Чтобы поработать и с телами, и с параметрами, с факультетами я работал чеерз тела, а со студентами - через параметры
@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/add")
    public ResponseEntity addFaculty(@RequestBody Faculty faculty) {
        Faculty f = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(f);
    }

    @GetMapping("/all")
    public Collection<Faculty> getAll() {
        return facultyService.getHogwarts();
    }

    @GetMapping("/toColor")
    public List<Faculty> toColor(@RequestParam(value = "color") String color) {
        return facultyService.toColor(color);
    }

    @GetMapping("/{id}")
    public ResponseEntity getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("/change")
    public ResponseEntity updateFaculty(@RequestBody Faculty faculty) {
        Faculty f = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(f);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.deleteFaculty(id);
        return ResponseEntity.ok(faculty);
    }
}
