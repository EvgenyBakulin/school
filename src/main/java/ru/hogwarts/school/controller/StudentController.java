package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfac.StudentService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarServiceImpl;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService, AvatarServiceImpl avatarService) {
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
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/toAge")
    public Collection<Student> studentsToAge(@RequestParam(value = "minAge") int min, @RequestParam(value = "maxAge") int max) {
        return studentService.studentsToAgeBetween(min, max);
    }

    @GetMapping("/studentFaculty")
    public ResponseEntity<Faculty> getStudentFaculty(@RequestParam long id) {
        Faculty f = studentService.getStudentFaculty(id);
        return ResponseEntity.ok(f);
    }

    @GetMapping("/numberOfStudents")
    public int getNumberOfStudents() {
        return studentService.getNumberOfStudents();
    }

    @GetMapping("/averageAge")
    public double averageAge() {
        return studentService.averageAge();
    }

    //Новый метод потска студентов, имена которых начинаются с буквы А
    @GetMapping("/namesOnA")
    public ResponseEntity<List<String>> namesOfStudentsOnA() {
        List<String> list = studentService.studentsToLiterA();
        return ResponseEntity.ok(list);
    }

   //Новый метод поиска среднего возраста студентов
    @GetMapping("/averageAgeOfStudents")
    public double averageAgeOfStudents() {
        return studentService.averageAgeOfStudents();
    }

    @GetMapping("/lastStudents")
    public Collection<Student> lastStudents() {
        return studentService.lastStudentsOrderById();
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
