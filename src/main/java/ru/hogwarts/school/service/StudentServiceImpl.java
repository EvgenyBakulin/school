package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.WrongIDExeption;
import ru.hogwarts.school.interfac.StudentService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(long myId) {

        return studentRepository.findById(myId).orElseThrow(WrongIDExeption::new);
    }

    public Student updateStudent(Student student) {

        return studentRepository.save(student);
    }

    public void deleteStudent(long myId) {
        if (studentRepository.existsById(myId)) {
            studentRepository.deleteById(myId);
        } else {
            throw new WrongIDExeption();
        }
    }

    public Collection<Student> studentsToAgeBetween(int min, int max) {
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getStudentFaculty(Long id) {
        return studentRepository.findById(id).orElseThrow(WrongIDExeption::new).getFaculty();
    }

    public int getNumberOfStudents() {
       return studentRepository.getNumberOfStudents();
    }

    public double averageAge() {
        return studentRepository.averageAge();
    }

    public  Collection<Student> lastStudentsOrderById() {
        return studentRepository.lastStudentsOrderById();
    }

}
