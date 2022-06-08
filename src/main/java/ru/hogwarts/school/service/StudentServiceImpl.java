package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.WrongIDExeption;
import ru.hogwarts.school.interfac.StudentService;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

//Чтобы поработать и с телами, и с параметрами, с факультетами я работал чеерз тела, а со студентами - через параметры
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    public StudentServiceImpl (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
   /* public Collection<Student> getAllStudents() {
        return students.values();
    }*/

    public Student addStudent(Student student) {
       return studentRepository.save(student);
    }

    public Student getStudent(long myId) {
        if (studentRepository.existsById(myId)) {
            return studentRepository.getReferenceById(myId);
        } else {
            throw new WrongIDExeption();
        }
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

  /*  public List<Student> toAge(int age) {
        return students.values().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }*/
}
