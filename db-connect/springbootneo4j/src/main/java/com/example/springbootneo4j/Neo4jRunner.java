package com.example.springbootneo4j;

import com.example.springbootneo4j.Account.Account;
import com.example.springbootneo4j.Account.AccountRepository;
import com.example.springbootneo4j.Account.Role;
import org.neo4j.driver.Session;
import org.neo4j.driver.internal.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {
//    @Autowired
    Neo4jTemplate neo4jTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account = new Account();
        account.setUsername("aaaa");
        account.setEmail("aaaa1234@email.com");

        Role role = new Role();
        role.setName("user");

        account.getRoles().add(role);


        accountRepository.save(account);

        System.out.println("finished");


    }
}
