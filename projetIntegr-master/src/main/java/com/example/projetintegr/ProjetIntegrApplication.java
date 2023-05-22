package com.example.projetintegr;

import com.example.projetintegr.entities.Club;
import com.example.projetintegr.service.ClubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ProjetIntegrApplication implements CommandLineRunner {
    @Autowired
    private ClubServiceImpl service;
    public static void main(String[] args) {
        SpringApplication.run(ProjetIntegrApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        Club club1 = new Club("Codeage",80.10,new Date(),"creation des application");
        Club club2 = new Club("Commerce",50.10,new Date(),"Management");

        service.saveClub(club1);
        service.saveClub(club2);
    }
}
