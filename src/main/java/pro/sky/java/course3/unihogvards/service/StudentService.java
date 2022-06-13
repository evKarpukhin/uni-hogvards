package pro.sky.java.course3.unihogvards.service;

import pro.sky.java.course3.unihogvards.Exception.StudentNotFoundException;
import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.stereotype.Service;
import pro.sky.java.course3.unihogvards.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentService;

    public StudentService(StudentRepository studentService) {
        this.studentService = studentService;
    }

    public Student createStudent(Student student) {
        return studentService.save(student);
    }

    public Student findStudent(long id) {
        final Student student = studentService.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Студент с " + id + " не найден !"));
        return student; //.get();
    }

    public Student editStudent(Student student) {
        return studentService.save(student);
    }

    public void deleteStudent(long id) {
        studentService.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentService.findAll();
    }

    public Collection<Student> getStudentByAge(int age) {
        return studentService.findByAge(age);
    }

    public Collection<Student> getFindByAgeBetween(int minAge, int maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> getStudentsByFaculty(Long id) {
        return studentService.findStudentsByFacultyId(id);
    }

}
