package com.example.demospringsecurity.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Autowired
    ObjectMapper objectMapper;

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void index_anonymous() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void createAccount() throws Exception {
        // given
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setUsername("test");
        accountRequest.setPassword("pass");
        accountRequest.setRole("USER");

        // when
        ResultActions resultActions = this.mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)));

        // then
        resultActions.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void login_success() throws Exception {
        String username = "daeho";
        String password = "123";
        Account account = createNewAccount(username, password);
        this.mockMvc.perform(formLogin().user(account.getUsername()).password(password))
                .andExpect(authenticated());
    }

    @Test
    @Transactional
    void login_fail() throws Exception {
        String username = "daeho";
        String password = "123";
        String invalidPassword ="12345";
        Account account = createNewAccount(username, password);
        this.mockMvc.perform(formLogin().user(account.getUsername()).password(invalidPassword))
                .andExpect(unauthenticated());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void access_ROLE_USER_url_with_ROLE_ADMIN() throws Exception {
        String username = "daeho";
        String password = "123";
        Account account = createNewAccount(username, password);
        account.setRole("ADMIN");
        this.mockMvc.perform(get("/info"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    private Account createNewAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole("USER");
        return accountService.createNew(account);
    }


}