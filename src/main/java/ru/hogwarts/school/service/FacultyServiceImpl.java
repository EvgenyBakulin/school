package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.WrongIDExeption;
import ru.hogwarts.school.interfac.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {

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

    public Collection<Faculty> toNameOrColor(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Collection<Student> getAllFacultyStudents(long id) {
        if (facultyRepository.existsById(id)) {
            return facultyRepository.findById(id).get().getStudents();
        } else {
            throw new WrongIDExeption();
        }
    }
}
