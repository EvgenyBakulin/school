package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Collection<Student> getAllStudents() {
        logger.info("Вызван метод getAllStudents");
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        logger.info("Вызван метод addStudent");
        return studentRepository.save(student);
    }

    public Student getStudent(long myId) {
        logger.info("Вызван метод getStudent");
        return studentRepository.findById(myId)
                .orElseThrow(()->{logger.error("Нет студента с id "+myId);
            throw new WrongIDExeption(); });
    }

    public Student updateStudent(Student student) {
        logger.info("Вызван метод updateStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(long myId) {
        logger.info("Вызван метод deleteStudent");
        if (studentRepository.existsById(myId)) {
            studentRepository.deleteById(myId);
        } else {
            logger.error("Нет студента с id "+myId);
            throw new WrongIDExeption();
        }
    }

    public Collection<Student> studentsToAgeBetween(int min, int max) {
        logger.info("Вызван метод studentsToAgeBetween");
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getStudentFaculty(Long id) {
        logger.info("Вызван метод getStudentFaculty");
        return studentRepository.findById(id)
                .orElseThrow(()->{logger.error("Нет студента с id "+id);
                  throw new WrongIDExeption();})
                .getFaculty();
    }

    public int getNumberOfStudents() {
        logger.info("Вызван метод getNumberOfStudents");
        return studentRepository.getNumberOfStudents();
    }

    public double averageAge() {
        logger.info("Вызван метод averageAge");
        return studentRepository.averageAge();
    }

    public List<String> studentsToLiterA(){
        return studentRepository.findAll()
                .stream()
                .map(s->s.getName().toUpperCase())
                .filter(name->name.startsWith("A"))
                .sorted()
                .toList();
    }
    public double averageAgeofStudents(){
        logger.info("Вызван метод averageAgeOfStudents");
        return studentRepository.findAll()
                .stream()
                .map(i->i.getAge())
                .reduce(Integer::sum).get()/(double)studentRepository.count();
    }

    public Collection<Student> lastStudentsOrderById() {
        logger.info("Вызван метод lastStudentsOrderById");
        return studentRepository.lastStudentsOrderById();
    }

}
