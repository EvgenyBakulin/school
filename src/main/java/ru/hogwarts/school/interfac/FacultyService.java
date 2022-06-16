package ru.hogwarts.school.interfac;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface FacultyService {
    Collection<Faculty> getHogwarts();

    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(long myId);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(long myId);

    Collection <Faculty> toNameOrColor(String name, String color);

    Collection<Student> getAllFacultyStudents(long id);

}
