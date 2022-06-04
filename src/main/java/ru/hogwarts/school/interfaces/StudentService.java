package ru.hogwarts.school.interfaces;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Collection<Student> getAllStudents();

    Student addStudent(String name, int age);

    Student getStudent(long myId);

    Student updateStudent(long id, String name, int age);

    Student deleteStudent(long myId);

    List<Student> toAge(int age);
}
