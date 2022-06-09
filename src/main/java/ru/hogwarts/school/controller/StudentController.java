package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfac.StudentService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student s = studentService.addStudent(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public Collection<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable long id) {
        Optional<Student> student = studentService.getStudent(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/toAge")
    public Collection<Student> studentsToAge(@RequestParam(value = "minAge") int min,@RequestParam(value = "maxAge") int max) {
        return studentService.studentsToAgeBetween(min,max);
    }

    @GetMapping("/studentFaculty")
    public ResponseEntity<Faculty> getStudentFaculty(@RequestParam long id) {
        Faculty f = studentService.getStudentFaculty(id);
        return ResponseEntity.ok(f);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student s = studentService.updateStudent(student);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
