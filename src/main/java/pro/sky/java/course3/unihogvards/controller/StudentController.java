package pro.sky.java.course3.unihogvards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course3.unihogvards.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}") // GET http://localhost:8080/student/24
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student st = studentService.findStudent(id);
        if (st == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(st);
    }

    @GetMapping("/stunents") // GET http://localhost:8080/student/all
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping // POST http://localhost:8080/student
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping // PUT http://localhost:8080/student
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student st = studentService.editStudent(student);
        if (st == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(st);
    }

    @DeleteMapping("/{id}") // DELETE http://localhost:8080/student/3
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{age}") // GET http://localhost:8080/student/age/24
    public List<Student> getStudents(@PathVariable int age) {
        return studentService.getStudentByAge(age);
    }

}
