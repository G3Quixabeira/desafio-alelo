package br.com.silvioricardo.alelo.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiTerceirosTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup()
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getResultadosOk() throws Exception {
        mockMvc.perform(
                get("/resultados/1234").header("X-API-KEY", "OAISJd8a9sdouihs98adUAHSDiusfyIUSYIUQY"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getResultadosError() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isNotFound());
    }
}