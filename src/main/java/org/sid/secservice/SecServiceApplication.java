package org.sid.secservice;

import org.sid.secservice.secr.entities.AppRole;
import org.sid.secservice.secr.entities.AppUser;
import org.sid.secservice.secr.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true , securedEnabled = true)
public class SecServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }
    @Bean
    CommandLineRunner start(AccountService accountService){
        return  args -> {
            accountService.addNewAppRole(new AppRole(null ,"USER")) ;
            accountService.addNewAppRole(new AppRole(null ,"ADMIN")) ;
            accountService.addNewAppRole(new AppRole(null ,"CUSTOMER_MANAGER")) ;
            accountService.addNewAppRole(new AppRole(null ,"BILL_MANAGER")) ;


            accountService.addNewAppUser(new AppUser(null , "user1","1234" , new ArrayList<>())) ;
            accountService.addNewAppUser(new AppUser(null , "user2","1234" , new ArrayList<>())) ;
            accountService.addNewAppUser(new AppUser(null , "user3","1234" , new ArrayList<>())) ;

            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user1","ADMIN");

            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("user2","CUSTOMER_MANAGER");

            accountService.addRoleToUser("user3","USER");
            accountService.addRoleToUser("user3","BILL_MANAGER");
        } ;

    }
}
