package ru.hogwarts.school.interfac;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Collection<Student> getAllStudents();

    Student addStudent(Student student);

    Student getStudent(long myId);

    Student updateStudent(Student student);

    void deleteStudent(long myId);

    Collection <Student> studentsToAgeBetween(int min, int max);

    Faculty getStudentFaculty(Long id);

    int getNumberOfStudents();

    double averageAge();

    double averageAgeofStudents();

    List<String> studentsToLiterA();

    Collection<Student> lastStudentsOrderById();

}
