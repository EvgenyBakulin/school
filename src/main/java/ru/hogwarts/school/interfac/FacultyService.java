package ru.hogwarts.school.interfac;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Collection<Faculty> getHogwarts();

    Faculty addFaculty(Faculty faculty);

    Optional<Faculty> getFaculty(long myId);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(long myId);

    List<Faculty> toColor(String color);
}
