package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private StudentController studentController;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private StudentRepository studentRepository;

	@Test
	public void contextLoads() throws Exception{
		Assertions.assertNotNull(studentController);
	}

	@Test
	public void testAddStudent() throws Exception{
		Student student = new Student();
		student.setId(111);
		student.setName("aaaaa");
		student.setAge(11);

		org.assertj.core.api.Assertions.assertThat(this.restTemplate.postForObject("http://localhost:"
				+port+"/student",student,String.class)).isNotNull();
	}
	@Test
	public void testGetAllStudent() throws Exception {
		org.assertj.core.api.Assertions.assertThat(this.restTemplate.getForObject("http://localhost:"
				+port+"/student",String.class)).isNotNull();
	}

	@Test
	public void testGetStudent() throws Exception {
		Assertions.assertNotNull(this.restTemplate.getForObject("http://localhost:"
				+port+"/student/10",String.class));

	}

	/*@Test
	public void testDeleteStudent() throws Exception {
		Student student = new Student();
		student.setId(111);
		student.setName("aaaaa");
		student.setAge(11);
		org.assertj.core.api.Assertions.assertThat(this.restTemplate.postForObject("http://localhost:"
				+port+"/student",student,String.class)).isNotNull();
		ResponseEntity<String> response = restTemplate.exchange("/student/10",
				HttpMethod.DELETE,new HttpEntity<>(""), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Student student = new Student();
		student.setId(111);
		student.setName("aaaaa");
		student.setAge(11);

		org.assertj.core.api.Assertions
				.assertThat(this.restTemplate.postForObject("http://localhost:"
				+port+"/student",student,String.class))
				.isNotNull();
		org.assertj.core.api.Assertions
				.assertThat(this.restTemplate.delete("http://localhost:"
								+port+"/student/"+student.getId()
						        ,student,Void.class))
				.isNotNull();
	}*/

}
