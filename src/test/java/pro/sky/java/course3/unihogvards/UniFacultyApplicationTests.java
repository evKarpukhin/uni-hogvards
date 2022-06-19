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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
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
    private void saveFacultyTest() throws Exception {
        final String nameFaculty = "B2";
        final String colorFaculty = "Yellow";
        final long id = 1;

        // send data to server to save
        final JSONObject facultyObject = new JSONObject();
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
        when(facultyRepository.findByColor(any(String.class))).thenReturn((clFac));
        when(facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(any(String.class), any(String.class))).thenReturn(clFac);
        when(facultyRepository.findFacultyByStudentId(any(Long.class))).thenReturn(nameFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(nameFaculty))
                .andExpect(jsonPath("$.color").value(colorFaculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/color/" + colorFaculty)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(nameFaculty))
                .andExpect(jsonPath("$.color").value(colorFaculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/facstudent/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(nameFaculty))
                .andExpect(jsonPath("$.color").value(colorFaculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(nameFaculty))
                .andExpect(jsonPath("$.color").value(colorFaculty));

    }

}
