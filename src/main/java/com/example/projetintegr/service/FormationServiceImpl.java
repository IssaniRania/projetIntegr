package com.example.projetintegr.service;

import com.example.projetintegr.dao.FormationRepository;
import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.Club;
import com.example.projetintegr.entities.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationServiceImpl implements FormationService{
    @Autowired
    FormationRepository formationRepository;
    @Override
    public Formation saveFormation(Formation f) {
        return formationRepository.save(f);
    }

    @Override
    public Formation updateFormation(Formation f) {
        return formationRepository.save(f);
    }

    @Override
    public void deleteFormation(Formation f) {
        formationRepository.delete(f);
    }

    @Override
    public void deleteFormationById(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public Formation getFormation(Long id) {
        return formationRepository.findById(id).get();
    }

    @Override
    public List<Formation> getAllFormation() {
        return formationRepository.findAll();
    }

    @Override
    public Page<Formation> getAllFormationsParPage(int page, int size) {
        return formationRepository.findAll(PageRequest.of(page, size)) ;
    }
    @Override
    public Formation getFormationById(Long id) {
        Optional<Formation> formation = formationRepository.findById(id);//option est une class pour tester club vide ou non
        return formation.orElse(null);//orElse qui test null ou non
    }
    @Override
    public List<Formation> findByNomFormation(String nom) {

        return formationRepository.findByNomFormation(nom);
    }

}
