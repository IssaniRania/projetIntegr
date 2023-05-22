package com.example.projetintegr.service;

import com.example.projetintegr.entities.Certif;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CertifService {
    Certif saveCertif(Certif cer);
    Certif updateCertif(Certif cer);
    void deleteCertif(Certif cer);
    void deleteCertifById(Long id);
    Certif getCertif(Long id);
    List<Certif> getAllCertif();
    Page<Certif> getAllCertifsParPage(int page, int size);

    Certif getCertifById(Long id);

    List<Certif> findByNomCertif(String nom);


}
