package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.interfaces.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;
//Чтобы поработать и с телами, и с параметрами, с факультетами я работал чеерз тела, а со студентами - через параметры
@Service
public class StudentServiceImpl implements StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private long id = 0;

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Student addStudent(String name, int age) {
        Student s = new Student(id, name, age);
        students.put(id, s);
        id++;
        return s;
    }

    public Student getStudent(long myId) {
        return students.get(myId);
    }

    public Student updateStudent(long id, String name, int age) {
        Student s = students.get(id);
        s.setName(name);
        s.setAge(age);
        students.put(s.getId(), s);
        return s;
    }

    public Student deleteStudent(long myId) {
        return students.remove(myId);
    }

    public List<Student> toAge(int age) {
        List<Student> listToAge = students.values().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
        return listToAge;
    }
}
