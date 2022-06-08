package pro.sky.java.course3.unihogvards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.course3.unihogvards.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findByColor(String color);
}
