import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spittr.controller.BusinessController;

import javax.servlet.http.HttpServlet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest {
    @Test
    public void testHomePage() throws  Exception{
        BusinessController homeController = new BusinessController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

        mockMvc.perform(get("/")).andExpect(view().name("home"));

        HttpServlet
    }
}
