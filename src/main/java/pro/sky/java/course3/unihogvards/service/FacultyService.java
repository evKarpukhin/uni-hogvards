package pro.sky.java.course3.unihogvards.service;

import pro.sky.java.course3.unihogvards.model.Faculty;
import org.springframework.stereotype.Service;
import pro.sky.java.course3.unihogvards.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Факультет с " + id + " не найден !")); //  .get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }


    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }


    public Collection<Faculty> getFindByColor(String color) {
        return facultyRepository.findAllByColorIgnoreCase(color);
    }

    public Collection<Faculty> getFindByName(String name) {
        return facultyRepository.findAllByNameIgnoreCase(name);
    }

    public String getFacultyByStudentId(Long id){
        return facultyRepository.findFacultyByStudentId(id);
    }

}
