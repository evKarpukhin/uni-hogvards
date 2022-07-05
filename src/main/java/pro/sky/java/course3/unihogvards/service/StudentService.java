package pro.sky.java.course3.unihogvards.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.sky.java.course3.unihogvards.Exception.StudentNotFoundException;
import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.stereotype.Service;
import pro.sky.java.course3.unihogvards.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentService) {
        this.studentRepository = studentService;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("There is not student with id = {}", id);
                            new StudentNotFoundException("Студент с " + id + " не найден !");
                            return null;
                        }
                );
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentByAge(int age) {
        logger.info("Was invoked method for get student by id");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getFindByAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for find students between minAge - maxAge  ");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> getStudentsByFaculty(Long id) {
        logger.info("Was invoked method for get students by faculty");
        return studentRepository.findStudentsByFacultyId(id);
    }

    public Integer getCountStudents() {
        logger.info("Was invoked method for count student");
        return studentRepository.countStudents();
    }

    public Double getAvgAgeStudents() {
        logger.info("Was invoked method for average age students");
        return studentRepository.avgAgeStudents();
    }

    public Collection<Student> getLast5Students() {
        logger.info("Was invoked method for get last 5 students");
        return studentRepository.find5LastStudents();
    }

    public Collection<String> gelAllStudentsA() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll().stream()
                        .map(n -> n.getName().toUpperCase(Locale.ROOT))
                        .filter(s -> s.startsWith("А"))
                        .sorted()
                        .collect(Collectors.toList());
    }

    public Double getAverageAgeStudents() {
        logger.info("Was invoked method for Average Age Students");
        return studentRepository.findAll().stream()
                .collect(Collectors.averagingDouble(n -> n.getAge()));
    }


}
