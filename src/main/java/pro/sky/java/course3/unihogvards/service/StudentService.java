package pro.sky.java.course3.unihogvards.service;

import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.stereotype.Service;
import pro.sky.java.course3.unihogvards.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

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
        return studentService.findById(id).get();
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

    public List<Student> getStudentByAge(int age) {
        return studentService.findByAge(age);
    }
}
