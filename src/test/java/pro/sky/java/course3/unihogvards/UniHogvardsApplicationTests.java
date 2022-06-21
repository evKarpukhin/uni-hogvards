package pro.sky.java.course3.unihogvards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import pro.sky.java.course3.unihogvards.controller.StudentController;
import pro.sky.java.course3.unihogvards.model.Student;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UniHogvardsApplicationTests {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/stunents", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudent() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/stunent/1", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsFaculty() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/studentsfaculty", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsBetweenAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?minAge=29&maxAge=50", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/30", String.class))
                .isNotNull();
    }


    @Test
    public void testEditStudent() throws Exception {
        Student student = restTemplate.getForObject("http://localhost:" + port + "/student/15", Student.class);
        student.setName("МАША");

       this.restTemplate.put("http://localhost:" + port + "/student/", student);
    }


    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student(1L,"Сергей", 25);

        Student student1 = restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);
        student.setId(student1.getId());

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
                .isNotNull();

        this.restTemplate.delete("http://localhost:" + port + "/student/{id}",  student.getId());

    }


}
