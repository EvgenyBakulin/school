package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.WrongIDExeption;
import ru.hogwarts.school.interfac.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Collection<Faculty> getHogwarts() {
        return facultyRepository.findAll();
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFaculty(long myId) {
        if (facultyRepository.existsById(myId)) {
            return facultyRepository.findById(myId);
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

    public List<Faculty> toColor(String color) {
        return facultyRepository.findAll().stream()
                .filter(e -> e.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
