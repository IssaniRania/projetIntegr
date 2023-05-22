package com.example.projetintegr.service;

import com.example.projetintegr.dao.CertifRepository;
import com.example.projetintegr.entities.Certif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CertifServiceImpl implements CertifService {
    @Autowired
    CertifRepository certifRepository;

    @Override
    public Certif saveCertif(Certif cer) {
        return certifRepository.save(cer);
    }

    @Override
    public Certif updateCertif(Certif cer) {
        return certifRepository.save(cer);
    }

    @Override
    public void deleteCertif(Certif cer) {
        certifRepository.delete(cer);

    }

    @Override
    public void deleteCertifById(Long id) {
        certifRepository.deleteById(id);
    }

    @Override
    public Certif getCertif(Long id) {
        return certifRepository.findById(id).get();
    }

    @Override
    public List<Certif> getAllCertif() {

        return certifRepository.findAll();
    }

    @Override
    public Page<Certif> getAllCertifsParPage(int page, int size) {
        // TODO Auto-generated method stub
        return certifRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Certif getCertifById(Long id) {
        Optional<Certif> certif = certifRepository.findById(id);//option est une class pour tester club vide ou non
        return certif.orElse(null);//orElse qui test null ou non
    }

    @Override
    public List<Certif> findByNomCertif(String nom) {

        return certifRepository.findByNomCertif(nom);
    }


}
