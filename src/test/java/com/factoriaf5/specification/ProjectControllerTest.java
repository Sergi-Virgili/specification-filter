package com.factoriaf5.specification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    @Transactional
    public void clearDatabase() {
        entityManager.createQuery("DELETE FROM Project").executeUpdate();
    }

    @Test
    @Transactional
    void shouldReturnEmptyProjectsListWhenDatabaseIsEmpty() throws Exception {
        mockMvc.perform(get("/projects")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{"
                        + "\"totalPages\":0,"
                        + "\"totalElements\":0,"
                        + "\"size\":10,"
                        + "\"content\":[],"
                        + "\"number\":0,"
                        + "\"sort\":{\"empty\":false,\"sorted\":true,\"unsorted\":false},"
                        + "\"pageable\":{\"pageNumber\":0,\"pageSize\":10,\"sort\":{\"empty\":false,\"sorted\":true,\"unsorted\":false},\"offset\":0,\"paged\":true,\"unpaged\":false},"
                        + "\"numberOfElements\":0,"
                        + "\"first\":true,"
                        + "\"last\":true,"
                        + "\"empty\":true"
                        + "}"));
    }


    @Test
    @Transactional
    public void shouldReturnProjectsWhenDatabaseIsNotEmpty() throws Exception {
        // Insert a test project into the database
        entityManager.createNativeQuery("INSERT INTO project (name, manager, location, start_date, end_date, description) VALUES ('Project Alpha', 'Alice', 'New York', '2024-01-01', '2024-06-01', 'Description for Project Alpha')")
                .executeUpdate();

        mockMvc.perform(get("/projects")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{"
                        + "\"totalPages\":1,"
                        + "\"totalElements\":1,"
                        + "\"size\":10,"
                        + "\"content\":[{\"id\":1,\"name\":\"Project Alpha\",\"manager\":\"Alice\",\"location\":\"New York\",\"startDate\":\"2024-01-01\",\"endDate\":\"2024-06-01\",\"description\":\"Description for Project Alpha\"}],"
                        + "\"number\":0,"
                        + "\"sort\":{\"empty\":false,\"sorted\":true,\"unsorted\":false},"
                        + "\"pageable\":{\"pageNumber\":0,\"pageSize\":10,\"sort\":{\"empty\":false,\"sorted\":true,\"unsorted\":false},\"offset\":0,\"paged\":true,\"unpaged\":false},"
                        + "\"numberOfElements\":1,"
                        + "\"first\":true,"
                        + "\"last\":true,"
                        + "\"empty\":false"
                        + "}"));
    }
}