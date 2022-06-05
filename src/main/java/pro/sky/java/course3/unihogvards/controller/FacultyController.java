package pro.sky.java.course3.unihogvards.controller;

import pro.sky.java.course3.unihogvards.model.Faculty;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course3.unihogvards.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}") // GET http://localhost:8080/faculty/4
    public Faculty getFacultyInfo(@PathVariable long id) {
        return facultyService.findFaculty(id);
    }

    @PostMapping // POST http://localhost:8080/faculty
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping // PUT http://localhost:8080/faculty
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("/{id}") // DELETE http://localhost:8080/faculty/12
    public Faculty deleteFaculty(@PathVariable long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("/color/{color}") // GET http://localhost:8080/faculty/color/4
    public List<Faculty> getStudents(@PathVariable String color) {
        return facultyService.getFacultyColor(color);
    }

}
