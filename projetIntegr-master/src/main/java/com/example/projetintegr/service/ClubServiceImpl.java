package com.example.projetintegr.service;

import com.example.projetintegr.dao.ClubRepository;
import com.example.projetintegr.entities.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
