package ru.hogwarts.school;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.interfac.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
public class SchoolApplicationMockTests {
    @Autowired
    private MockMvc mock;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void testAddFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        String name = "aaaa";
        String color = "ffffff";
        facultyObject.put("name",name);
        facultyObject.put("color",color);
        Faculty f = new Faculty();
        f.setId(100);
        f.setName(name);
        f.setColor(color);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(f);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(f));
    }
}
