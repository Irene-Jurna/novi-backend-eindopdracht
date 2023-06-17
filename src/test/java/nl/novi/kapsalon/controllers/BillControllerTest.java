package nl.novi.kapsalon.controllers;//package nl.novi.kapsalon.controllers;
//
//import nl.novi.kapsalon.dtos.BillInputDto;
//import nl.novi.kapsalon.models.Role;
//import nl.novi.kapsalon.models.User;
//import nl.novi.kapsalon.repositories.UserRepository;
//import nl.novi.kapsalon.services.BillService;
//import nl.novi.kapsalon.services.JwtService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@WebMvcTest(BillController.class)
//@ActiveProfiles("test")
//class BillControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    JwtService jwtService;
//
//    @MockBean
//    UserRepository userRepos;
//
//    @MockBean
//    BillService billService;
//
//    private void createTestUser() {
//        User testUser = new User();
//        testUser.setFirstName("Test");
//        testUser.setLastName("User");
//        testUser.setUsername("TestUser");
//        testUser.setEmail("testuser@email.com");
//        testUser.setPassword("testpassword");
//        testUser.setAddress("testadres");
//        testUser.setHouseNumber(1);
//        testUser.setResidence("Teststad");
//        testUser.setInCaseOfEmergencyContact("Test contactpersoon");
//        testUser.setEmergencyContactPhoneNumber("Test nummer");
//        testUser.setPreferredHairdresser("Test kapster");
//        testUser.setNotes("Test knipbeurt geslaagd");
//
//        Role role = new Role("ROLE_OWNER");
//        testUser.setRole(role);
//
//        userRepos.save(testUser);
//    }
//
//    @Test
//    @WithUserDetails("testUser")
//    @WithMockUser(username = "testUser", roles = "ROLE_OWNER")
//    @DisplayName("Should delete correct bill")
//    void deleteBill() throws Exception {
//
//        createTestUser();
//
//        BillInputDto biDto = new BillInputDto();
//        biDto.customerId = 56L;
//        biDto.hairdresserId = 50L;
//        biDto.treatmentIds = new ArrayList<>(Arrays.asList(30L, 31L));
//        biDto.productIds = new ArrayList<>(Arrays.asList(10L, 11L));
//        biDto.paid = true;
//        biDto.setId(123L);
//
//        Mockito.doNothing().when(billService).deleteBill(123L);
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.delete("/bill/123"))
//                .andExpect(MockMvcResultMatchers.status().isNoContent())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//}


