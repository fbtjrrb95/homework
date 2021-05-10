package me.screw.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.screw.homework.domain.Account;
import me.screw.homework.repository.AccountRepository;
import me.screw.homework.service.AccountService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
// 왜 public이 사라져야 하는 거지?
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountController accountController;

    @MockBean
    private BindingResult bindingResult;

    @Test
    public void createAccountTest() throws Exception {
        Account account = Account.builder()
                .name("seokkyu")
                .nick("screw")
                .password("1234567891010")
                .email("tjrrb95@gmail.com")
                .phonenumber("01087399737")
                .gender("male")
                .build();
        String content = objectMapper.writeValueAsString(account);
        given(this.accountController.createAccount(account, bindingResult))
                .willReturn(new ResponseEntity<>(account, HttpStatus.OK));
        mockMvc.perform(post("/account/save")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}