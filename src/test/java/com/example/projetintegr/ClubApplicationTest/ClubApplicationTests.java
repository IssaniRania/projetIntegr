package com.example.projetintegr.ClubApplicationTest;

import com.example.projetintegr.dao.ClubRepository;
import com.example.projetintegr.entities.Club;
import com.example.projetintegr.service.ClubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
@SpringBootTest
public class ClubApplicationTests {
    @Autowired
    private ClubRepository clubRepository;
    private ClubService service;

    @Test
    public void testCreateClub() {
        Club c = new Club("sport",15,new Date(),"sportifclub");
        clubRepository.save(c);
    }

    @Test
    public void testFindClub()
    {
        Club c = clubRepository.findById(1L).get();
        System.out.println(c);
    }
    @Test
    public void testUpdateClub()
    {
        Club p = clubRepository.findById(1L).get();
        p.setPrixClub(2000);
       clubRepository.save(p);
        System.out.println(p);
    }
    @Test
    public void testDeleteClub()
    {
        clubRepository.deleteById(1L);
    }
    @Test
    public void testFindAllClub()
    {
        List<Club> prods = clubRepository.findAll();

        for (Club p:prods)
            System.out.println(p);

    }
/*
    @Test
    public void testFindByNomClubsContains()
    {
        Page<Club> prods = service.getAllClubsParPage(0,3);
        System.out.println(prods.getSize());
        System.out.println(prods.getTotalElements());

        System.out.println(prods.getTotalPages());
        prods.getContent().forEach(p -> {System.out.println(p.toString());});
    }
 */
}
