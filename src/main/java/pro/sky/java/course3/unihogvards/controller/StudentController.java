package pro.sky.java.course3.unihogvards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course3.unihogvards.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    private final Object flag = new Object();

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}") // GET http://localhost:8080/student/24
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        return Optional.ofNullable(studentService.findStudent(id))
                .map(opt -> ResponseEntity.ok(opt))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/alla") // GET http://localhost:8080/student/alla
    public Collection<String> getAllStudentsA() {
        return studentService.gelAllStudentsA();
    }

    @GetMapping("/strimavgage") // GET http://localhost:8080/student/avgage
    public double getAverageAge() {
        return studentService.getAverageAgeStudents();
    }

    @GetMapping("/stunents") // GET http://localhost:8080/students
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/stdsinthreadunsinchon") // GET http://localhost:8080/stdsinthreadunsinchon
    public Collection<Student> getStudentsInThreadUnsynchronized() {
        Collection<Student> colStd = studentService.getAllStudents();
        if (!colStd.isEmpty() && (colStd.size() >= 6)) {
            List<String> arrayStd = colStd.stream()
                    .map((std) -> std.getName())
                    .toList();

            System.out.println(arrayStd.get(0));
            System.out.println(arrayStd.get(1));

            // Thread 1
            Thread thread1 = new Thread(() -> {
                System.out.println(arrayStd.get(2));
                System.out.println(arrayStd.get(3));
            });

            // Thread 2
            Thread thread2 = new Thread(() -> {
                System.out.println(arrayStd.get(4));
                System.out.println(arrayStd.get(5));
            });

            thread1.start();
            thread2.start();
        }
        return studentService.getAllStudents();
    }


    @GetMapping("/stdsinthreadonsinchon") // GET http://localhost:8080/stdsinthreadonsinchon
    public Collection<Student> getStudentsInThreadOnSynchronized() {
        Collection<Student> colStd = studentService.getAllStudents();
        if (!colStd.isEmpty() && (colStd.size() >= 6)) {
            List<String> arrayStd = colStd.stream()
                    .map((std) -> std.getName())
                    .toList();

            System.out.println(arrayStd.get(0));
            System.out.println(arrayStd.get(1));

            // Thread 1
            Thread thread1 = new Thread(() -> {
                synchronized (flag) {
                    studentService.outputName(arrayStd.get(2));
                    studentService.outputName(arrayStd.get(3));
                }
            });

            // Thread 2
            Thread thread2 = new Thread(() -> {
                synchronized (flag) {
                    studentService.outputName(arrayStd.get(4));
                    studentService.outputName(arrayStd.get(5));
                }
            });

            thread1.start();
            thread2.start();
        }
        return studentService.getAllStudents();
    }


    @GetMapping("/studentsfaculty") // GET http://localhost:8080/studentsfaculty
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@RequestParam Long id) {
        return ResponseEntity.ok(studentService.getStudentsByFaculty(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudentsBetweenAge(@RequestParam int minAge,
                                                                     @RequestParam int maxAge) {
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
    public Integer getCountStudents() {
        return studentService.getCountStudents();
    }

    @GetMapping("/avgage") // GET http://localhost:8080/student/avgage
    public Double getAvgAgeStudents() {
        return studentService.getAvgAgeStudents();
    }

    @GetMapping("/laststudents") // GET http://localhost:8080/student/last5students
    public ResponseEntity<Collection<Student>> getLast5Students() {
        Collection<Student> collection = studentService.getLast5Students();
        return ResponseEntity.ok(collection);
    }

}
