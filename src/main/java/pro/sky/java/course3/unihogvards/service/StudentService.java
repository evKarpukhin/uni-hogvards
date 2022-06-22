package pro.sky.java.course3.unihogvards.service;

import pro.sky.java.course3.unihogvards.Exception.StudentNotFoundException;
import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.stereotype.Service;
import pro.sky.java.course3.unihogvards.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentService) {
        this.studentRepository = studentService;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Студент с " + id + " не найден !"));
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getFindByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> getStudentsByFaculty(Long id) {
        return studentRepository.findStudentsByFacultyId(id);
    }

    public Integer getCountStudents(){
        return studentRepository.countStudents();
    }

    public Double getAvgAgeStudents(){
        return studentRepository.avgAgeStudents();
    }

    public Collection<Student> getLast5Students() {
        return studentRepository.find5LastStudents();
    }


}
