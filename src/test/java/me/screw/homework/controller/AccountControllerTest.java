package me.screw.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.screw.homework.domain.Account;
import me.screw.homework.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    private AccountService accountService;

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
        given(this.accountService.createAccount(account))
                .willReturn(1L);
        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(content))

                .andExpect(status().isOk())
                .andDo(print());
    }
}