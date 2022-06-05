package pro.sky.java.course3.unihogvards.service;

import pro.sky.java.course3.unihogvards.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> mapStudents = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        mapStudents.put(lastId, student);
        return student;
    }

    public Student findStudent(long id) {
        return mapStudents.get(id);
    }

    public Student editStudent (Student student){
        mapStudents.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id){
        return mapStudents.remove(id);
    }

    public List<Student> getStudentbyAge(int age) {
        List<Student> lsStudents;
        lsStudents = mapStudents.values().stream()
                .filter(st -> st.getAge() == age)
                .collect(Collectors.toList());
        return lsStudents;
    }
}
