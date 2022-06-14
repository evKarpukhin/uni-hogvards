package pro.sky.java.course3.unihogvards.controller;

import org.springframework.http.ResponseEntity;
import pro.sky.java.course3.unihogvards.model.Faculty;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course3.unihogvards.service.FacultyService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}") // GET http://localhost:8080/faculty/4
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        return Optional.ofNullable(facultyService.findFaculty(id))
                .map(st -> ResponseEntity.ok(st))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST http://localhost:8080/faculty
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/faculties") // GET http://localhost:8080/ffaculties
    public ResponseEntity<Collection<Faculty>> getAllFacultyByNameOrColor(@RequestParam(required = false)
                                                                                  String searchNameOrColor) {
        if (searchNameOrColor != null) {
            return ResponseEntity.ok(facultyService.getFindByNameOrColor(searchNameOrColor, searchNameOrColor));
        }
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("/facstudent") // GET http://localhost:8080/facstudent
    public String getFacultyByStudentId(@RequestParam Long id) {
        return facultyService.getFacultyByStudentId(id);
    }


    @PutMapping // PUT http://localhost:8080/faculty
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("/{id}") // DELETE http://localhost:8080/faculty/12
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color/{color}") // GET http://localhost:8080/faculty/color/4
    public Collection<Faculty> getFaculties(@PathVariable String color) {
        return facultyService.getFacultyByColor(color);
    }

}
