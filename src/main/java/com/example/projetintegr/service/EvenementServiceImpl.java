package com.example.projetintegr.service;

import com.example.projetintegr.dao.EvenementRepository;
import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EvenementServiceImpl implements EvenementService{
    @Autowired
    EvenementRepository evenementRepository;
    @Override
    public Evenement saveEvenement(Evenement event) {
        return evenementRepository.save(event);
    }

    @Override
    public Evenement updateEvenement(Evenement event) {
        return evenementRepository.save(event);

    }

    @Override
    public void deleteEvenement(Evenement event) {
      evenementRepository.delete(event);

    }

    @Override
    public void deleteEvenementById(Long id) {
        evenementRepository.deleteById(id);
    }

    @Override
    public Evenement getEvenement(Long id) {
        return  evenementRepository.findById(id).get();
    }

    @Override
    public List<Evenement> getAllEvenement() {
        return  evenementRepository.findAll();
    }

    @Override
    public Page<Evenement> getAllEvenementsParPage(int page, int size) {
        return  evenementRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Evenement getEvenementById(Long id) {
        Optional<Evenement> event = evenementRepository.findById(id);//option est une class pour tester club vide ou non
        return event.orElse(null);//orElse qui test null ou non
          }
    @Override
    public List<Evenement> findByNomEvenement(String nom) {

        return evenementRepository.findByNomEvenement(nom);
    }
}
