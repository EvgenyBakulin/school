package ru.hogwarts.school.interfac;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyService {
    Collection<Faculty> getHogwarts();

    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(long myId);

    Faculty updateFaculty(Faculty faculty);

    Faculty deleteFaculty(long myId);

    List<Faculty> toColor(String color);
}
