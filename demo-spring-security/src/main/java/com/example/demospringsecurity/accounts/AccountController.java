package com.example.demospringsecurity.accounts;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final AccountService accountService;

    private final ModelMapper modelMapper;

    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/account")
    public String createAccountGet(){
        return "hello";
    }

    @PostMapping("/account")
    public ResponseEntity createAccount(@RequestBody AccountRequest accountRequest){
        Account save = accountService.createNew(modelMapper.map(accountRequest, Account.class));
        return ResponseEntity.ok(save);
    }

    @GetMapping("/account/debug")
    public ResponseEntity createAccountDebug(AccountRequest accountRequest){
        Account save = accountService.createNew(modelMapper.map(accountRequest, Account.class));
        return ResponseEntity.ok(save);
    }
}
