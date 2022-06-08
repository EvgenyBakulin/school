package ru.hogwarts.school.interfac;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Collection<Student> getAllStudents();

    Student addStudent(Student student);

    Optional<Student> getStudent(long myId);

    Student updateStudent(Student student);

    void deleteStudent(long myId);

    List<Student> toAge(int age);
}
