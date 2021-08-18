//package security.k8s;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//public class AccountTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//
//
//    @Test
//    public void index_user() throws Exception {
//        mockMvc.perform(get("/")
//                .with(user("jake").roles("USER")))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void admin_user() throws Exception {
//        mockMvc.perform(get("/admin")
//                .with(user("jake").roles("USER")))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }
//
//}
