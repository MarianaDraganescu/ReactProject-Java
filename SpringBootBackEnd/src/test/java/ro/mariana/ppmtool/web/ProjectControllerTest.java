package ro.mariana.ppmtool.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.mariana.ppmtool.domain.Project;
import ro.mariana.ppmtool.services.MapValidationErrorService;
import ro.mariana.ppmtool.services.ProjectService;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @MockBean
    private ProjectService projectService;

    @MockBean
    private MapValidationErrorService mapValidationErrorService;

    private ProjectController projectController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {

        projectController = new ProjectController(projectService,mapValidationErrorService);
    }

    @Test
    public void createNewProject_whenPostRequestWithValidProject_thenCorrectResponse() throws Exception {
        String project = "{\"description\":\"desc\",\n" +
                "    \"projectIdentifier\": \"id13\",\n" +
                "    \"projectName\": \"Project name\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/projects")
                .content(project)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

   /* @Test
    public void createNewProject_whenPostRequestWithNullProjectName_thenCorrectResponse() throws Exception {
        String project = "{\"description\":\"desc\",\n" +
                "    \"projectIdentifier\": \"id13\",\n" +
                "    \"projectName\": \"\"}";

        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/projects")
                .content(objectMapper.writeValueAsString(project1)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    } */
}