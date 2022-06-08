package pro.sky.java.course3.unihogvards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.course3.unihogvards.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAge(int age);

}
