package ru.hogwarts.school.interfac;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    //Collection<Student> getAllStudents();

    Student addStudent(Student student);

    Student getStudent(long myId);

    Student updateStudent(Student student);

    void deleteStudent(long myId);

   // List<Student> toAge(int age);
}
