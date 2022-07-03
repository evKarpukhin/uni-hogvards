package pro.sky.java.course3.unihogvards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.java.course3.unihogvards.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Student findById(int id);

    Collection<Student> findByAgeBetween (int minAge, int maxAge);

    Collection<Student> findStudentsByFacultyId(Long id);

    @Query(value = "SELECT COUNT(*) FROM STUDENT", nativeQuery = true)
    Integer countStudents();

    @Query(value = "SELECT AVG(age) FROM STUDENT", nativeQuery = true)
    Double avgAgeStudents();

//    @Query(value = "SELECT S. FROM STUDENT S order by id DESC LIMIT 5", nativeQuery = true)
//    @Query(value = "SELECT S.to_json FROM STUDENT S order by id DESC LIMIT 5", nativeQuery = true)
    @Query(value = "SELECT S.id, S.name, S.age, S.faculty_id FROM STUDENT S order by id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> find5LastStudents();

}