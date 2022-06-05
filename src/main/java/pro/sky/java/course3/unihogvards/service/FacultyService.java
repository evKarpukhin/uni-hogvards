package pro.sky.java.course3.unihogvards.service;

import pro.sky.java.course3.unihogvards.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final Map<Long, Faculty> mapFaculties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        mapFaculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return mapFaculties.get(id);
    }

    public Faculty editFaculty (Faculty faculty){
        mapFaculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id){
        return mapFaculties.remove(id);
    }

    public List<Faculty> getFacultyColor(String color) {
        List<Faculty> lsFaculties;
        lsFaculties = mapFaculties.values().stream()
                .filter(st -> st.getColor().equals(color))
                .collect(Collectors.toList());
        return lsFaculties;
    }


}
