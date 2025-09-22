package com.example.auth.controller;
import com.example.auth.AuthApplication; import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
@SpringBootTest(classes = com.example.auth.AuthApplication.class) @AutoConfigureMockMvc
class AuthControllerIntegrationTest {
    @Autowired private MockMvc mockMvc;
    @Test void testRegisterAndLogin() throws Exception {
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content("{"username":"bob","password":"bobpass"}")).andExpect(status().isOk());
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content("{"username":"bob","password":"bobpass"}")).andExpect(status().isOk()).andExpect(jsonPath("$.accessToken").exists()).andExpect(jsonPath("$.refreshToken").exists());
    }
}
