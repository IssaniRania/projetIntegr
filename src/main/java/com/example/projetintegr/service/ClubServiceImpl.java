package com.example.projetintegr.service;

import com.example.projetintegr.dao.ClubRepository;
import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService{
    @Autowired
    ClubRepository clubRepository;
    @Override
    public Club saveClub(Club c) {
        return clubRepository.save(c);
    }
    @Override
    public Club updateClub(Club c) {
        return clubRepository.save(c);
    }
    @Override
    public void deleteClub(Club c) {
        clubRepository.delete(c);

    }
    @Override
    public void deleteClubById(Long id) {
        clubRepository.deleteById(id);
    }
    @Override
    public Club getClub(Long id) {
        return clubRepository.findById(id).get();
    }
    @Override
    public List<Club> getAllClub() {

        return clubRepository.findAll();
    }
    @Override
    public Page<Club> getAllClubsParPage(int page, int size) {
        // TODO Auto-generated method stub
        return clubRepository.findAll(PageRequest.of(page, size));
    }
    @Override
    public Club getClubById(Long id) {
        Optional<Club> club = clubRepository.findById(id);//option est une class pour tester club vide ou non
        return club.orElse(null);//orElse qui test null ou non
    }
    @Override
    public Club findById(Long clubId) {
       return clubRepository.findById(clubId).orElse(null) ;   }

    @Override
    public List<Club> findByNomClub(String nom) {

        return clubRepository.findByNomClub(nom);
    }
}
