package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfac.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
//Чтобы поработать и с телами, и с параметрами, с факультетами я работал чеерз тела, а со студентами - через параметры
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity <Student> addStudent (@RequestParam(value = "name") String name, @RequestParam(value = "age") int age){
        Student student = studentService.addStudent(name,age);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public Collection<Student> getAll(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/toAge")
    public List<Student> studentsToAge(@RequestParam (value = "age") int age) {
        return studentService.toAge(age);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestParam (value = "id") long id, @RequestParam(value = "newName") String name,
                                 @RequestParam(value = "newAge") int age) {
        Student student = studentService.updateStudent(id,name,age);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Student> deleteStudent(@PathVariable long id) {
        Student student = studentService.deleteStudent(id);
        return ResponseEntity.ok(student);
    }

}
