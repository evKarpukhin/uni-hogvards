package pro.sky.java.course3.unihogvards;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.java.course3.unihogvards.controller.FacultyController;
import pro.sky.java.course3.unihogvards.model.Faculty;
import pro.sky.java.course3.unihogvards.repository.FacultyRepository;
import pro.sky.java.course3.unihogvards.service.FacultyService;

import java.util.ArrayList;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FacultyController.class)
public class UniFacultyApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void allFacultyTest() throws Exception {
        final String nameFaculty = "B2";
        final String colorFaculty = "Yellow";
        final long id = 1L;

        // send data to server to save
        final JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("color", colorFaculty);
        facultyObject.put("name", nameFaculty);
        // get data from server to compare
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(nameFaculty);
        faculty.setColor(colorFaculty);

        ArrayList<Faculty> clFac = new ArrayList<>();
        clFac.add(faculty);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findByColor(faculty.getColor())).thenReturn(Set.of(faculty));
        when(facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(any(String.class), any(String.class))).thenReturn(Set.of(faculty));
        when(facultyRepository.findFacultyByStudentId(id)).thenReturn(nameFaculty);

        // OK
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(nameFaculty))
                .andExpect(jsonPath("$.color").value(colorFaculty));

        // Ok
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/color/" + colorFaculty)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(nameFaculty))
                .andExpect(jsonPath("$[0].color").value(colorFaculty));

        // Ok
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/facstudent/?id="+id))
                .andExpect(status().isOk()) //receive
                .andExpect(content().string(containsString(nameFaculty)));

        // Ok
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); //receive


    }


}
