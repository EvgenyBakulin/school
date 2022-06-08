package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.WrongIDExeption;
import ru.hogwarts.school.interfac.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Чтобы поработать и с телами, и с параметрами, с факультетами я работал чеерз тела, а со студентами - через параметры
@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    /*public Collection<Faculty> getHogwarts() {
        return faculties.values();
    }*/

    public Faculty addFaculty(Faculty faculty) {
       return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long myId) {
        if (facultyRepository.existsById(myId)) {
            return facultyRepository.getReferenceById(myId);
        } else {
            throw new WrongIDExeption();
        }
    }

    public Faculty updateFaculty(Faculty faculty) {
     return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long myId) {
        if (facultyRepository.existsById(myId)) {
            facultyRepository.deleteById(myId);
        } else {
            throw new WrongIDExeption();
        }
    }

   /* public List<Faculty> toColor(String color) {
        return faculties.values().stream()
                .filter(e -> e.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }*/
}
