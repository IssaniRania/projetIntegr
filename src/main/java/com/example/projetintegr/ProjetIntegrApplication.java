package com.example.projetintegr;

import com.example.projetintegr.dao.UserRepository;
import com.example.projetintegr.entities.Club;
import com.example.projetintegr.entities.Formation;
import com.example.projetintegr.entities.Users;
import com.example.projetintegr.service.ClubServiceImpl;
import com.example.projetintegr.service.FormationServiceImpl;
//import com.example.projetintegr.service.UserServiceImpl;
import com.example.projetintegr.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
//@EnableWebSecurity
@SpringBootApplication
public class ProjetIntegrApplication implements CommandLineRunner {
    @Autowired
    private ClubServiceImpl service;
    @Autowired
    private UserServiceImpl userservice;
    @Autowired
    private FormationServiceImpl formationService;
    public static void main(String[] args) {
        SpringApplication.run(ProjetIntegrApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
     /*   Users us1= new Users("raniaissani","rania@gmail.com","123rania",null);
        Users us2= new Users("omarsaidani","omar@gmail.com","123omar",null);
        Club club1 = new Club("Codeage",80.10,new Date(),"creation des application","https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8&w=1000&q=80",us1);
        Club club2 = new Club("Commerce",50.10,new Date(),"Management","https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8&w=1000&q=80",us2);
    // Formation f= new Formation("spring",40, new Date(), "formation",  "imageFormation");
        userservice.saveUser(us1);
        userservice.saveUser(us2);
        service.saveClub(club1);
        service.saveClub(club2);*/
       // formationService.saveFormation(f);
    }
}
