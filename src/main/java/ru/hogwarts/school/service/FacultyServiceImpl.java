package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.WrongIDExeption;
import ru.hogwarts.school.interfac.FacultyService;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//Чтобы поработать и с телами, и с параметрами, с факультетами я работал чеерз тела, а со студентами - через параметры
@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long id = 0;

    public Collection<Faculty> getHogwarts() {
        return faculties.values();
    }

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(id);
        faculties.put(id, faculty);
        id++;
        return faculty;
    }

    public Faculty getFaculty(long myId) {
        if (faculties.containsKey(myId)) {
        return faculties.get(myId);}
        else throw new WrongIDExeption();
    }

    public Faculty updateFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long myId) {
        if (faculties.containsKey(myId)) {
        return faculties.remove(myId);}
        else throw new WrongIDExeption();
    }

    public List<Faculty> toColor(String color) {
        return faculties.values().stream()
                .filter(e -> e.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
