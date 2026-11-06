
package kz.iitu.springlab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityAccessTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void userCanViewBooks() throws Exception {

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());

        System.out.println("USER GET /books: 200 OK");
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void userCannotDeleteBooks() throws Exception {

        mockMvc.perform(
                post("/books/999999/delete")
                        .with(csrf())
        ).andExpect(status().isForbidden());

        System.out.println("USER DELETE: 403 FORBIDDEN");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void adminCanViewBooks() throws Exception {

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());

        System.out.println("ADMIN GET /books: 200 OK");
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void adminCannotDeleteWithoutCsrf() throws Exception {

        mockMvc.perform(
                post("/books/999999/delete")
        ).andExpect(status().isForbidden());

        System.out.println("CSRF TEST: 403 FORBIDDEN");
    }
}
