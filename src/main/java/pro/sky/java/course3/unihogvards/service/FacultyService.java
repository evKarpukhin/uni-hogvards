package pro.sky.java.course3.unihogvards.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.sky.java.course3.unihogvards.Exception.FacultyNotFoundException;
import pro.sky.java.course3.unihogvards.model.Faculty;
import org.springframework.stereotype.Service;
import pro.sky.java.course3.unihogvards.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Stream;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for find faculty");
        return facultyRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("There is not faculty with id = {}", id);
                            new FacultyNotFoundException("Факультет с " + id + " не найден !");
                            return null;
                        }
                ); //  .get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        logger.info("Was invoked method for get faculty by color");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> getFindByNameOrColor(String name, String color) {
        logger.info("Was invoked method for find faculty by name or color");
        return facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

/*
    public Collection<Faculty> getFindByName(String name) {
        return facultyRepository.findAllByNameIgnoreCase(name);
    }
*/

    public String getFacultyByStudentId(Long id) {
        logger.info("Was invoked method for get faculty by student id");
        return facultyRepository.findFacultyByStudentId(id);
    }

    public String getMaxNameFaculty() {
        return facultyRepository.findAll().stream()
                .map(s -> s.getName())
                .max((s1, s2) -> s1.length() - s2.length())
                .get();
    }

    public Integer getMinTime() { //1784293664 / 1784293664 / 1784293664
        int sum = Stream
                .iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        return sum;
    }
}
