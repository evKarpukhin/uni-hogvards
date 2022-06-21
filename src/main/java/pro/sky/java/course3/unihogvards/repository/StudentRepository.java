package pro.sky.java.course3.unihogvards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.java.course3.unihogvards.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Student findById(int id);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    Collection<Student> findStudentsByFacultyId(Long id);

}
