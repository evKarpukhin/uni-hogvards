package pro.sky.java.course3.unihogvards.controller;

import org.springframework.http.ResponseEntity;
import pro.sky.java.course3.unihogvards.model.Faculty;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course3.unihogvards.model.Student;
import pro.sky.java.course3.unihogvards.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}") // GET http://localhost:8080/faculty/4
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping // POST http://localhost:8080/faculty
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/faculties") // GET http://localhost:8080/student/all
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }


    @PutMapping // PUT http://localhost:8080/faculty
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("/{id}") // DELETE http://localhost:8080/faculty/12
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color/{color}") // GET http://localhost:8080/faculty/color/4
    public List<Faculty> getFaculties(@PathVariable String color) {
        return facultyService.getFacultyByColor(color);
    }

}
