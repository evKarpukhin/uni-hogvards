package pro.sky.java.course3.unihogvards.controller;

import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course3.unihogvards.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/student/24
    public Student getStudentInfo(@PathVariable long id) {
        return studentService.findStudent(id);
    }

    @PostMapping // POST http://localhost:8080/student
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping // PUT http://localhost:8080/student
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/student/3
    public Student deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping({"age/{age}"}) // GET http://localhost:8080/student/age/24
    public List<Student> getStudents(@PathVariable int age) {
        return studentService.getStudentbyAge(age);
    }

}
