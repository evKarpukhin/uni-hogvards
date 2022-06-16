package pro.sky.java.course3.unihogvards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.java.course3.unihogvards.model.Faculty;
import pro.sky.java.course3.unihogvards.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByColor(String color);

    Collection<Faculty> findAllByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

    @Query("select fc.name from Faculty as fc, Student as st where fc.id = st.faculty.id and st.id = ?1")
    String findFacultyByStudentId(Long idStudent);
}
