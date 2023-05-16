package com.example.khalil_bejaoui_5lgSWHiYhN.web;

import com.example.khalil_bejaoui_5lgSWHiYhN.Util.PageDeserializer;
import com.example.khalil_bejaoui_5lgSWHiYhN.dto.StudentDTO;
import com.example.khalil_bejaoui_5lgSWHiYhN.web.dto.ErrorResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentsControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @BeforeAll()
    public void init() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Page.class, new PageDeserializer<>());
        objectMapper.registerModule(module);
    }


    @Test
    public void should_return_401_unauthorized() throws Exception {
        this.mockMvc.perform(get("/api/v1/students")).andDo(print()).andExpect(status().isUnauthorized()).andReturn();
    }

    @Test
    @WithMockUser
    public void should_return_list_of_student() throws Exception {


        MvcResult result =
              this.mockMvc.perform(get("/api/v1/students")).andDo(print()).andExpect(status().is2xxSuccessful())
                    .andReturn();
        Page<StudentDTO> studentResponse =
              objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Page<StudentDTO>>() {
              });

        assertEquals(3, studentResponse.getTotalElements());
    }

    @Test
    @WithMockUser
    public void should_return_student_by_classname() throws Exception {


        MvcResult result = this.mockMvc.perform(get("/api/v1/students?className=B")).andDo(print())
              .andExpect(status().is2xxSuccessful()).andReturn();
        Page<StudentDTO> studentResponse =
              objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Page<StudentDTO>>() {
              });

        assertEquals(1, studentResponse.getTotalElements());
        StudentDTO studentDTO = studentResponse.getContent().get(0);
        assertEquals("B", studentDTO.getStudentClass().getName());
    }

    @Test
    @WithMockUser
    public void should_return_student_by_teacher_fullName() throws Exception {

        MvcResult result =
              this.mockMvc.perform(get("/api/v1/students?teacherFirstName=salim&teacherLastName=Ben slouma"))
                    .andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();
        Page<StudentDTO> studentResponse =
              objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Page<StudentDTO>>() {
              });

        assertEquals(2, studentResponse.getTotalElements());
        StudentDTO studentDTO = studentResponse.getContent().get(0);
        assertEquals("salim", studentDTO.getStudentClass().getTeacher().getFirstName());
        assertEquals("Ben slouma", studentDTO.getStudentClass().getTeacher().getLastName());
    }

    @Test
    @WithMockUser
    public void should_return_badRequestException() throws Exception {

        MvcResult result =
              this.mockMvc.perform(get("/api/v1/students?teacherFirstName=salim"))
                    .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        ErrorResponseDTO errorResponse =
              objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponseDTO.class);

        assertEquals("You need to provide both first name and last name for the teacher.", errorResponse.message());
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.status());
    }


}
