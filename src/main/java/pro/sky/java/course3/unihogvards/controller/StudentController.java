package pro.sky.java.course3.unihogvards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course3.unihogvards.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}") // GET http://localhost:8080/student/24
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        return Optional.ofNullable(studentService.findStudent(id))
                .map(opt->ResponseEntity.ok(opt))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stunents") // GET http://localhost:8080/students
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/studentsfaculty") // GET http://localhost:8080/studentsfaculty
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@RequestParam Long id) {
        return ResponseEntity.ok(studentService.getStudentsByFaculty(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudentsBetweenAge(@RequestParam int minAge, @RequestParam int maxAge) {
        return ResponseEntity.ok(studentService.getFindByAgeBetween(minAge, maxAge));
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
    public Collection<Student> getStudents(@PathVariable int age) {
        return studentService.getStudentByAge(age);
    }

    @GetMapping("/count") // GET http://localhost:8080/student/count
    public Integer getCountStudents(){
        return studentService.getCountStudents();
    }

    @GetMapping("/avgage") // GET http://localhost:8080/student/avgage
    public Double getAvgAgeStudents(){
        return studentService.getAvgAgeStudents();
    }

    @GetMapping("/laststudents") // GET http://localhost:8080/last5students
    public ResponseEntity<Collection<Student>> getLast5Students() {
        Collection<Student> collection = studentService.getLast5Students();
        return ResponseEntity.ok(collection);
    }


}
